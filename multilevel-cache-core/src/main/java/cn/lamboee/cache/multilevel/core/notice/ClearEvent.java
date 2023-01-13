package cn.lamboee.cache.multilevel.core.notice;

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

    private final String nodeId;

    public ClearEvent(String nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public String getNodeId() {
        return nodeId;
    }
}
