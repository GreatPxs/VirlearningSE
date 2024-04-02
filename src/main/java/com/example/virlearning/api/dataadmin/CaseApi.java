package com.example.virlearning.api.dataadmin;

import ch.qos.logback.classic.Logger;
import com.example.virlearning.api.sysadmin.AdminRegisteUserAPI;
import com.example.virlearning.entity.Case;
import com.example.virlearning.service.CaseService;

import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Case")
public class CaseApi {
    @Autowired    //自动装配
    private CaseService caseService;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminRegisteUserAPI.class);
    /**
     * 添加病例信息
     *
     * @param record
     * @return 返回成功
     */
    @RequestMapping("/addCase")
    public ResponseResult<Void> addCase(Case record) {

        caseService.addCase(record);
        return new ResponseResult<Void>(200);
    }
    /**
     * 根据uid查询病例全部数据
     * @param id
     * @return
     */
    @RequestMapping("/findId")
    public ResponseResult<Case> getfindId(Integer id){
        Case data = caseService.findId(id);
        return new ResponseResult<Case>(200,data);
    };

    /**
     * 修改病例数据
     *
     * @param record
     * @return
     */
    @RequestMapping("/updateIdCase")
    public Result updateIdDrug(Case record) {

        caseService.updateIdCase(record);
        return ResultGenerator.genSuccessResult();
    }

}