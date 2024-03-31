package com.example.virlearning.service.impl;

import com.example.virlearning.dao.QuestionMapper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.service.QuestionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class QuestionServiceImpl implements QuestionService {
    @Resource
    private QuestionMapper questionDao;
    public List<Question> getfindbyTypeDescription(String type, String description){return questionDao.getfindbyTypeDescription(type,description);}
    public Integer insertQuestion(Question question){return questionDao.insertQuestion(question);}
    public Integer deleteQuestion(Question question){return questionDao.deleteQuestion(question);}
    public Integer modifyQuestion(Question question){return questionDao.modifyQuestion(question);}
}
