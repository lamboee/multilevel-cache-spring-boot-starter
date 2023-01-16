package cn.lamboee.cache.multilevel.core.notify;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;

import java.io.Serial;

/**
 * clear operation event
 *
 * @author lambochen@yeah.net
 */
public class ClearEvent implements Event, CacheNodeWrapper {

    @Serial
    private static final long serialVersionUID = -4798983359896640532L;

    private String nodeId;

    public ClearEvent(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String nodeId() {
        return nodeId;
    }

    @Override
    public EventType type() {
        return EventType.CLEAR;
    }
}
