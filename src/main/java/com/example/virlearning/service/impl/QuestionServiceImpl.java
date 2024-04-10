package com.example.virlearning.service.impl;

import com.example.virlearning.dao.QuestionMapper;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Question;
import com.example.virlearning.service.QuestionService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionDao;
    public List<Question> getfindbyTypeDescription(String type, String description){return questionDao.getfindbyTypeDescription(type,description);}
    public Integer insertQuestion(Question question){return questionDao.insertQuestion(question);}
    public Integer deleteQuestion(Question question){
        int cnt = questionDao.countQuestionInPaper(question);
        if(cnt == 0) {return questionDao.deleteQuestion(question);}
        else {return -1;}
    }
    public List<String> getPapersQuestion(Question question){return questionDao.getPapersQuestion(question.getId());}
    public Integer modifyQuestion(Question question){return questionDao.modifyQuestion(question);}
    public PageResult getQuestionPage(PageQueryUtil pageUtil){
        List<Question> Question = questionDao.findQuestionList(pageUtil);
        int total = questionDao.getTotalQuestion(pageUtil);
        PageResult pageResult = new PageResult(Question, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
