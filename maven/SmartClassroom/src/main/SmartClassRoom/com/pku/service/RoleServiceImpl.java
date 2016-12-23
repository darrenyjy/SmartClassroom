package com.pku.service;

import com.pku.dao.IRoleDao;
import com.pku.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangdongyu on 2016/12/4.
 */
@Service("RoleService")
public class RoleServiceImpl implements RoleService {
    //自动注入iuserdao 用于访问数据库
    @Autowired
    IRoleDao Mapper;
    public Role selectById(int id){
        return Mapper.selectById(id);
    }

    public List<Role> getAllRole(){
        return Mapper.getAllRole();
    }

    public void addRole(Role role){
        Mapper.addRole(role);
    }

    public void updateRole(Role role){
        Mapper.updateRole(role);
    }

    public void deleteRole(int id){
        Mapper.deleteRole(id);
    }

}
