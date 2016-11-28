package com.mjl.test;

import com.mjl.dao.IUserDao;


import com.mjl.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by jiangdongyu on 16/11/28.
 */
public class Test {
   private static ApplicationContext ac;
    static {
        ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
    }

    public static void main(String[] args) {
        IUserDao mapper = (IUserDao) ac.getBean("IUserDao");
        System.out.println("获取jdy");
        User user = mapper.selectByName("jdy");

        System.out.println(user.getId()+":"+"username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());

    }
}
