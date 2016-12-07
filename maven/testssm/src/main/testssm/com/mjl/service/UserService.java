package com.mjl.service;

import com.mjl.model.User;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by jiangdongyu on 16/11/28.
 */
public interface UserService {
    public boolean login(String username,String password);
    public List<User> getAllUser();
    public User selectById(int id);
    public void addUser(User user);
    public void udateUser(User user);
    public void deleteUser(int id);

}
