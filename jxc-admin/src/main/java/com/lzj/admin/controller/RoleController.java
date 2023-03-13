package com.lzj.admin.controller;


import com.lzj.admin.model.RespBean;
import com.lzj.admin.pojo.Role;
import com.lzj.admin.quary.RoleQuery;
import com.lzj.admin.service.IRoleService;
import io.swagger.models.auth.In;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author lqc
 * @since 2023-03-13
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    @RequestMapping("index")
    public  String index(){
        return "role/role";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String , Object> roleList(RoleQuery roleQuery){
        return roleService.roleList(roleQuery);
    }


    @RequestMapping("addOrUpdateRolePage")
    public  String addOrUpdateRolePage(Integer id , Model model){
        if (null != id){
            model.addAttribute("role",roleService.getById(id));
        }
        return  "role/add_update";
    }


    @RequestMapping("save")
    @ResponseBody
    public RespBean save(Role role){
        roleService.save(role);
        return  RespBean.success("角色添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public  RespBean update(Role role){

        roleService.updateRole(role);

        return RespBean.success("角色信息更新成功");
    }

//    @RequestMapping("save")
//    @ResponseBody
//    public RespBean saveRole(Role role){
//        roleService.saveRole(role);
//        return RespBean.success("角色添加成功");
//    }

    @RequestMapping("delete")
    @ResponseBody
    public RespBean deleteRole(Integer id){
        roleService.deleteRole(id);
        return RespBean.success("角色记录删除成功");
    }

    @RequestMapping("queryAllRoles")
    public List<Map<String , Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }

    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer roleId,Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }


    @RequestMapping("addGrant")
    @ResponseBody
    public RespBean addGrant(Integer roleId , Integer[] mids){
        roleService.addGrant(roleId,mids);
        return RespBean.success("角色记录授权成功");
    }

}
