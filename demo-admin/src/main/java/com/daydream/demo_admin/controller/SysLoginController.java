package com.daydream.demo_admin.controller;

import com.daydream.demo_admin.vo.LoginBean;
import com.daydream.demo_admin.model.SysUser;
import com.daydream.demo_admin.security.JwtAuthenticatioToken;
import com.daydream.demo_admin.service.SysUserService;
import com.daydream.demo_admin.util.ErrorCode;
import com.daydream.demo_admin.util.PasswordUtils;
import com.daydream.demo_admin.util.SecurityUtils;
import com.daydream.demo_common.utils.IOUtils;
import com.daydream.demo_core.http.HttpResult;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 0839d
 * \* Date: 2019/11/7
 * \* Time: 22:23
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class SysLoginController {
    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws ServletException, IOException {
        httpServletResponse.setHeader("Cache-Control", "no-store,no-cache");
        httpServletResponse.setContentType("image/jpeg");
        //文字验证码
//        String text = producer.createText();
        String text = "11111";
        //图片验证码
        BufferedImage bufferedImage = producer.createImage(text);
        //保存验证码到Session
        httpServletRequest.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ImageIO.write(bufferedImage, "jpg", servletOutputStream);
        IOUtils.closeQuietly(servletOutputStream);
    }

    @PostMapping(value = "/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest httpServletRequest) throws IOException {
        String userName = loginBean.getAccount();
        String passWord = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        Object kaptcha = httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (kaptcha == null) {
            return HttpResult.error(ErrorCode.CAPTCHA_EXPIRED);
        }
        if (!captcha.equals(kaptcha)) {
            return HttpResult.error(ErrorCode.CAPTCHA_ERROR);
        }
        // 用户信息
        SysUser user = sysUserService.findByName(userName);
        // 账号不存在、密码错误
        if (user == null) {
            return HttpResult.error(ErrorCode.USER_NOT_EXITS);
        }
        if (!PasswordUtils.matches(user.getSalt(), passWord, user.getPassword())) {
            return HttpResult.error(ErrorCode.PASSWORD_ERROR);
        }
        // 账号锁定
        if (user.getStatus() == 0) {
            return HttpResult.error(ErrorCode.USER_LOCKED);
        }
        // 系统登录认证
        JwtAuthenticatioToken token = SecurityUtils.login(httpServletRequest, userName, passWord, authenticationManager);
        return HttpResult.ok(token);
    }
}