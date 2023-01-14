package cn.lamboee.cache.multilevel.core.event.serialize;

import cn.lamboee.cache.multilevel.core.event.Event;

/**
 * @author lambochen@yeah.net
 */
public interface EventSerializer<T extends Event> {

    byte[] serialize(T event);

    T deserialize(byte[] data);

}
