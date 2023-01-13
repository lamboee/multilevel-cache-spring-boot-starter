package cn.lamboee.cache.multilevel.core.node;

import java.util.function.Supplier;

/**
 * cache node wrapper
 *
 * @author lambochen@yeah.net
 */
public interface CacheNodeWrapper extends Supplier<String> {

    @Override
    default String get() {
        return getNodeId();
    }

    String getNodeId();

}
