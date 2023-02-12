package cn.lamboee.cache.multilevel.core.serializer;

/**
 * data serializer
 */
public interface Serializer {

    <T> byte[] serialize(T data);

    <T> T deserialize(byte[] data, Class<T> tClass);

}
