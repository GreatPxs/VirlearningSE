package com.example.virlearning.service.impl;

import com.example.virlearning.dao.QuestionMapper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.model.param.QuestionAddParam;
import com.example.virlearning.service.QuestionService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageResult getQuestionsPage(PageQueryUtil pageUtil) {
        List<Question> questions = questionMapper.findQuestionsByPage(pageUtil);
        int total = questionMapper.getTotalUsers(pageUtil);
        PageResult pageResult = new PageResult(questions, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
