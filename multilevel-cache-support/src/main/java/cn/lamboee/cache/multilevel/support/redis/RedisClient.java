package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.notify.Event;
import cn.lamboee.cache.multilevel.core.notify.EventMessage;
import cn.lamboee.cache.multilevel.core.notify.serialize.EventSerializers;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * redis facade client
 *
 * @author lambochen@yeah.net
 */
public class RedisClient {

    private RedisTemplate<?, ?> redisTemplate;
    private String messageEventTopic = "lamboee.cache.multilevel:topic.event.message";
    private String nodeIdGeneratorKey = "lamboee.cache.multilevel:key.node.id.generator";

    public RedisClient(RedisTemplate<?, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String currentNodeId() {
        return RedisCacheNode.id();
    }

    public String getMessageEventTopic() {
        return messageEventTopic;
    }

    public void setMessageEventTopic(String messageEventTopic) {
        this.messageEventTopic = messageEventTopic;
    }

    public String getNodeIdGeneratorKey() {
        return nodeIdGeneratorKey;
    }

    public void setNodeIdGeneratorKey(String nodeIdGeneratorKey) {
        this.nodeIdGeneratorKey = nodeIdGeneratorKey;
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
                messageEventTopic,
                EventMessage.builder().setNodeId(nodeId).setCacheName(cacheName).setData(EventSerializers.serializeForString(event)).build()
        );
    }
}
