package com.daydream.demo_backup.util;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/19
 * \* Time: 4:06
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class HttpResult {

    private int code = 200;
    private String msg;
    private Object data;

    public static HttpResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static HttpResult error(String msg) {
        return error(500, msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(String msg) {
        HttpResult r = new HttpResult();
        r.setMsg(msg);
        return r;
    }

    public static HttpResult ok(Object data) {
        HttpResult r = new HttpResult();
        r.setData(data);
        return r;
    }

    public static HttpResult ok() {
        return new HttpResult();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}