package com.example.virlearning.api.dataadmin;
import com.example.virlearning.common.Constants;
import com.example.virlearning.config.annotation.TokenToUser;
import com.example.virlearning.entity.Exam;
import com.example.virlearning.entity.User;
import com.example.virlearning.service.ExamService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.ResponseResult;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import com.example.virlearning.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Resource
    ExamService examService;
//    @Autowired
//    private RedisCache redisCache;

    @GetMapping("/select")
    public ResponseResult<List<Exam>> getfindbyName(String name) {
        List<Exam> list = examService.getfindbyName(name);
        return new ResponseResult<>(200,list);
    }
    @GetMapping("/insert")
    public ResponseResult<Void> insertExam(Exam exam) {
        examService.insertExam(exam);
        return new ResponseResult<>(200);
    }
    @GetMapping("/delete")
    public ResponseResult<Void> deleteExam(Exam exam) {
        examService.deleteExam(exam);
        return new ResponseResult<>(200);
    }
    @GetMapping("/modify")
    public ResponseResult<Void> modifyExam(Exam exam) {
        examService.modifyExam(exam);
        return new ResponseResult<>(200);
    }
    @GetMapping("/getExamUser")
    public ResponseResult<List<User>> getExamUser(Exam exam) {
        List<User> list = examService.getExamUser(exam);
        return new ResponseResult<>(200,list);
    }
    @PostMapping("/insertExamUser")
    public ResponseResult<Void> insertExamUser(Exam exam,User user) {
        examService.insertExamUser(exam,user);
        return new ResponseResult<>(200);
    }
    @DeleteMapping("/deleteExamUser")
    public ResponseResult<Void> deleteExamUser(Exam exam,User user) {
        examService.deleteExamUser(exam,user);
        return new ResponseResult<>(200);
    }
    //考试提交后，计算得分
    @PostMapping("/calculateExamScore")
    public ResponseResult<Integer> calculateExamScore(Exam exam,String userAnswer,String time,@TokenToUser @Parameter(hidden = true) User user) {
        Integer score = examService.calculateExamScore(exam,user,userAnswer);
//        redisCache.setCacheObject(Constants.VIRLESRNING_SESSION_KEY + user.getUserId(), score, 24, TimeUnit.HOURS);
        examService.updateUserAnswerScore(exam,user,userAnswer,score);
//        examService.updateUserScore(exam,user,score);
        examService.updateEndExamTime(exam,user,time);
        return new ResponseResult<Integer>(200,score);
    }
//    //将用户提交的答案存入
//    @PutMapping("/updateUserAnswer")
//    public ResponseResult<Void> updateUserAnswer(Exam exam,User user,String userAnswer) {
//        examService.updateUserAnswer(exam,user,userAnswer,score);
//        return new ResponseResult<>(200);
//    }
//    //将计算出的用户分数存入
//    @PutMapping("/updateUserScore")
//    public ResponseResult<Void> updateUserScore(Exam exam,User user,Integer score) {
//        examService.updateUserScore(exam,user,score);
//        return new ResponseResult<>(200);
//    }
    @PutMapping("/updateStartExamTime")
    public ResponseResult<Void> updateStartExamTime(Exam exam,User user,String time) {
        examService.updateStartExamTime(exam,user,time);
        return new ResponseResult<>(200);
    }
    @GetMapping("/getExamLimitTime")
    public ResponseResult<Integer> getExamLimitTime(Exam exam) {
        Integer time = examService.getExamLimitTime(exam);
        return new ResponseResult<Integer>(200,time);
    }
    //根据用户查历史考试
    @GetMapping("/getUserExam")
    public ResponseResult<List<Exam>> getUserExam(User user) {
        List<Exam> list = examService.getUserExam(user);
        return new ResponseResult<>(200,list);
    }
    //查用户一个考试的分数
    @GetMapping("/getExamScore")
    public ResponseResult<Integer> getExamScore(Exam exam,User user) {
        Integer score = examService.getExamScore(exam,user);
        return new ResponseResult<>(200,score);
    }
    //查用户一个考试的用户答案
    @GetMapping("/getExamUserAnswer")
    public ResponseResult<String> getExamUserAnswer(Exam exam,User user) {
        String ans = examService.getExamUserAnswer(exam,user);
        return new ResponseResult<>(200,ans);
    }

    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    @Operation(summary = "考试列表")
    public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                       @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(examService.getExamPage(pageUtil));
    }

    @RequestMapping(value = "/userExamHistoryList", method = RequestMethod.GET)
    @Operation(summary = "用户历史考试列表")
    public Result userExamHistoryList(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                               @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,Long userId) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(examService.getUserExamHistoryPage(pageUtil,userId));
    }
    @RequestMapping(value = "/userExamTodoList", method = RequestMethod.GET)
    @Operation(summary = "用户待考考试列表")
    public Result userExamTodoList(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                                      @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,Long userId) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(examService.getUserExamTodoPage(pageUtil,userId));
    }
    @RequestMapping(value = "/examUserList", method = RequestMethod.GET)
    @Operation(summary = "参加考试的用户列表")
    public Result examUserList(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                               @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,Integer examId) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(examService.getExamUserPage(pageUtil,examId));
    }
    @RequestMapping(value = "/noExamUserList", method = RequestMethod.GET)
    @Operation(summary = "未参加考试的用户列表")
    public Result noExamUserList(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                                 @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,Integer examId) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(examService.getNoExamUserPage(pageUtil,examId));
    }
//    @PostMapping("/checkExamTime")
//    public ResponseResult<Void> checkExamTime(Exam exam,User user) {
//        examService.deleteExamUser(exam,user);
//        return new ResponseResult<>(200);
//    }
}
