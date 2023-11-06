package com.kolnick.demo.server.command;

import com.kolnick.demo.StatusCode;
import com.kolnick.demo.meta.Response;
import com.kolnick.demo.util.RandomUtil;

/**
 * @author caochaojie
 * 2018/11/15 10:42
 * @description
 */
public class SimpleMessage2Command extends AbstractCommand {

    @Override
    Response doCommand() {
        if (params != null && params.length > 0) {
            Object param = params[0];
            if (param instanceof String) {
                try {
                    Long rand = RandomUtil.rand(3, 5);
                    Thread.sleep(rand * 1000);
                    String s1 = (String) param;
                    return new Response(StatusCode.SUCCESS, s1);
                } catch (Exception e) {

                }
            }
        }
        return new Response(StatusCode.ERROR, "fail");
    }
}
