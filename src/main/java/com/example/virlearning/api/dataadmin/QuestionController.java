package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Question;
import com.example.virlearning.service.QuestionService;
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
@RequestMapping("/question")
public class QuestionController {
    @Resource
    QuestionService questionService;
    @GetMapping("/select")
    public ResponseResult<List<Question>> getfindbyTypeDescription(String type,String description) {
        List<Question> list = questionService.getfindbyTypeDescription(type, description);
        return new ResponseResult<List<Question>>(200, list);
    }
    @PutMapping("/add")
    public ResponseResult<Void> insertQuestion(Question question) {
        questionService.insertQuestion(question);
        return new ResponseResult<>(200);
    }
    @GetMapping("/delete")
    public ResponseResult<Void> deleteQuestion(Question question) {
        questionService.deleteQuestion(question);
        return new ResponseResult<>(200);
    }
    @GetMapping("/modify")
    public ResponseResult<Void> modifyQuestion(Question question) {
        questionService.modifyQuestion(question);
        return new ResponseResult<>(200);
    }

    @RequestMapping(value = "/showall", method = RequestMethod.GET)
    @Operation(summary = "题目列表")
    public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                       @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 5) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);

        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(questionService.getQuestionPage(pageUtil));
    }
}
