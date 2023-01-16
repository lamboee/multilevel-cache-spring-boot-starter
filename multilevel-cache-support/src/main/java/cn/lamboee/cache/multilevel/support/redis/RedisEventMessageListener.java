package cn.lamboee.cache.multilevel.support.redis;

import cn.lamboee.cache.multilevel.core.MultilevelCaches;
import cn.lamboee.cache.multilevel.core.notify.EventMessage;
import cn.lamboee.cache.multilevel.core.notify.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * redis event message listener
 *
 * @author lambochen@yeah.net
 * @see cn.lamboee.cache.multilevel.core.notify.Notifiable
 */
public class RedisEventMessageListener implements MessageListener {

    private static final Logger log = LoggerFactory.getLogger(RedisEventMessageListener.class);

    private RedisClient redisClient;
    private RedisCacheManager cacheManager;

    public RedisEventMessageListener(RedisClient redisClient, RedisCacheManager cacheManager) {
        this.redisClient = redisClient;
        this.cacheManager = cacheManager;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        EventMessage eventMessage = (EventMessage) redisClient.redisSerializer().deserialize(message.getBody());

        if (null == eventMessage || redisClient.isCurrentNodeId(eventMessage.getNodeId())) {
            return;
        }

        Cache cache = cacheManager.getCache(eventMessage.getCacheName());
        if (!(cache instanceof Subscriber)) {
            log.warn("no cache subscriber for [{}]", eventMessage.getCacheName());
            return;
        }

        Subscriber subscriber = (Subscriber) cache;
        MultilevelCaches.subscribeEventDispatcher(subscriber, eventMessage);
    }
}
