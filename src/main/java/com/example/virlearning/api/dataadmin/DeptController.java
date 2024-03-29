package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Department;
import com.example.virlearning.service.DeptService;
import com.example.virlearning.util.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;
    @GetMapping("/select")
    public ResponseResult<List<Department>> getfindName(String name) {
        List<Department> list = deptService.getfindName(name);
        return new ResponseResult<List<Department>>(200,list);
    }
    @GetMapping("/insert")
    public ResponseResult<Void> insertDept(Department dept) {
        deptService.insertDept(dept);
        return new ResponseResult<Void>(200);
    }
    @GetMapping("/delete")
    public ResponseResult<Void> deleteDept(Department dept) {
        deptService.deleteDept(dept);
        return new ResponseResult<Void>(200);
    }
    @GetMapping("/update")
    public ResponseResult<Void> modifyDept(Department dept) {
        deptService.modifyDept(dept);
        return new ResponseResult<Void>(200);
    }

}
