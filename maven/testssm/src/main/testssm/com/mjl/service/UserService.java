package com.mjl.service;

import org.springframework.ui.Model;

/**
 * Created by jiangdongyu on 16/11/28.
 */
public interface UserService {
    public boolean login(String username,String password);
}
