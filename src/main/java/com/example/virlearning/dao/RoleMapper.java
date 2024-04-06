package com.example.virlearning.dao;

import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Role;
import com.example.virlearning.util.PageQueryUtil;

import java.util.List;

public interface RoleMapper {
    List<Role> getfindNameDep(String name, String dep);

    Integer insertRole(Role role);

    Integer deleteRole(Role role);

    Integer modifyRole(Role role);
    List<Role> findRoleList(PageQueryUtil pageUtil);

    int getTotalRoles(PageQueryUtil pageUtil);
}
