package cn.lamboee.cache.multilevel.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;

import java.util.concurrent.Callable;

@Getter
@Setter
public class MultilevelCache extends AbstractValueAdaptingCache implements Cache {

    private Cache l1cache;
    private Cache l2cache;

    private String name;

    protected MultilevelCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return null != l1cache ? l1cache : l2cache;
    }

    @Override
    protected Object lookup(Object key) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
