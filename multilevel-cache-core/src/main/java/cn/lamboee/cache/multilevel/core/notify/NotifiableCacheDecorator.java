package cn.lamboee.cache.multilevel.core.notify;

import org.springframework.cache.Cache;

/**
 * notifiable cache
 *
 * @author lambochen@yeah.net
 */
public abstract class NotifiableCacheDecorator implements Notifiable {

    protected Cache cache;

    public NotifiableCacheDecorator(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void subscribe(PutEvent event) {
        if (nodeId().equals(event.getNodeId())) {
            return;
        }

        cache.put(event.getKey(), event.getValue());
    }

    @Override
    public void subscribe(EvictEvent event) {
        if (nodeId().equals(event.getNodeId())) {
            return;
        }

        cache.evict(event.getKey());
    }

    @Override
    public void subscribe(ClearEvent event) {
        if (nodeId().equals(event.getNodeId())) {
            return;
        }

        cache.clear();
    }
}
