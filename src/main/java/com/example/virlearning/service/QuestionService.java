package com.example.virlearning.service;

import com.example.virlearning.model.param.QuestionAddParam;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;

public interface QuestionService {
    void addQuestion(QuestionAddParam questionAddParam);

    void deleteQuestionById(Long questionId);

    PageResult getQuestionsPage(PageQueryUtil pageUtil);
}
