package cn.lamboee.cache.multilevel.core;

/**
 * cache node utils
 *
 * @author lambochen
 */
public class Nodes {

    /**
     * current node id
     */
    private static String NODE_ID = null;

    public static String currentNodeId() {
        return NODE_ID;
    }

    public static void init(String nodeId) {
        Nodes.NODE_ID = nodeId;
    }

}
