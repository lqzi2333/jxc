package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.User;
import com.lzj.admin.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author 老李
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public RespBean login(String userName,String password,HttpSession session){
        try {
            User user = userService.login(userName, password);
            session.setAttribute("user",user);
            return RespBean.success("当前用户登陆成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("用户登录失败");
        }
    }

    @RequestMapping("setting")
    public String setting(HttpSession session){
        User user = (User) session.getAttribute("user");
        session.setAttribute("user",userService.getById(user.getId()));
        return "user/setting";
    }


    @RequestMapping("updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(User user){

        try {
            userService.updateUserInfo(user);
            return RespBean.success("当前用户更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("用户更新失败");
        }
    }


    @RequestMapping("password")
    public String password(){

        return "user/password";
    }

    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(HttpSession session , String oldPassword , String newPassword ,String confirmPassword){
        try {
            User user = (User) session.getAttribute("user");
            userService.updateUserPassword(user.getUserName(),oldPassword,newPassword,confirmPassword);
            return RespBean.success("当前用户密码更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e){
            e.printStackTrace();
            return RespBean.error("用户密码更新失败");
        }
    }

}
