package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.event.Event;
import cn.lamboee.cache.multilevel.core.event.EventMessage;
import cn.lamboee.cache.multilevel.core.event.serialize.EventSerializers;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * redis facade client
 *
 * @author lambochen@yeah.net
 */
public class RedisClient {

    private RedisTemplate<?, ?> redisTemplate;
    private String TOPIC_EVENT_MESSAGE = "lamboee.cache.multilevel:topic.event.message";
    private String KEY_REDIS_NODE_ID_GENERATOR = "lamboee.cache.multilevel:key.node.id.generator";

    public RedisClient(RedisTemplate<?, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setEventMessageTopic(String eventMessageTopic) {
        this.TOPIC_EVENT_MESSAGE = eventMessageTopic;
    }

    public String getEventMessageTopic() {
        return this.TOPIC_EVENT_MESSAGE;
    }

    public void setNodeIdGeneratorRedisKey(String nodeIdGeneratorRedisKey) {
        this.KEY_REDIS_NODE_ID_GENERATOR = nodeIdGeneratorRedisKey;
    }

    public String getNodeIdGeneratorRedisKey() {
        return this.KEY_REDIS_NODE_ID_GENERATOR;
    }

    public String currentNodeId() {
        return RedisCacheNode.id();
    }

    public boolean isCurrentNodeId(String nodeId) {
        return currentNodeId().equalsIgnoreCase(nodeId);
    }

    public RedisTemplate<?, ?> redisTemplate() {
        return redisTemplate;
    }

    public RedisSerializer<?> redisSerializer() {
        return redisTemplate.getValueSerializer();
    }

    public void publishEventMessage(String nodeId, String cacheName, Event event) {
        redisTemplate.convertAndSend(
                TOPIC_EVENT_MESSAGE,
                EventMessage.builder().setNodeId(nodeId).setCacheName(cacheName).setData(EventSerializers.serializeForString(event)).build()
        );
    }
}
