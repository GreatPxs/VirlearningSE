package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Case;
import com.example.virlearning.service.CaseService;

import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Case")
public class CaseApi {
    @Autowired    //自动装配
    private CaseService caseService;

    /**
     * 添加数据。药品类别信息
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
     * 根据uid查询药品全部数据
     * @param id
     * @return
     */
    @RequestMapping("/findId")
    public ResponseResult<Case> getfindId(Integer id){
        Case data = caseService.findId(id);
        return new ResponseResult<Case>(200,data);
    };

    /**
     * 修改药品数据
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