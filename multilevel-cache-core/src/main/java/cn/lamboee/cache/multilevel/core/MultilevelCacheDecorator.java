package cn.lamboee.cache.multilevel.core;

import cn.lamboee.cache.multilevel.core.event.ClearEvent;
import cn.lamboee.cache.multilevel.core.event.Event;
import cn.lamboee.cache.multilevel.core.event.EvictEvent;
import cn.lamboee.cache.multilevel.core.event.NoticeWrapper;
import cn.lamboee.cache.multilevel.core.event.PutEvent;
import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;
import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

/**
 * multi level cache decorator
 *
 * @author lambochen@yeah.net
 * @see Cache
 * @see CacheDecorator
 * @see MultilevelCache
 * @see NoticeWrapper
 * @see CacheNodeWrapper
 */
public abstract class MultilevelCacheDecorator implements Cache, CacheDecorator, NoticeWrapper, CacheNodeWrapper {

    protected Cache cache;

    @Override
    public String getName() {
        return cache.getName();
    }

    @Override
    public Object getNativeCache() {
        return cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        return cache.get(key);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return cache.get(key, type);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return cache.get(key, valueLoader);
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);

        publish(new PutEvent(nodeId(), key, value));
    }

    @Override
    public void evict(Object key) {
        cache.evict(key);

        publish(new EvictEvent(nodeId(), key));
    }

    @Override
    public void clear() {
        cache.clear();

        publish(new ClearEvent(nodeId()));
    }

    @Override
    public void publish(PutEvent event) {
        publishEventMessage(nodeId(), cache.getName(), event);
    }

    @Override
    public void publish(EvictEvent event) {
        publishEventMessage(nodeId(), cache.getName(), event);
    }

    @Override
    public void publish(ClearEvent event) {
        publishEventMessage(nodeId(), cache.getName(), event);
    }

    protected abstract void publishEventMessage(String nodeId, String name, Event event);

}
