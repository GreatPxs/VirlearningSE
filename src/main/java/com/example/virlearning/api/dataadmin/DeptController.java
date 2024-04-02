package com.example.virlearning.api.dataadmin;

import ch.qos.logback.classic.Logger;
import com.example.virlearning.api.sysadmin.AdminRegisteUserAPI;
import com.example.virlearning.common.ServiceResultEnum;
import com.example.virlearning.config.annotation.TokenToAdminUser;
import com.example.virlearning.entity.AdminUserToken;
import com.example.virlearning.entity.Department;
import com.example.virlearning.service.DeptService;
import com.example.virlearning.service.FileService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;
    @Autowired
    private FileService fileService;
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
    public Result insertDept(Department dept) {
        String insertDeptResult = deptService.insertDept(dept);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertDeptResult)) {
            return ResultGenerator.genSuccessResult();
        }

        return ResultGenerator.genFailResult(insertDeptResult);
    }
    @PostMapping("/file")
    public Result insertFile(@RequestParam("id :") Integer id ,MultipartFile file) throws IOException {

        // 获取文件的输入流
        InputStream inputStream = file.getInputStream();
        // 生成文件名
        String filename = UUID.randomUUID().toString() + file.getOriginalFilename();
        // 调用文件上传方法
        String fileUrl = fileService.uploadFile(filename, inputStream);
        String insertFileResult = deptService.insertfile(id,fileUrl);
        if (ServiceResultEnum.SUCCESS.getResult().equals(insertFileResult)) {
            Result result=ResultGenerator.genSuccessResult();
            result.setData(fileUrl);
            return result;
        }
        return ResultGenerator.genFailResult(insertFileResult);

    }
    /**
     * 列表
     */
    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    @Operation(summary = "科室列表")
    public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                       @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(deptService.getDeptsPage(pageUtil));
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
