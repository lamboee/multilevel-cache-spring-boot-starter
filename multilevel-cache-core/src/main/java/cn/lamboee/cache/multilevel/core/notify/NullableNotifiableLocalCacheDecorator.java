package cn.lamboee.cache.multilevel.core.notify;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;
import org.springframework.cache.Cache;

/**
 * empty decorator
 *
 * @author lambochen@yeah.net
 * @see NotifiableCacheDecorator
 * @see NotifiableLocalCacheDecorator
 */
public class NullableNotifiableLocalCacheDecorator extends NotifiableLocalCacheDecorator {

    public NullableNotifiableLocalCacheDecorator(Cache cache) {
        super(cache);
    }

    @Override
    public String nodeId() {
        return CacheNodeWrapper.DEFAULT_NODE_ID;
    }

    @Override
    public void publish(PutEvent event) {
        // do nothing
    }

    @Override
    public void publish(EvictEvent event) {
        // do nothing
    }

    @Override
    public void publish(ClearEvent event) {
        // do nothing
    }
}
