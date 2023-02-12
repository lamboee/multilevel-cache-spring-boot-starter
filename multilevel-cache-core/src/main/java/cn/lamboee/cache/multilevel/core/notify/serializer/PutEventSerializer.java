package cn.lamboee.cache.multilevel.core.notify.serializer;

import cn.lamboee.cache.multilevel.core.notify.PutEvent;
import cn.lamboee.cache.multilevel.core.serializer.Serializer;

/**
 * @author lambochen@yeah.net
 */
public class PutEventSerializer implements EventSerializer<PutEvent> {

    private final Serializer support;

    public PutEventSerializer(Serializer serializer) {
        this.support = serializer;
    }

    @Override
    public byte[] serialize(PutEvent event) {
        return support.serialize(event);
    }

    @Override
    public PutEvent deserialize(byte[] data) {
        return support.deserialize(data, PutEvent.class);
    }

}
