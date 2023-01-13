package cn.lamboee.cache.multilevel.core;

import org.springframework.cache.Cache;

/**
 * multi level cache builder
 *
 * @author lambochen@yeah.net
 * @see MultilevelCache
 */
public class MultilevelCacheBuilder {

    private Cache l1cache;
    private Cache l2cache;
    private String name;

    private static final ThreadLocal<MultilevelCacheBuilder> INSTANCE = new ThreadLocal<>();

    public static MultilevelCacheBuilder builder() {
        return INSTANCE.get();
    }

    public MultilevelCacheBuilder setL1cache(MultilevelCacheDecorator l1cache) {
        this.l1cache = l1cache;
        return this;
    }

    public MultilevelCacheBuilder setL2cache(MultilevelCacheDecorator l2cache) {
        this.l2cache = l2cache;
        return this;
    }

    public MultilevelCacheBuilder setCacheName(String cacheName) {
        this.name = cacheName;
        return this;
    }

    public MultilevelCache build() {
        MultilevelCacheBuilder.INSTANCE.remove();
        return new MultilevelCache(name, l1cache, l2cache);
    }
}
