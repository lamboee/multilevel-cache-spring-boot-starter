package cn.lamboee.cache.multilevel.support.redis;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis cache facade
 *
 * @author lambochen@yeah.net
 */
@Component
public class RedisClient {

    @Resource
    private RedisTemplate<?, ?> redisTemplate;

    public static String currentNodeId() {
        return RedisCacheNode.id();
    }

    public RedisTemplate<?, ?> redisTemplate() {
        return redisTemplate;
    }

}
