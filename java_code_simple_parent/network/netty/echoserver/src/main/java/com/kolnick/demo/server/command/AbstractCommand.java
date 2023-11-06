package com.kolnick.demo.server.command;

import com.alibaba.fastjson.JSON;
import com.kolnick.demo.meta.Response;
import io.netty.channel.Channel;

/**
 * @author caochaojie
 * 2018/11/15 10:45
 * @description
 */
public abstract class AbstractCommand implements ICommand {

    protected Channel channel;

    protected Object[] params;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    abstract Response doCommand();

    @Override
    public void execute() {
        Response response = doCommand();
        String s = JSON.toJSONString(response);
        s += "\r\n";
        channel.writeAndFlush(s);
    }
}
