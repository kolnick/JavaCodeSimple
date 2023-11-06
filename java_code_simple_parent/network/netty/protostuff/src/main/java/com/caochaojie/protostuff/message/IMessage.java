package com.caochaojie.protostuff.message;

/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public interface IMessage {


    int getId();

    void setId(int id);

    int getLength();

    void setLength(int length);
}
