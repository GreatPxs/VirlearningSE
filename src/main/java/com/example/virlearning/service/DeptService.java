package com.example.virlearning.service;

import com.example.virlearning.entity.Department;

import java.util.List;

public interface DeptService {
    List<Department> getfindName(String name);

    Integer insertDept(Department dept);

    Integer deleteDept(Department dept);

    Integer modifyDept(Department dept);
}
