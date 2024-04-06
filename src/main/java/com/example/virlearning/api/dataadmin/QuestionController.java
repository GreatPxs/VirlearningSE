package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Question;
import com.example.virlearning.service.QuestionService;
import com.example.virlearning.util.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
