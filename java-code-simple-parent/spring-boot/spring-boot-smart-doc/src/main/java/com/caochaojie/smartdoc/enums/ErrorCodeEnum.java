package com.caochaojie.smartdoc.enums;

/**
 * @author caochaojie
 * 2023/1/9
 * @description
 */
public enum ErrorCodeEnum {
    SUCCESS( 200, "SUCCESS" ),//成功
    FAILURE( 400, "FAILURE" ),//失败
    UNAUTHORIZED( 401, "未认证或Token失效" ),//未认证（签名错误、token错误）
    USER_UNAUTHORIZED( 402, "用户名或密码不正确" ),//未通过认证
    NOT_FOUND( 404, "接口不存在" ),//接口不存在
    INTERNAL_SERVER_ERROR( 500, "服务器内部错误" );//服务器内部错误

    private int code;
    private String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
