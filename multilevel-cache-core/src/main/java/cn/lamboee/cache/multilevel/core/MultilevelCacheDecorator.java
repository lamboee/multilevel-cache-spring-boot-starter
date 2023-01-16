package cn.lamboee.cache.multilevel.core;

import cn.lamboee.cache.multilevel.core.notify.ClearEvent;
import cn.lamboee.cache.multilevel.core.notify.EvictEvent;
import cn.lamboee.cache.multilevel.core.notify.Notifiable;
import cn.lamboee.cache.multilevel.core.notify.NotifyWrapper;
import cn.lamboee.cache.multilevel.core.notify.PutEvent;
import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;
import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

/**
 * notifiable local cache decorator
 *
 * @author lambochen@yeah.net
 * @see Cache
 * @see CacheDecorator
 * @see MultilevelCache
 * @see NotifyWrapper
 * @see CacheNodeWrapper
 * @see Notifiable
 */
public class MultilevelCacheDecorator implements Cache, CacheDecorator, NotifyWrapper, CacheNodeWrapper {

    protected Cache cache;
    protected Notifiable notifiable;

    public MultilevelCacheDecorator(Cache cache, Notifiable notifiable) {
        this.cache = cache;
        this.notifiable = notifiable;
    }

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
        notifiable.publish(event);
    }

    @Override
    public void publish(EvictEvent event) {
        notifiable.publish(event);
    }

    @Override
    public void publish(ClearEvent event) {
        notifiable.publish(event);
    }

    @Override
    public void subscribe(PutEvent event) {
        notifiable.subscribe(event);
    }

    @Override
    public void subscribe(EvictEvent event) {
        notifiable.subscribe(event);
    }

    @Override
    public void subscribe(ClearEvent event) {
        notifiable.subscribe(event);
    }

    @Override
    public String nodeId() {
        return notifiable.nodeId();
    }
}
