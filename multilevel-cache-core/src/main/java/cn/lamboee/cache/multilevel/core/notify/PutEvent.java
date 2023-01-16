package cn.lamboee.cache.multilevel.core.notify;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;

import java.io.Serial;

/**
 * put key/value operation event
 *
 * @author lambochen@yeah.net
 */
public class PutEvent implements Event, CacheNodeWrapper {

    @Serial
    private static final long serialVersionUID = -2041822338226041360L;

    private String nodeId;
    private Object key;
    private Object value;

    public PutEvent() {
    }

    public PutEvent(String nodeId, Object key, Object value) {
        this.nodeId = nodeId;
        this.key = key;
        this.value = value;
    }

    @Override
    public String nodeId() {
        return nodeId;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public EventType type() {
        return EventType.PUT;
    }
}
