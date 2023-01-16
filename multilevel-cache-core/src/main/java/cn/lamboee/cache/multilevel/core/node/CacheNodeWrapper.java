package cn.lamboee.cache.multilevel.core.node;

import java.util.function.Supplier;

/**
 * cache node wrapper
 *
 * @author lambochen@yeah.net
 */
public interface CacheNodeWrapper extends Supplier<String> {

    String DEFAULT_NODE_ID = "1";

    @Override
    default String get() {
        return nodeId();
    }

    String nodeId();

}
