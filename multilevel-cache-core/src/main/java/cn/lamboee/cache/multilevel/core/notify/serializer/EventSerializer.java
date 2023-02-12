package cn.lamboee.cache.multilevel.core.notify.serializer;

import cn.lamboee.cache.multilevel.core.notify.Event;

/**
 * @author lambochen@yeah.net
 */
public interface EventSerializer<T extends Event> {

    byte[] serialize(T event);

    T deserialize(byte[] data);

}
