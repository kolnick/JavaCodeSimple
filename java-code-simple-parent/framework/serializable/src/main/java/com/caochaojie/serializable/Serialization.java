package com.caochaojie.serializable;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
public interface Serialization {

    <T> byte[] serializer(T obj);

    <T> T deserialize(Class<T> clazz, byte[] data);

}
