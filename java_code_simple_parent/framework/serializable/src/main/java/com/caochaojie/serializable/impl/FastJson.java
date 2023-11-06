package com.caochaojie.serializable.impl;

import com.alibaba.fastjson.JSON;
import com.caochaojie.serializable.Serialization;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
public class FastJson implements Serialization {

    @Override
    public <T> byte[] serializer(T obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) {
        return JSON.parseObject(data, clazz);
    }
}
