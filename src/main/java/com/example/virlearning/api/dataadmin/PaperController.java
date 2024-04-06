package com.example.virlearning.api.dataadmin;

import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.service.PaperService;
import com.example.virlearning.util.ResponseResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paper")
public class PaperController {
    @Resource
    PaperService paperService;
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
    public ResponseResult<Void> deletePaper(Paper paper) {
        paperService.deletePaper(paper);
        return new ResponseResult<>(200);
    }
    @GetMapping("/modify")
    public ResponseResult<Void> modifyPaper(Paper paper) {
        paperService.modifyPaper(paper);
        return new ResponseResult<>(200);
    }
    @GetMapping("/getPaperInf")
    public ResponseResult<List<Question>> getPaperInf(Paper paper) {
        List<Question> list = paperService.getPaperInf(paper);
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

}
