package com.pku.dao;

import com.pku.model.Role;

import java.util.List;

/**
 * Created by jiangdongyu on 2016/12/4.
 */
public interface IRoleDao {
    //这里以接口形式定义了数据库操作方法,我们只需
    // 在Mybatis映射文件中对其进行映射就可以直接使用
    public Role selectById(int id);
    public List<Role> getAllRole();
    public void addRole(Role role);
    public void updateRole(Role role);
    public void deleteRole(int id);
}
