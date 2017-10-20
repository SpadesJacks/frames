package com.struts2.action;

import com.alibaba.dubbo.common.json.JSON;
import com.struts2.model.User;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/20 15:55
 * classType:
 */
public class HelloWorldAction {

    private String message;

    private User user = new User();

    // all struts logic here
    public String message() {
        setMessage("Hello World!!");
        user.setId(1L);
        user.setGender("男");
        user.setAge(13);
        user.setUserName("zhangsan");
        return "SUCCESS";

    }

    public String json() {
        user.setId(1L);
        user.setGender("男");
        user.setAge(13);
        user.setUserName("zhangsan");
        return "SUCCESS";

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
