package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Exam;
import com.example.virlearning.entity.User;
import com.example.virlearning.service.ExamService;
import com.example.virlearning.util.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Resource
    ExamService examService;
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
}
