package cn.lamboee.cache.multilevel.core;

import cn.lamboee.cache.multilevel.core.notify.Event;
import cn.lamboee.cache.multilevel.core.notify.EventMessage;
import cn.lamboee.cache.multilevel.core.notify.Subscriber;
import cn.lamboee.cache.multilevel.core.notify.serializer.EventSerializers;

/**
 * multilevel cache utils
 *
 * @author lambochen@yeah.net
 */
public class MultilevelCaches {

    public static void subscribeEventDispatcher(Subscriber subscriber, EventMessage eventMessage) {
        Event event = EventSerializers.deserialize(eventMessage.getEventType(), eventMessage.getData());
        subscriber.subscribe(event);
    }

}
