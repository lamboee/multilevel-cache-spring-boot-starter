package cn.lamboee.cache.multilevel.core.serializer.support;

import cn.lamboee.cache.multilevel.core.serializer.Serializer;
import com.alibaba.fastjson2.JSON;

public class FastJsonSerializer implements Serializer {

    @Override
    public <T> byte[] serialize(T data) {
        return JSON.toJSONBytes(data);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        return JSON.parseObject(data, tClass);
    }

}
