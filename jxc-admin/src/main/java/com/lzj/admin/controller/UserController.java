package com.lzj.admin.controller;


import com.lzj.admin.exceptions.ParamsException;
import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.User;
import com.lzj.admin.quary.UserQuery;
import com.lzj.admin.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Priority;
import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;

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
    public RespBean updateUserPassword(Principal principal, String oldPassword , String newPassword , String confirmPassword){
            userService.updateUserPassword(principal.getName(),oldPassword,newPassword,confirmPassword);
            return RespBean.success("当前用户密码更新成功");
        }
    @RequestMapping("/index")
    public String index(){
        return  "user/user";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> userList(UserQuery userQuery){
        return userService.userList(userQuery);
    }

    /**
     * 添加|更新用户页
     * @param id
     * @param model
     * @return
     */

    @RequestMapping("addOrUpdateUserPage")
    public  String addOrUpdatePage(Integer id , Model model){
        if (null != id ){
            model.addAttribute("user",userService.getById(id));
        }

        return "user/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public  RespBean saveUser(User user){
        userService.save(user);
        return  RespBean.success("用户记录添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public RespBean updateUser(User user){
        userService.updateById(user);
        return RespBean.success("用户记录更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteUser(Integer[] ids){
        userService.deleteUser(ids);
        return  RespBean.success("用户记录删除成功");
    }
}
