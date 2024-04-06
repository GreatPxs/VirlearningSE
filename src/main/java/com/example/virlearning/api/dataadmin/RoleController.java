package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Role;
import com.example.virlearning.service.RoleService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @GetMapping("/select")
    public ResponseResult<List<Role>> getfindNameDep(String name,String dep) {
        List<Role> list = roleService.getfindNameDep(name,dep);
        return new ResponseResult<List<Role>>(200,list);

    }
    @GetMapping("/add")
    public ResponseResult<Void> insertRole(Role role) {
        roleService.insertRole(role);
        return new ResponseResult<Void>(200);
    }

    @GetMapping("/delete")
    public ResponseResult<Void> deleteRole(Role role) {
        roleService.deleteRole(role);
        System.out.println("delete方法执行");
        return new ResponseResult<Void>(200);
    }
    @GetMapping("/update")
    public ResponseResult<Void> modifyRole(Role role) {
        roleService.modifyRole(role);
        return new ResponseResult<Void>(200);
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
        return ResultGenerator.genSuccessResult(roleService.getRolesPage(pageUtil));
    }
}
