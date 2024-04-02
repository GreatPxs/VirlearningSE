package com.example.virlearning.dao;

import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Role;
import com.example.virlearning.entity.User;
import com.example.virlearning.util.PageQueryUtil;

import java.util.List;

public interface DeptMapper {
    List<Department> getfindName(String name);

    int insertDept(Department dept);

    Integer deleteDept(Department dept);

    Integer modifyDept(Department dept);

    List <String> getdepname(String role);
    Department selectbyname(String name);
    int insertfile(int id,String url);
    List<Department> getfindid(Integer id);
    List<Department> findDeptList(PageQueryUtil pageUtil);

    int getTotalDepts(PageQueryUtil pageUtil);
}
