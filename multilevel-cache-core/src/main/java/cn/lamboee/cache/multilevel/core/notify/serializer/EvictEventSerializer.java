package cn.lamboee.cache.multilevel.core.notify.serializer;

import cn.lamboee.cache.multilevel.core.notify.EvictEvent;
import cn.lamboee.cache.multilevel.core.serializer.Serializer;

/**
 * @author lambochen@yeah.net
 */
public class EvictEventSerializer implements EventSerializer<EvictEvent> {

    private final Serializer support;

    public EvictEventSerializer(Serializer serializer) {
        this.support = serializer;
    }

    @Override
    public byte[] serialize(EvictEvent event) {
        return support.serialize(event);
    }

    @Override
    public EvictEvent deserialize(byte[] data) {
        return support.deserialize(data, EvictEvent.class);
    }

}
