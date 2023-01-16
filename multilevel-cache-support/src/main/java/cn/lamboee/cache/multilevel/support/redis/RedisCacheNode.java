package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.node.CacheNodeIdGenerator;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author lambochen@yeah.net
 */
public class RedisCacheNode {

    public static String id() {
        return RedisCacheNodeIdGenerator.getCurrentNodeId();
    }

    public static class RedisCacheNodeIdGenerator implements CacheNodeIdGenerator {

        private static String nodeId;

        public RedisCacheNodeIdGenerator(RedisClient redisClient) {
            nodeId = String.valueOf(
                    ((RedisTemplate<Object, Object>) redisClient.redisTemplate()).opsForValue()
                            .increment(redisClient.getNodeIdGeneratorKey())
            );
        }

        @Override
        public String get() {
            return nodeId;
        }

        public static String getCurrentNodeId() {
            return nodeId;
        }
    }
}
