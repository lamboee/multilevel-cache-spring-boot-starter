package cn.lamboee.cache.multilevel.core.notify.serialize;

import cn.lamboee.cache.multilevel.core.notify.Event;
import cn.lamboee.cache.multilevel.core.notify.EventType;

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
        return lookup(type);
    }

    public static Optional<EventSerializer<?>> lookup(String event) {
        EventType type = EventType.ofEvent(event);
        return lookup(type);
    }

    public static Optional<EventSerializer<?>> lookup(EventType type) {
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

    public static Event deserialize(String eventType, String data) {
        return lookup(eventType).map(serializer -> serializer.deserialize(data.getBytes())).orElse(null);
    }
}
