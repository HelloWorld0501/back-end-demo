package com.daydream.demo_core.http;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/9/13
 * \* Time: 6:26
 * \* To change this template use File | Settings | File Templates.
 * \* Description: Http结果封装
 * \
 */
public class HttpResult {
    private int code = 200;

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

    private String msg;
    private Object data;

    public static HttpResult error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知错误!");
    }

    public static HttpResult error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(code);
        httpResult.setMsg(msg);
        return httpResult;
    }

    public static HttpResult ok(String msg) {
        HttpResult httpResult = new HttpResult();
        httpResult.setMsg(msg);
        return httpResult;
    }

    public static HttpResult ok(Object data) {
        HttpResult httpResult = new HttpResult();
        httpResult.setData(data);
        return httpResult;
    }

    public static HttpResult ok() {
        return new HttpResult();
    }
}