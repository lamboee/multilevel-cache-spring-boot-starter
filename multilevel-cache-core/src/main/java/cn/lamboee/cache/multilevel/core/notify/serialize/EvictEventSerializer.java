package cn.lamboee.cache.multilevel.core.notify.serialize;

import cn.lamboee.cache.multilevel.core.notify.EvictEvent;

/**
 * @author lambochen@yeah.net
 */
public class EvictEventSerializer implements EventSerializer<EvictEvent> {

    private static final EvictEventSerializer SERIALIZER = new EvictEventSerializer();

    private EvictEventSerializer() {
    }

    public static EvictEventSerializer getInstance() {
        return SERIALIZER;
    }

    @Override
    public byte[] serialize(EvictEvent event) {
        // todo
        return new byte[0];
    }

    @Override
    public EvictEvent deserialize(byte[] data) {
        // todo
        return null;
    }

}
