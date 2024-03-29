package com.example.virlearning.api.dataadmin;

import com.example.virlearning.model.param.QuestionAddParam;
import com.example.virlearning.service.QuestionService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value="/api/v1/question")
public class QuestionAPI {
    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "/addQuestions")
    public Result addQuestion(@Validated @RequestBody QuestionAddParam questionAddParam, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
        }
        questionService.addQuestion(questionAddParam);
        Result result = ResultGenerator.genSuccessResult();
        result.setData(null);
        return result;
    }

    @DeleteMapping(value ="/deleteQuestionById/{questionId}")
    public Result deleteQuestionById(@PathVariable(value="questionId") Long questionId){
        questionService.deleteQuestionById(questionId);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping(value="/findByPage")
    public Result list(@RequestParam(required = false) @Parameter(description = "页码") Integer pageNumber,
                       @RequestParam(required = false) @Parameter(description = "每页条数") Integer pageSize,
                       @RequestParam(required = false) String description) {

        if (pageNumber == null || pageNumber < 1 || pageSize == null || pageSize < 10) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        Map params = new HashMap(8);
        params.put("page", pageNumber);
        params.put("limit", pageSize);
        params.put("description",description);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(questionService.getQuestionsPage(pageUtil));
    }

}
