package com.auth.controller;

import com.auth.entity.User;
import com.auth.service.api.UserService;
import com.auth.service.imp.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/18 15:51
 * classType:
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService =new UserServiceImpl();


    @RequestMapping("/queryAllUserInfo")
    public String queryAllUserInfo(HttpServletRequest request)
    {
        List<User> userList = userService.queryAllUserInfo();

        request.setAttribute("userList", userList);

        return "/admin";
    }
}
