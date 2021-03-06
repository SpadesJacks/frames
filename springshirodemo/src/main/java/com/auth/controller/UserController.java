package com.auth.controller;

import com.auth.entity.User;
import com.auth.util.VerifyCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/18 15:49
 * classType:
 */
@Controller
@RequestMapping("/member")
public class UserController {
    //登录
    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * VerifyCodeUtil.java 类实现验证码
     */

    /**
     * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
     */
    @RequestMapping("/getVerifyCodeImage")
    public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置页面不缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_ALL_MIXED, 4, null);
        //将验证码放到HttpSession里面
        request.getSession().setAttribute("verifyCode", verifyCode);
        System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
        //设置输出的内容的类型为JPEG图像
        response.setContentType("image/jpeg");
        BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 25, 4, true, Color.WHITE, Color.BLACK, null);
        //写给浏览器
        ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
    }

    /**
     * @param request
     * @return 登录验证
     */
    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //返回地址
        String returnUrl = "/login";

        //获取HttpSession验证码
        String verifyCode = (String) request.getSession().getAttribute("verifyCode");
        //获取用户输入的验证码
        String submitCode = WebUtils.getCleanParam(request, "verifyCode");

        System.out.println("用户输入的验证码是：" + submitCode + ";系统生成的验证码是：" + verifyCode);

//        if (StringUtils.isEmpty(submitCode) || !StringUtils.equalsIgnoreCase(verifyCode, submitCode)) {
//            request.setAttribute("login_msg", "验证码错误");
//            return returnUrl;
//        }

        //根据获取的用户名和密码封装成Token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //是否记住用户
        token.setRememberMe(true);
        //获取当前的subject
        Subject subject = SecurityUtils.getSubject();

        try {
            System.out.println("对用户：[" + username + "]进行登录验证,验证开始...");
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            subject.login(token);
            System.out.println("对用户：[" + username + "]进行登录验证,验证通过!");
            returnUrl = "/main";

        } catch (UnknownAccountException e) {

            System.out.println("对用户：[" + username + "]进行登录验证,验证未通过!错误：未知账号");
            request.setAttribute("login_msg", "未知账号");

        } catch (IncorrectCredentialsException e) {
            System.out.println("对用户：[" + username + "]进行登录验证,验证未通过!错误：密码错误");
            request.setAttribute("login_msg", "密码错误");

        } catch (LockedAccountException e) {
            System.out.println("对用户：[" + username + "]进行登录验证,验证未通过!错误：账号被锁定");
            request.setAttribute("login_msg", "账号被锁定");

        } catch (ExcessiveAttemptsException e) {
            System.out.println("对用户：[" + username + "]进行登录验证,验证未通过!错误：错误次数过多");
            request.setAttribute("login_msg", "密码或用户名输入错误次数过多");

        } catch (AuthenticationException e) {
            System.out.println("对用户：[" + username + "]进行登录验证,验证未通过!错误：用户名或密码不正确");
            request.setAttribute("login_msg", "身份认证失败，用户名或密码不正确");
        }

        //验证是否登录成功
        if (subject.isAuthenticated()) {
            System.out.println("用户：[" + username + "]进行登录验证通过");
        } else {
            token.clear();
        }
        return returnUrl;
    }


    /**
     * @param request
     * @return 注销登录
     */

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityUtils.getSubject().logout();
        return "/login";
    }

    @RequestMapping("/queryMyUserInfo")
    public String queryUserInfo(HttpServletRequest request) {
        //从Session中取得当前用户信息
        User currentUser = (User) request.getSession().getAttribute("currentUser");

        request.setAttribute("user", currentUser);

        return "/myinfo";
    }
}
