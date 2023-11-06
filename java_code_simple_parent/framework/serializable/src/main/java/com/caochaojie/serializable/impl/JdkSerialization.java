package com.caochaojie.serializable.impl;

import com.caochaojie.serializable.Serialization;

import java.io.*;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
public class JdkSerialization implements Serialization {

    @Override
    public <T> byte[] serializer(T obj) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos);) {
            oos.writeObject(obj);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bais);

        ) {
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
