package com.sunan.literature.common;

public enum ServiceError {

    NORMAL(1, "操作成功"),
    INVALID_PARAMETER(-2,"参数不合法"),
    UN_KNOW_ERROR(-1, "未知错误"),


    GLOBAL_ERR_NO_SIGN_IN(-10001, "未登录或登录过期/Not sign in"),
    GLOBAL_ERR_WRONG_PASSWORD(-10002, "密码错误"),
    GLOBAL_ERR_NO_CODE(-10003, "code错误/error code"),
    GLOBAL_ERR_NO_AUTHORITY(-10004, "没有操作权限/No operating rights"),
    ;


    private final int code;
    private final String msg;

    private ServiceError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
