package com.example.virlearning.service;

import com.example.virlearning.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getfindNameDep(String name, String dep);
    Integer insertRole(Role role);

    Integer deleteRole(Role role);
    Integer modifyRole(Role role);
}
