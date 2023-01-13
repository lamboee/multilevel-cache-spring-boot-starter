package cn.lamboee.cache.multilevel.core.notice;

import cn.lamboee.cache.multilevel.core.node.CacheNodeWrapper;

import java.io.Serial;

/**
 * evit key operation event
 *
 * @author lambochen@yeah.net
 */
public class EvictEvent implements Event, CacheNodeWrapper {

    @Serial
    private static final long serialVersionUID = 8144152567656271015L;

    private final String nodeId;
    private final Object key;

    public EvictEvent(String nodeId, Object key) {
        this.nodeId = nodeId;
        this.key = key;
    }

    @Override
    public String getNodeId() {
        return nodeId;
    }

    public Object getKey() {
        return key;
    }

}
