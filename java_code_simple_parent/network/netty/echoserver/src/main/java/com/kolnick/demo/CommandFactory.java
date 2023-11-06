package com.kolnick.demo;

import com.kolnick.demo.meta.Message;
import com.kolnick.demo.server.command.AbstractCommand;
import com.kolnick.demo.server.command.SimpleMessage2Command;
import com.kolnick.demo.server.command.SimpleMessageCommand;
import io.netty.channel.Channel;


/**
 * @author caochaojie
 * 2018/11/15 10:39
 * @description
 */
public class CommandFactory {

    public final static CommandFactory instance = new CommandFactory();

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public void executeCommand(Channel channel, Message message) {
        if (message == null) {
            return;
        }
        String protocol = message.getProtocol();
        String msg = message.getMsg();
        AbstractCommand cmd = null;
        switch (protocol) {
            case Protocal.SIMPLE_MESSAGE1: {
                cmd = new SimpleMessageCommand();
                break;
            }
            case Protocal.SIMPLE_MESSAGE2: {
                cmd = new SimpleMessage2Command();
            }
        }
        assert cmd != null;
        cmd.setParams(new String[]{msg});
        cmd.setChannel(channel);
        cmd.execute();
    }
}
