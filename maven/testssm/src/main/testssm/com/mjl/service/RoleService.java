package com.mjl.service;

import com.mjl.model.Role;

import java.util.List;

/**
 * Created by jiangdongyu on 2016/12/4.
 */
public interface RoleService {
    public Role selectById(int id);
    public List<Role> getAllRole();
    public void addRole(Role role);
    public void updateRole(Role role);
    public void deleteRole(int id);
}
