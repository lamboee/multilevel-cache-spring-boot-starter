package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.MultilevelCacheDecorator;
import cn.lamboee.cache.multilevel.core.notice.ClearEvent;
import cn.lamboee.cache.multilevel.core.notice.EvictEvent;
import cn.lamboee.cache.multilevel.core.notice.PutEvent;
import org.springframework.data.redis.cache.RedisCache;

/**
 * redis multi level cache decorator
 *
 * @author lambochen@yeah.net
 * @see cn.lamboee.cache.multilevel.core.MultilevelCache
 * @see MultilevelCacheDecorator
 */
public class RedisMultilevelCacheDecorator extends MultilevelCacheDecorator {

    private RedisClient redisClient;

    public RedisMultilevelCacheDecorator(boolean allowNullValues, RedisCache redisCache, RedisClient redisClient) {
        super(allowNullValues, redisCache);
        this.redisClient = redisClient;
    }

    @Override
    public String getNodeId() {
        return RedisClient.currentNodeId();
    }

    @Override
    public void publish(PutEvent event) {
        // todo
    }

    @Override
    public void publish(EvictEvent event) {
        // todo
    }

    @Override
    public void publish(ClearEvent event) {
        // todo
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
}
