package com.mjl.service;

import com.mjl.dao.IUserDao;
import com.mjl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by jiangdongyu on 16/11/28.
 */
//@Service("UserService") 注解用于标示此类为业务层组件,在使用时会被注解的类会自动由
//spring进行注入,无需我们创建实例
@Service("UserService")

public class UserServiceImpl implements UserService {
    //自动注入iuserdao 用于访问数据库
    @Autowired
    IUserDao Mapper;

    //登录方法的实现,从jsp页面获取username与password
    public boolean login(String username, String password) {
        //对输入账号进行查询,取出数据库中保存对信息
        User user = Mapper.selectByName(username);
        if (user != null) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;

        }
        return false;

    }

    public List<User> getAllUser() {
        return Mapper.getAllUser();
    }

    public User selectById(int id) {
        return Mapper.selectById(id);
    }

    public void addUser(User user) {
        Mapper.addUser(user);
    }

    public void udateUser(User user) {
        Mapper.updateUser(user);
    }

    public void deleteUser(int id) {
        Mapper.deleteUser(id);
    }
}