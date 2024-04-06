package com.example.virlearning.service.impl;

import com.example.virlearning.dao.RoleMapper;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Role;
import com.example.virlearning.service.RoleService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleDao;

    public List<Role> getfindNameDep(String name, String dep){
        return roleDao.getfindNameDep(name,dep);
    }
    public Integer insertRole(Role role) {return roleDao.insertRole(role);}
    public Integer deleteRole(Role role) {return roleDao.deleteRole(role);}
    public Integer modifyRole(Role role) {return roleDao.modifyRole(role);}
    public PageResult getRolesPage(PageQueryUtil pageUtil) {
        List<Role> Dept = roleDao.findRoleList(pageUtil);
        int total = roleDao.getTotalRoles(pageUtil);
        PageResult pageResult = new PageResult(Dept, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
