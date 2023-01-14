package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.MultilevelCacheDecorator;
import cn.lamboee.cache.multilevel.core.event.ClearEvent;
import cn.lamboee.cache.multilevel.core.event.Event;
import cn.lamboee.cache.multilevel.core.event.EvictEvent;
import cn.lamboee.cache.multilevel.core.event.PutEvent;

/**
 * redis multi level cache decorator
 *
 * @author lambochen@yeah.net
 * @see cn.lamboee.cache.multilevel.core.MultilevelCache
 * @see MultilevelCacheDecorator
 */
public class RedisMultilevelCacheDecorator extends MultilevelCacheDecorator {

    private RedisClient redisClient;

    @Override
    public String nodeId() {
        return redisClient.currentNodeId();
    }

    @Override
    public void subscribe(PutEvent event) {
        // todo
    }

    @Override
    public void subscribe(EvictEvent event) {
        // todo
    }

    @Override
    public void subscribe(ClearEvent event) {
        // todo
    }

    @Override
    protected void publishEventMessage(String nodeId, String name, Event event) {
        redisClient.publishEventMessage(nodeId, name, event);
    }
}
