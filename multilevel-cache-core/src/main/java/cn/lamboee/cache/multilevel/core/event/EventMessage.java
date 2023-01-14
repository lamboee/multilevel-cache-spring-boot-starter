package cn.lamboee.cache.multilevel.core.event;

import java.io.Serial;
import java.io.Serializable;

/**
 * event message dto
 *
 * @author lambochen@yeah.net
 */
public class EventMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -2038878375570263144L;

    private String nodeId;
    private String cacheName;
    private String eventType;
    private String data;

    public EventMessage() {
    }

    public EventMessage(String nodeId, String cacheName, String data) {
        this.nodeId = nodeId;
        this.cacheName = cacheName;
        this.data = data;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getCacheName() {
        return cacheName;
    }

    public String getData() {
        return data;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String nodeId;
        private String cacheName;
        private String data;

        public Builder setNodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public Builder setCacheName(String cacheName) {
            this.cacheName = cacheName;
            return this;
        }

        public Builder setData(String data) {
            this.data = data;
            return this;
        }

        public EventMessage build() {
            return new EventMessage(nodeId, cacheName, data);
        }
    }

}
