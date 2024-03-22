package com.example.virlearning.service.impl;

import com.example.virlearning.dao.QuestionMapper;
import com.example.virlearning.model.param.QuestionAddParam;
import com.example.virlearning.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class questionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void addQuestion(QuestionAddParam questionAddParam) {
        questionMapper.addQuestion(questionAddParam);
    }

    @Override
    public void deleteQuestionById(Long questionId) {
        questionMapper.deleteQuestionById(questionId);
    }
}
