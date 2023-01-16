package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.MultilevelCacheDecorator;
import cn.lamboee.cache.multilevel.core.notify.ClearEvent;
import cn.lamboee.cache.multilevel.core.notify.Event;
import cn.lamboee.cache.multilevel.core.notify.EvictEvent;
import cn.lamboee.cache.multilevel.core.notify.NotifiableLocalCacheDecorator;
import cn.lamboee.cache.multilevel.core.notify.PutEvent;
import org.springframework.cache.Cache;

/**
 * redis multi level cache decorator
 *
 * @author lambochen@yeah.net
 * @see cn.lamboee.cache.multilevel.core.MultilevelCache
 * @see MultilevelCacheDecorator
 */
public class RedisNotifiableLocalCacheDecorator extends NotifiableLocalCacheDecorator {

    private RedisClient redisClient;

    public RedisNotifiableLocalCacheDecorator(Cache cache, RedisClient redisClient) {
        super(cache);
        this.redisClient = redisClient;
    }

    @Override
    public String nodeId() {
        return redisClient.currentNodeId();
    }

    @Override
    public void publish(PutEvent event) {
        publishEventMessage(event);
    }

    @Override
    public void publish(EvictEvent event) {
        publishEventMessage(event);
    }

    @Override
    public void publish(ClearEvent event) {
        publishEventMessage(event);
    }

    private void publishEventMessage(Event event) {
        redisClient.publishEventMessage(nodeId(), cache.getName(), event);
    }
}
