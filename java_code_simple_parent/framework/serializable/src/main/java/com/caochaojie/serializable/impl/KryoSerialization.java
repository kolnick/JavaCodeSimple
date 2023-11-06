package com.caochaojie.serializable.impl;

import com.caochaojie.serializable.Serialization;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
public class KryoSerialization implements Serialization {
    static Kryo kryo = new Kryo();

    @Override
    public <T> byte[] serializer(T obj) {
        byte[] buffer = new byte[2048];
        try (Output output = new Output(buffer);) {
            kryo.writeClassAndObject(output, obj);
            return output.toBytes();
        } catch (Exception e) {
        }
        return buffer;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) {
        try (Input input = new Input(data);) {
            return (T) kryo.readClassAndObject(input);
        } catch (Exception e) {
        }
        return null;
    }
}
