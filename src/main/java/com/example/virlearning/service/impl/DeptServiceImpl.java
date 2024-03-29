package com.example.virlearning.service.impl;

import com.example.virlearning.dao.DeptMapper;
import com.example.virlearning.entity.Department;
import com.example.virlearning.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptDao;
    public List<Department> getfindName(String name){return deptDao.getfindName(name);}

    public Integer insertDept(Department dept){return deptDao.insertDept(dept);}

    public Integer deleteDept(Department dept){return deptDao.deleteDept(dept);}

    public Integer modifyDept(Department dept){return deptDao.modifyDept(dept);}
}
