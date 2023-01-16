package cn.lamboee.cache.multilevel.core.notify;

import org.springframework.cache.Cache;

/**
 * @author lambochen@yeah.net
 */
public abstract class NotifiableLocalCacheDecorator extends NotifiableCacheDecorator {
    public NotifiableLocalCacheDecorator(Cache cache) {
        super(cache);
    }
}
