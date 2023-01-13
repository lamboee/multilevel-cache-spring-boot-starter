package cn.lamboee.cache.multilevel.core;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;
import cn.lamboee.cache.multilevel.core.notice.ClearEvent;
import cn.lamboee.cache.multilevel.core.notice.EvictEvent;
import cn.lamboee.cache.multilevel.core.notice.NoticeWrapper;
import cn.lamboee.cache.multilevel.core.notice.PutEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;

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
public abstract class MultilevelCacheDecorator extends AbstractValueAdaptingCache implements Cache, CacheDecorator, NoticeWrapper, CacheNodeWrapper {

    protected Cache cache;

    public MultilevelCacheDecorator(boolean allowNullValues, Cache cache) {
        super(allowNullValues);
        this.cache = cache;
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
    protected Object lookup(Object key) {
        return cache.get(key);
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return cache.get(key, valueLoader);
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);

        publish(new PutEvent(getNodeId(), key, value));
    }

    @Override
    public void evict(Object key) {
        cache.evict(key);

        publish(new EvictEvent(getNodeId(), key));
    }

    @Override
    public void clear() {
        cache.clear();

        publish(new ClearEvent(getNodeId()));
    }
}
