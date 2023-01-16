package cn.lamboee.cache.multilevel.core.notify;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author lambochen@yeah.net
 */
public class NotifyMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -3927252234045894945L;

    private String nodeId;
    private String cacheName;
    private Event event;

    public NotifyMessage() {
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
