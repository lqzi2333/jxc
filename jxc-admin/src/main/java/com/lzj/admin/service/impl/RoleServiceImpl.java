package com.lzj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzj.admin.pojo.Role;
import com.lzj.admin.mapper.RoleMapper;
import com.lzj.admin.pojo.RoleMenu;
import com.lzj.admin.quary.RoleQuery;
import com.lzj.admin.service.IRoleMenuService;
import com.lzj.admin.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzj.admin.utils.AssertUtil;
import com.lzj.admin.utils.PageResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lqc
 * @since 2023-03-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private IRoleMenuService roleMenuService;

    @Override
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        IPage<Role> page = new Page<Role>(roleQuery.getPage(),roleQuery.getLimit());
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(roleQuery.getRoleName())){
            roleQueryWrapper.like("name",roleQuery.getRoleName());
        }
        page = this.baseMapper.selectPage(page,roleQueryWrapper);
        return PageResultUtil.getResult(page.getTotal(),page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入角色名");
        Role temp = this.findRoleByRoleName(role.getName());
        AssertUtil.isTrue(null!=temp && !(temp.getId().equals(role.getId())),"角色名已经存在");
        AssertUtil.isTrue(!(this.updateById(role)),"用户更新失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()),"请输入角色名");
        AssertUtil.isTrue(null != this.findRoleByRoleName(role.getName()),"角色名已经存在");
        role.setIsDel(0);
        AssertUtil.isTrue(!(this.save(role)),"角色添加失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        AssertUtil.isTrue(null == id , "请选择要删除的角色记录");
        Role role  = this.getById(id);
        AssertUtil.isTrue(role == null,"该记录不存在");
        role.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(role)),"角色记录删除失败");
    }

    @Override
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return this.baseMapper.queryAllRoles(userId);
    }

    @Override
    public void addGrant(Integer roleId, Integer[] mids) {
        Role role = this.getById(roleId);
        AssertUtil.isTrue(role == null , "待授权的角色记录不存在!");
        int count = roleMenuService.count(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
        if (count>0){
            AssertUtil.isTrue(!(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id",roleId))),"角色授权失败");
        }
        if (null != mids && mids.length>0){
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (Integer mid : mids){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(mid);
                roleMenus.add(roleMenu);
            }
            AssertUtil.isTrue(!(roleMenuService.saveBatch(roleMenus)),"角色授权失败！");
        }
    }


    private Role findRoleByRoleName(String name) {
        return this.baseMapper.selectOne(new QueryWrapper<Role>().eq("name",name).eq("is_del",0));
    }





}
