package com.example.virlearning.api.dataadmin;

import com.example.virlearning.model.param.QuestionAddParam;
import com.example.virlearning.service.QuestionService;
import com.example.virlearning.util.Result;
import com.example.virlearning.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
}
