package cn.lamboee.cache.multilevel.core.event.serialize;

import cn.lamboee.cache.multilevel.core.event.PutEvent;

/**
 * @author lambochen@yeah.net
 */
public class PutEventSerializer implements EventSerializer<PutEvent> {

    private static final PutEventSerializer SERIALIZER = new PutEventSerializer();

    private PutEventSerializer() {
    }

    public static PutEventSerializer getInstance() {
        return SERIALIZER;
    }

    @Override
    public byte[] serialize(PutEvent event) {
        // todo
        return new byte[0];
    }

    @Override
    public PutEvent deserialize(byte[] data) {
        // todo
        return null;
    }

}
