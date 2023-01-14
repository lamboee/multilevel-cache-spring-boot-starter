package cn.lamboee.cache.multilevel.core.event.serialize;

import cn.lamboee.cache.multilevel.core.event.ClearEvent;

/**
 * @author lambochen@yeah.net
 */
public class ClearEventSerializer implements EventSerializer<ClearEvent> {

    private static final ClearEventSerializer SERIALIZER = new ClearEventSerializer();

    private ClearEventSerializer() {
    }

    public static ClearEventSerializer getInstance() {
        return SERIALIZER;
    }

    @Override
    public byte[] serialize(ClearEvent event) {
        // todo
        return new byte[0];
    }

    @Override
    public ClearEvent deserialize(byte[] data) {
        // todo
        return null;
    }
}
