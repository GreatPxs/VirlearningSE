package com.example.virlearning.service;

import com.example.virlearning.entity.Department;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;

import java.util.List;

public interface DeptService {
    List<Department> getfindName(String name);


    String insertDept(Department dept);

    Integer deleteDept(Department dept);

    Integer modifyDept(Department dept);
    List<Department> getdepname(String role);
    String insertfile(Integer id,String url);
    PageResult getDeptsPage(PageQueryUtil pageUtil);
}
