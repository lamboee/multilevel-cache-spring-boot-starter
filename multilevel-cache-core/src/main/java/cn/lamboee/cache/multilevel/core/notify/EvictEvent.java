package cn.lamboee.cache.multilevel.core.notify;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;

import java.io.Serial;

/**
 * evict key operation event
 *
 * @author lambochen@yeah.net
 */
public class EvictEvent implements Event, CacheNodeWrapper {

    @Serial
    private static final long serialVersionUID = 8144152567656271015L;

    private String nodeId;
    private Object key;

    public EvictEvent(String nodeId, Object key) {
        this.nodeId = nodeId;
        this.key = key;
    }

    @Override
    public EventType type() {
        return EventType.EVICT;
    }

    @Override
    public String nodeId() {
        return nodeId;
    }

    public Object getKey() {
        return key;
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
}
