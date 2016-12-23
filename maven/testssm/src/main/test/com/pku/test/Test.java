package com.pku.test;

import com.pku.dao.IRoleDao;
import com.pku.dao.IUserDao;


import com.pku.model.Role;
import com.pku.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jiangdongyu on 16/11/28.
 */
public class Test {
   private static ApplicationContext ac;
    static {
        ac = new ClassPathXmlApplicationContext("config/applicationContext.xml");
    }

    public static void main(String[] args) {
        IUserDao mapperu = (IUserDao) ac.getBean("IUserDao");
        User user = new User();
        List<User> userList = new ArrayList<User>();

//        System.out.println("查询单条记录");
//        user = mapperu.selectByName("jdy");
//        System.out.println("username:"+user.getUsername()+" password:"+user.getPassword());

//        System.out.println("增加记录");
//        user.setId(3);
//        user.setUsername("xixi");
//        user.setPassword("xixi");
//        user.setR_id(0);
//        mapper.addUser(user);
//
//        user.setId(4);
//        user.setUsername("bat");
//        user.setPassword("bat");
//        user.setR_id(2);
//        mapper.addUser(user);
//        System.out.println("添加成功");

        System.out.println("查询多条记录");
        userList = mapperu.getAllUser();
        System.out.println(userList.toString());
        for(User user2 :userList)
        {
            System.out.println(user2.getId()+"--"+user2.getUsername()+"--"+user2.getRole().getR_name());

        }
//
//        //修改数据
//        user.setId(2);
//        user.setPassword("802");
//        mapperu.updateUser(user);
//        System.out.println("修改成功");
//
//        //删除数据
//        mapperu.deleteUser(2);
//        System.out.println("删除成功");
//
//        System.out.println("查询多条记录");
//        userList = mapperu.getAllUser();
//        for(int i = 0;i<userList.size();i++){
//            System.out.println("username:"+userList.get(i).getUsername()+" password:"+user.getPassword());
//        }

//        IRoleDao mapperr = (IRoleDao) ac.getBean("IRoleDao");
//        Role role = new Role();
//        List<Role> roleList = new ArrayList<Role>();

//        System.out.println("增加记录");
//        role.setR_id(0);
//        role.setR_name("ssss");
//        mapperr.addRole(role);

//        role.setR_id(1);
//        role.setR_name("ss");
//        mapperr.addRole(role);
//
//        role.setR_id(2);
//        role.setR_name("t");
//        mapperr.addRole(role);
//
//        role.setR_id(3);
//        role.setR_name("s");
//        mapperr.addRole(role);
//        System.out.println("添加成功");

//        System.out.println("查询单条记录");
//        role = mapperr.selectById(2);
//        System.out.println("rolename:"+role.getR_name());
//
//        System.out.println("查询多条记录");
//        roleList = mapperr.getAllRole();
//        for(int i = 0;i<roleList.size();i++){
//            System.out.println("rolename:"+roleList.get(i).getR_name());
//        }
//
//        //修改数据
//        Role role2 = mapperr.selectById(0);
//        role2.setR_name("student");
//        mapperr.updateRole(role2);
//        System.out.println("修改成功");
//
//        System.out.println("查询多条记录");
//        roleList = mapperr.getAllRole();
//        for(int i = 0;i<roleList.size();i++){
//            System.out.println("rolename:"+roleList.get(i).getR_name());
//        }
//
//        //删除数据
//        mapperr.deleteRole(3);
//        System.out.println("删除成功");
//
//        System.out.println("查询多条记录");
//        roleList = mapperr.getAllRole();
//        for(int i = 0;i<roleList.size();i++){
//            System.out.println("rolename:"+roleList.get(i).getR_name());
//        }

    }
}
