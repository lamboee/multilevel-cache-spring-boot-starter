package cn.lamboee.cache.multilevel.core.notice;

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

    private final String nodeId;
    private final Object key;
    private final Object value;

    public PutEvent(String nodeId, Object key, Object value) {
        this.nodeId = nodeId;
        this.key = key;
        this.value = value;
    }

    @Override
    public String getNodeId() {
        return nodeId;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
