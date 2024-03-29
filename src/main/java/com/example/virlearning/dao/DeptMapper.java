package com.example.virlearning.dao;

import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Role;

import java.util.List;

public interface DeptMapper {
    List<Department> getfindName(String name);

    Integer insertDept(Department dept);

    Integer deleteDept(Department dept);

    Integer modifyDept(Department dept);
    
}
