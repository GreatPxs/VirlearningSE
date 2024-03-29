package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Role;
import com.example.virlearning.service.RoleService;
import com.example.virlearning.util.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
