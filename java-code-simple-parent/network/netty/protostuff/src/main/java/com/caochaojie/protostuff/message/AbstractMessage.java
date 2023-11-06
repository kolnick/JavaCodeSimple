package com.caochaojie.protostuff.message;

/**
 * @Author: caochaojie
 * @Date: 2019/5/14
 */
public abstract class AbstractMessage implements IMessage {
    private int id;
    private int length;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void setLength(int length) {
        this.length = length;
    }


}
