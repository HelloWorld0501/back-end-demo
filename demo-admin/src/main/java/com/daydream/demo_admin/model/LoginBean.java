package com.daydream.demo_admin.model;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/18
 * \* Time: 5:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 登录接口封装对象
 * \
 */
public class LoginBean {
    private String account;
    private String password;
    private String captcha;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}