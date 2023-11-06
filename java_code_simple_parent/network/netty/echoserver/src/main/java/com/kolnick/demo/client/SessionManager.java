package com.kolnick.demo.client;

import io.netty.channel.Channel;

/**
 * @author caochaojie
 * 2018/11/15 13:44
 * @description
 */
public class SessionManager {
    public final static SessionManager instance = new SessionManager();

    private Channel clientChannel;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return instance;
    }

    public Channel getClientChannel() {
        return clientChannel;
    }

    public void setClientChannel(Channel clientChannel) {
        this.clientChannel = clientChannel;
    }


}
