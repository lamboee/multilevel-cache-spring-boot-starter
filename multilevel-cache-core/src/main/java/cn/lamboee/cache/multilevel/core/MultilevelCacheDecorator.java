package cn.lamboee.cache.multilevel.core;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;
import cn.lamboee.cache.multilevel.core.notice.ClearEvent;
import cn.lamboee.cache.multilevel.core.notice.EvictEvent;
import cn.lamboee.cache.multilevel.core.notice.NoticeWrapper;
import cn.lamboee.cache.multilevel.core.notice.PutEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

/**
 * multi level cache decorator
 *
 * @author lambochen@yeah.net
 * @see CacheDecorator
 * @see Cache
 * @see MultilevelCache
 * @see NoticeWrapper
 * @see CacheNodeWrapper
 */
public abstract class MultilevelCacheDecorator implements Cache, CacheDecorator, NoticeWrapper, CacheNodeWrapper {

    private static final Logger logger = LoggerFactory.getLogger(MultilevelCache.class);

    protected Cache cache;

    public MultilevelCacheDecorator(Cache cache) {
        if (null == cache) {
            throw new IllegalArgumentException("cache is null");
        }
        this.cache = cache;
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
