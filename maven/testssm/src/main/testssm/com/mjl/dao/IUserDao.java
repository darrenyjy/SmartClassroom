package com.mjl.dao;

import com.mjl.model.User;

import java.util.List;

/**
 * Created by jiangdongyu on 16/11/28.
 * 此类为接口模式下的配置
 */

public interface IUserDao {
    //这里以接口形式定义了数据库操作方法,我们只需
    // 在Mybatis映射文件中对其进行映射就可以直接使用
    public User selectById(int id);
    public User selectByName(String username);
    public List<User> getAllUser();
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(int id);
}
