package cn.lamboee.cache.multilevel.core;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * multi level cache manager
 *
 * @author lambochen@yeah.net
 * @see MultilevelCache
 * @see org.springframework.cache.CacheManager
 */
public interface MultilevelCacheManager extends CacheManager {

    default Cache getL1cache(String name) {
        Cache cache = getCache(name);
        if (cache instanceof MultilevelCache) {
            return ((MultilevelCache) cache).getL1cache();
        }

        return notMultilevelCacheProcess(cache);
    }

    default Cache getL2cache(String name) {
        Cache cache = getCache(name);
        if (cache instanceof MultilevelCache) {
            return ((MultilevelCache) cache).getL2cache();
        }

        return notMultilevelCacheProcess(cache);
    }

    default Cache notMultilevelCacheProcess(Cache cache) {
        // fast fail
        throw new RuntimeException("this cache is not MultilevelCache");
    }

}
