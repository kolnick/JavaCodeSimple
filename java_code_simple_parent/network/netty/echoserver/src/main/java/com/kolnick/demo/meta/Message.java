package com.kolnick.demo.meta;

/**
 * @author caochaojie
 * 2018/11/15 10:13
 * @description
 */
public class Message {

    private String protocol;
    private String msg;

    public Message() {

    }

    public Message(String protocol, String msg) {
        this.protocol = protocol;
        this.msg = msg;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
