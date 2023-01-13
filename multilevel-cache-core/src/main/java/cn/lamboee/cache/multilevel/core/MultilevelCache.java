package cn.lamboee.cache.multilevel.core;

import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

/**
 * Multi-level cache, L1, L2 must have value
 *
 * @author lambochen@yeah.net
 */
public class MultilevelCache implements Cache {

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

    public Cache getL1cache() {
        return l1cache;
    }

    public Cache getL2cache() {
        return l2cache;
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
        l2cache.put(key, value);
        l1cache.put(key, value);
    }

    @Override
    public void evict(Object key) {
        l2cache.evict(key);
        l1cache.evict(key);
    }

    @Override
    public void clear() {
        l2cache.clear();
        l1cache.clear();
    }
}
