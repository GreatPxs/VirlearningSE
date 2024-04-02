package com.example.virlearning.service.impl;

import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.dao.DeptMapper;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.User;
import com.example.virlearning.service.DeptService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptDao;
    public List<Department> getfindName(String name){return deptDao.getfindName(name);}

    public String insertDept(Department dept){
        if (!deptDao.getfindName(dept.getName()) .isEmpty()) {
            return ServiceResultEnum.SAME_DEPT_NAME_EXIST.getResult();
        }
        deptDao.insertDept(dept);
        return ServiceResultEnum.SUCCESS.getResult();}
    public String insertfile(Integer id,String url){
        if (deptDao.getfindid(id) .isEmpty()) {
            return ServiceResultEnum.Empty_DEPT_ERROR.getResult();
        }
        deptDao.insertfile(id, url);
        return ServiceResultEnum.SUCCESS.getResult();}

    public Integer deleteDept(Department dept){return deptDao.deleteDept(dept);}

    public Integer modifyDept(Department dept){return deptDao.modifyDept(dept);}
    /*根据职能名搜索对应的科室

     */
    public List<String> getdepname(String role){
    return deptDao.getdepname(role);
    }
    public PageResult getDeptsPage(PageQueryUtil pageUtil) {
        List<Department> Dept = deptDao.findDeptList(pageUtil);
        int total = deptDao.getTotalDepts(pageUtil);
        PageResult pageResult = new PageResult(Dept, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
