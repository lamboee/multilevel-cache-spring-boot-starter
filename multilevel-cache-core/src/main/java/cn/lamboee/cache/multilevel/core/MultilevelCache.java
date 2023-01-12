package cn.lamboee.cache.multilevel.core;

import lombok.Builder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * Multi-level cache, L1, L2 must have value
 *
 * @author lambochen
 */
public abstract class MultilevelCache implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(MultilevelCache.class);

    protected final Cache l1cache;
    protected final Cache l2cache;
    protected final String name;

    public MultilevelCache(String name, Cache l1cache, Cache l2cache) {
        if (null == l1cache || null == l2cache) {
            throw new IllegalArgumentException("l1cache or l2cache is null");
        }

        this.l1cache = l1cache;
        this.l2cache = l2cache;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return l1cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        ValueWrapper valueWrapper = l1cache.get(key);
        if (null != valueWrapper) {
            return valueWrapper;
        }

        valueWrapper = l2cache.get(key);

        // update l1 cache
        if (null != valueWrapper) {
            l1cache.put(key, valueWrapper.get());
        }

        return valueWrapper;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        T data = l1cache.get(key, type);
        if (null != data) {
            return data;
        }

        data = l2cache.get(key, type);

        // update l1 cache
        if (null != data) {
            l1cache.put(key, data);
        }

        return data;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        T data = l1cache.get(key, valueLoader);
        if (null != data) {
            return data;
        }

        data = l2cache.get(key, valueLoader);

        // update l1 cache
        if (null != data) {
            l1cache.put(key, data);
        }

        return data;
    }

    @Override
    public void put(Object key, Object value) {
        l1cache.put(key, value);
        l2cache.put(key, value);

        publish(PutEvent.builder().nodeId(Nodes.currentNodeId()).key(key).value(value).build());
    }

    public void publish(Event event) {
        if (event instanceof PutEvent) {
            publish((PutEvent) event);
            return;
        }

        if (event instanceof EvictEvent) {
            publish((EvictEvent) event);
            return;
        }

        if (event instanceof ClearEvent) {
            publish((ClearEvent) event);
            return;
        }

        logger.warn("unsupported event. event:{}", event);
        throw new UnsupportedOperationException("Unsupported Event");
    }

    public abstract void publish(PutEvent event);

    public abstract void publish(EvictEvent event);

    public abstract void publish(ClearEvent event);

    public void subscribe(Event event) {
        if (event instanceof PutEvent) {
            subscribe((PutEvent) event);
            return;
        }

        if (event instanceof EvictEvent) {
            subscribe((EvictEvent) event);
            return;
        }

        if (event instanceof ClearEvent) {
            subscribe((ClearEvent) event);
            return;
        }

        logger.warn("unsupported event. event:{}", event);
        throw new UnsupportedOperationException("Unsupported Event");
    }

    public abstract void subscribe(PutEvent event);

    public abstract void subscribe(EvictEvent event);

    public abstract void subscribe(ClearEvent event);

    @Override
    public void evict(Object key) {
        l2cache.evict(key);
        l1cache.evict(key);

        publish(EvictEvent.builder().nodeId(Nodes.currentNodeId()).key(key).build());
    }

    @Override
    public void clear() {
        l2cache.clear();
        l1cache.clear();

        publish(ClearEvent.builder().nodeId(Nodes.currentNodeId()).build());
    }

    public interface Event extends Serializable {

    }

    @Data
    @Builder
    public static class PutEvent implements Event {

        private static final long serialVersionUID = 8988924161485730293L;

        private String nodeId;
        private Object key;
        private Object value;
    }

    @Data
    @Builder
    public static class EvictEvent implements Event {

        private static final long serialVersionUID = -8906429761200287230L;

        private String nodeId;
        private Object key;
    }

    @Data
    @Builder
    public static class ClearEvent implements Event {

        private static final long serialVersionUID = -6804480041016188724L;

        private String nodeId;
    }
}
