package cn.lamboee.cache.multilevel.core.event.serialize;

import cn.lamboee.cache.multilevel.core.event.Event;
import cn.lamboee.cache.multilevel.core.event.EventType;

import java.util.Optional;

/**
 * event serializer factory and utils
 *
 * @author lambochen@yeah.net
 */
public class EventSerializers {

    public static ClearEventSerializer clearEventSerializer() {
        return ClearEventSerializer.getInstance();
    }

    public static PutEventSerializer putEventSerializer() {
        return PutEventSerializer.getInstance();
    }

    public static EvictEventSerializer evictEventSerializer() {
        return EvictEventSerializer.getInstance();
    }

    public static Optional<EventSerializer<?>> lookup(Event event) {
        if (null == event || null == event.type()) {
            // illegal param, degrade
            return Optional.empty();
        }

        EventType type = event.type();
        if (type.isClear()) {
            return Optional.of(clearEventSerializer());
        }

        if (type.isPut()) {
            return Optional.of(putEventSerializer());
        }

        if (type.isEvict()) {
            return Optional.of(evictEventSerializer());
        }

        // will not arrive
        return Optional.empty();
    }

    public static String serializeForString(Event event) {
        Optional<EventSerializer<?>> serializerOp = EventSerializers.lookup(event);
        if (serializerOp.isEmpty()) {
            throw new IllegalArgumentException("event illegal");
        }

        EventSerializer<Event> eventSerializer = (EventSerializer<Event>) serializerOp.get();
        byte[] eventSerialized = eventSerializer.serialize(event);

        return new String(eventSerialized);
    }

}
