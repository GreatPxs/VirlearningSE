package com.example.virlearning.service;

import com.example.virlearning.model.param.QuestionAddParam;

public interface QuestionService {
    void addQuestion(QuestionAddParam questionAddParam);

    void deleteQuestionById(Long questionId);
}
