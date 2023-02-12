package cn.lamboee.cache.multilevel.core.notify.serializer;

import cn.lamboee.cache.multilevel.core.notify.ClearEvent;
import cn.lamboee.cache.multilevel.core.serializer.Serializer;

/**
 * @author lambochen@yeah.net
 */
public class ClearEventSerializer implements EventSerializer<ClearEvent> {

    private final Serializer support;

    public ClearEventSerializer(Serializer serializer) {
        this.support = serializer;
    }

    @Override
    public byte[] serialize(ClearEvent event) {
        return support.serialize(event);
    }

    @Override
    public ClearEvent deserialize(byte[] data) {
        return support.deserialize(data, ClearEvent.class);
    }

}
