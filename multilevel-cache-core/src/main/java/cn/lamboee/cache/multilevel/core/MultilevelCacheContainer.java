package cn.lamboee.cache.multilevel.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lambochen@yeah.net
 */
public class MultilevelCacheContainer {

    private static final Logger log = LoggerFactory.getLogger(MultilevelCacheContainer.class);

    /**
     * key: l1 cache or l2 cache
     * value: MultilevelCache
     */
    private Map<Cache, MultilevelCache> containers;

    public MultilevelCacheContainer() {
        this.containers = new ConcurrentHashMap<>();
    }

    public void register(MultilevelCache multilevelCache) {
        log.info("register multilevel cache to containers. cacheName: {} ({}, {})",
                multilevelCache.getName(), multilevelCache.getL1cache().getName(), multilevelCache.getL2cache().getName());

        register(multilevelCache.getL1cache(), multilevelCache);
        register(multilevelCache.getL2cache(), multilevelCache);
    }

    private void register(Cache cache, MultilevelCache multilevelCache) {
        containers.put(cache, multilevelCache);
    }

    public MultilevelCache lookup(Cache cache) {
        return containers.get(cache);
    }

}
