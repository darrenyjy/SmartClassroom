package com.pku.controller;

import com.pku.model.User;
import com.pku.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by jiangdongyu on 16/11/28.
 */

//@Controller注解用于标示本类为web层控制组件
@Controller
//@RequestMapping("/user")用于标定访问时对url位置
//@RequestMapping("/user")
//在默认情况下springmvc的实例都是单例模式,所以使用scope域将其注解为每次都创建一个新的实例
@Scope("prototype")
public class UserController {
    //自动注入业务层的userService类
    @Autowired
        UserService userService;

    //login业务的访问位置为/user/login
    @RequestMapping("/login")
       public @ResponseBody String login(User user, HttpServletRequest request){
        //调用login方法来验证是否是注册用户
        boolean loginType = userService.login(user.getUsername(),user.getPassword());
        JSONObject jsonObject = new JSONObject();
        if(loginType){
            //如果验证通过,则将用户信息传到前台
            request.setAttribute("user",user);
            //并跳转到success.jsp页面
            //return "success";
            jsonObject.put("msg","0");
        }else{
            //若不对,则将错误信息显示到错误页面
            request.setAttribute("message","用户名密码错误");
            //return "error";
            jsonObject.put("msg","-1");
        }
        return jsonObject.toString();
    }

    @RequestMapping(value="/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("homePage");
        return mv;
    }

//    @RequestMapping(value="/userManage")
//    public ModelAndView userManage(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("userManage");
//        return mv;
//    }

    @RequestMapping(value="/roleManage")
    public ModelAndView roleManage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("roleManage");
        return mv;
    }



    /**
     * 查找所有用户
     * @return
     */
    @RequestMapping(value="/userManage")
    public ModelAndView getAllUser(){
        ModelAndView mv= new ModelAndView();
        List<User> list= userService.getAllUser();
        mv.addObject("list",list);
        mv.setViewName("userManage");
        return mv;
    }
}
