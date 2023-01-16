package cn.lamboee.cache.multilevel.core.notify;

import java.util.Arrays;
import java.util.Optional;

/**
 * notice event type
 *
 * @author lambochen@yeah.net
 */
public enum EventType {

    /**
     * clear all
     */
    CLEAR("clear"),
    /**
     * evict some key
     */
    EVICT("evict"),
    /**
     * put new value for key
     */
    PUT("put");

    private final String event;

    EventType(String event) {
        this.event = event;
    }

    public static EventType ofEvent(String event) {
        return Arrays.stream(values()).filter(x -> x.event.equalsIgnoreCase(event)).findAny().orElse(null);
    }

    public boolean isClear() {
        return CLEAR.equals(this);
    }

    public static boolean isClear(String event) {
        return Optional.ofNullable(ofEvent(event)).map(EventType::isClear).orElse(false);
    }

    public boolean isEvict() {
        return EVICT.equals(this);
    }

    public static boolean isEvict(String event) {
        return Optional.ofNullable(ofEvent(event)).map(EventType::isEvict).orElse(false);
    }

    public boolean isPut() {
        return PUT.equals(this);
    }

    public static boolean isPut(String event) {
        return Optional.ofNullable(ofEvent(event)).map(EventType::isPut).orElse(false);
    }
}
