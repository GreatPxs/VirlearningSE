package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Device;
import com.example.virlearning.service.DeviceService;
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
@RequestMapping("/device")
public class DeviceApi {
    @Resource
    private DeviceService DeviceService;
    @PostMapping("/select")
    public ResponseResult<List<Device>> getfindNameDep(String name, String dep) {
        List<Device> list = DeviceService.getfindNameDev(name,dep);
        return new ResponseResult<List<Device>>(200,list);

    }
    @PostMapping("/add")
    public ResponseResult<Void> insertDevice(Device Device) {
        DeviceService.insertDevice(Device);
        return new ResponseResult<Void>(200);
    }

    @GetMapping("/delete")
    public ResponseResult<Void> deleteDevice(Integer id,Integer isdeleted) {
        DeviceService.deleteDevice(id,isdeleted);
        System.out.println("delete方法执行");
        return new ResponseResult<Void>(200);
    }
    @PostMapping("/update")
    public Result modifyDevice(Device Device) {
        boolean flag=DeviceService.modifyDevice(Device);
        if (flag) {
            //返回成功
            Result result = ResultGenerator.genSuccessResult();
            return result;
        } else {
            //返回失败
            Result result = ResultGenerator.genFailResult("修改失败或未修改");
            return result;
        }

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
        return ResultGenerator.genSuccessResult(DeviceService.getDevicesPage(pageUtil));
    }
}
