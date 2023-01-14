package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.event.EventMessage;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author lambochen@yeah.net
 */
public class RedisEventMessageListener implements MessageListener {

    private RedisClient redisClient;

    public RedisEventMessageListener(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        EventMessage eventMessage = (EventMessage) redisClient.redisSerializer().deserialize(message.getBody());

        if (null == eventMessage || redisClient.isCurrentNodeId(eventMessage.getNodeId())) {
            return;
        }

        // todo
    }
}
