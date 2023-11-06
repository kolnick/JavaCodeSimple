package com.kolnick.demo.server.command;


import com.kolnick.demo.StatusCode;
import com.kolnick.demo.meta.Response;
import com.kolnick.demo.util.RandomUtil;

/**
 * @author caochaojie
 * 2018/11/15 10:42
 * @description
 */
public class SimpleMessageCommand extends AbstractCommand {

    @Override
    public Response doCommand() {
        if (params != null && params.length > 0) {
            Object param = params[0];
            Long rand = RandomUtil.rand(1, 3);
            try {
                Thread.sleep(rand * 1000);
                if (param instanceof String) {
                    String s1 = (String) param;
                    return new Response(StatusCode.SUCCESS, s1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return new Response(StatusCode.ERROR, "fail");
    }
}
