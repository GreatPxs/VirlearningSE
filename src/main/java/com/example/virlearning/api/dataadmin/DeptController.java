package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Department;
import com.example.virlearning.service.DeptService;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Resource
    private DeptService deptService;
    @PostMapping ("/select")
    public Result<List<Department>> getfindName(String name) {
        List<Department> list = deptService.getfindName(name);
        if(list.isEmpty())return ResultGenerator.genFailResult("没有符合条件的科室信息");
        else {Result result = ResultGenerator.genSuccessResult();
            result.setMessage("Success");
            result.setData(list);
            return result;}
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
    @PostMapping("/getdepname")
    public Result<List<String>> getdepname(String role){
        List<String> list =deptService.getdepname(role);
        if(list.isEmpty())return ResultGenerator.genFailResult("没有符合条件的科室");
        else {Result result = ResultGenerator.genSuccessResult();
        result.setMessage("Success");
        result.setData(list);
        return result;}
    }
}
