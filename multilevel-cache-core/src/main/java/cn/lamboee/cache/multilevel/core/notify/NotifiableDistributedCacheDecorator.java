package cn.lamboee.cache.multilevel.core.notify;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;
import org.springframework.cache.Cache;

/**
 * @author lambochen@yeah.net
 */
public class NotifiableDistributedCacheDecorator extends NotifiableCacheDecorator {
    public NotifiableDistributedCacheDecorator(Cache cache) {
        super(cache);
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

    @Override
    public String nodeId() {
        // do not need to notify system
        return CacheNodeWrapper.DEFAULT_NODE_ID;
    }
}
