package cn.lamboee.cache.multilevel.core.notify.serialize;

import cn.lamboee.cache.multilevel.core.notify.Event;

/**
 * @author lambochen@yeah.net
 */
public interface EventSerializer<T extends Event> {

    byte[] serialize(T event);

    T deserialize(byte[] data);

}
