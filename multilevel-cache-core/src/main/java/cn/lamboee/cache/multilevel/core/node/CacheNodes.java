package cn.lamboee.cache.multilevel.core.node;

/**
 * cache node utils
 *
 * @author lambochen@yeah.net
 */
public class CacheNodes {

    /**
     * current node id
     */
    private static String NODE_ID = null;

    public static String currentNodeId() {
        return NODE_ID;
    }

    public static void init(String nodeId) {
        CacheNodes.NODE_ID = nodeId;
    }

}
