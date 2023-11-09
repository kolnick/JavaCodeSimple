package handler;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * @Author: caochaojie
 * @Date: 2023-11-09 10:51
 */
public class ClientIdleCheckHandler extends IdleStateHandler {

    public ClientIdleCheckHandler() {
        super(0, 5, 0);
    }
}
