package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.redis.RedisCache;
import com.example.virlearning.service.PaperService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.virlearning.common.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Resource
    PaperService paperService;
    @Autowired
    private RedisCache redisCache;

    @GetMapping("/select")
    public ResponseResult<List<Paper>> getfindbyName(String name) {
        List<Paper> list = paperService.getfindbyName(name);
        return new ResponseResult<List<Paper>>(200, list);
    }
    @PutMapping("/add")
    public ResponseResult<Void> insertPaper(Paper paper) {
        paperService.insertPaper(paper);
        return new ResponseResult<>(200);
    }
    @GetMapping("/delete")
    public ResponseResult<Integer> deletePaper(Paper paper) {
        int cnt = paperService.deletePaper(paper);
        return new ResponseResult<>(200,cnt);
    }
    @GetMapping("/modify")
    public ResponseResult<Void> modifyPaper(Paper paper) {
        paperService.modifyPaper(paper);
        return new ResponseResult<>(200);
    }
    @GetMapping("/getPaperInf")
    public ResponseResult<List<Question>> getPaperInf(Paper paper) {
        //构造key
        String key = Constants.PAPER_REDIS_KEY + paper.getPaperId();
        //查询redis是否存在
        List<Question> list = (List<Question>) redisCache.getCacheObject(key);
        //如果存在，直接返回
        if(list != null && list.size() > 0){
            return new ResponseResult<List<Question>>(200,list);
        }
        //否则添加到缓存再返回
        list = paperService.getPaperInf(paper);
        redisCache.setCacheObject(key, list, 24, TimeUnit.HOURS);
        return new ResponseResult<List<Question>>(200,list);
    }
    @GetMapping("/insertPaperQuestion")
    public ResponseResult<Void> insertPaperQuestion(Paper paper,Question question,Integer pqScore) {
        if(pqScore == null)return new ResponseResult<>(500);
        paperService.insertPaperQuestion(paper,question,pqScore);
        return new ResponseResult<>(200);
    }
    @GetMapping("/deletePaperQuestion")
    public ResponseResult<Void> deletePaperQuestion(Paper paper,Question question) {
        paperService.deletePaperQuestion(paper,question);
        return new ResponseResult<>(200);
    }

    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    @Operation(summary = "试卷列表")
    public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                       @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(paperService.getPaperPage(pageUtil));
    }

    @RequestMapping(value = "/paperQuestionList", method = RequestMethod.GET)
    @Operation(summary = "试卷中已选题目列表")
    public Result paperQuestionList(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                                    @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,
                                    Integer paperId,String type,String description) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(paperService.getPaperQuestionPage(pageUtil,paperId,type,description));
    }

    @RequestMapping(value = "/nopaperQuestionList", method = RequestMethod.GET)
    @Operation(summary = "试卷中未选（剩余）题目列表")
    public Result noPaperQuestionList(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                                      @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,
                                      Integer paperId,String type,String description) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(paperService.getNoPaperQuestionPage(pageUtil,paperId,type,description));
    }
}