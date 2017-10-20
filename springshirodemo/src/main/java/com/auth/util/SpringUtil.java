package com.auth.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/18 15:52
 * classType:
 */
public class SpringUtil {

    private static ApplicationContext ctx =new ClassPathXmlApplicationContext("springmvc.xml");


    public static Object getBean(String beanId)
    {
        return ctx.getBean(beanId);
    }
}
