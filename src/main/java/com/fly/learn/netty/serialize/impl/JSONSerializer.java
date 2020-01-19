package com.fly.learn.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.fly.learn.netty.serialize.Serializer;
import com.fly.learn.netty.serialize.SerializerAlgorithm;

/**
 * json序列化方式
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
