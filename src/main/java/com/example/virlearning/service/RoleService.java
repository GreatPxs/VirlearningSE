package com.example.virlearning.service;

import com.example.virlearning.entity.Role;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;

import java.util.List;

public interface RoleService {
    List<Role> getfindNameDep(String name, String dep);
    Integer insertRole(Role role);

    Integer deleteRole(Role role);
    Integer modifyRole(Role role);
    PageResult getRolesPage(PageQueryUtil pageUtil);
}
