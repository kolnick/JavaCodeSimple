package com.kolnick.demo.meta;

import java.util.Arrays;

/**
 * @author caochaojie
 * 2018/11/15 10:25
 * @description
 */
public class Response {

    private int code;

    private Object[] result;

    public Response() {

    }

    public Response(int code, Object... result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object[] getResult() {
        return result;
    }

    public void setResult(Object[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
