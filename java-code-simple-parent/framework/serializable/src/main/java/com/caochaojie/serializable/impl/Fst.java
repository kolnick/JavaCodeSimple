package com.caochaojie.serializable.impl;

import com.caochaojie.serializable.Serialization;
import org.nustaq.serialization.FSTConfiguration;

/**
 * @author caochaojie
 * 2022/7/1
 * @description
 */
public class Fst implements Serialization {

    static FSTConfiguration configuration = null;

    static {
        init();
    }

    private static void init() {
        configuration = FSTConfiguration.createDefaultConfiguration();
    }


    @Override
    public <T> byte[] serializer(T obj) {
        return configuration.asByteArray(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) {
        return (T) configuration.asObject(data);
    }
}
