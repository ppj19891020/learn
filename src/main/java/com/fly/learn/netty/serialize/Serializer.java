package com.fly.learn.netty.serialize;

import com.fly.learn.netty.serialize.impl.JSONSerializer;

/**
 * 序列化接口
 * @author: peijiepang
 * @date 2020-01-19
 * @Description:
 */
public interface Serializer {

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 序列化
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz,byte[] bytes);
}
