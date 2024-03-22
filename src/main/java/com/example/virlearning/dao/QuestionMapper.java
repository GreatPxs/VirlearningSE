package com.example.virlearning.dao;

import com.example.virlearning.model.param.QuestionAddParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionMapper {
    void addQuestion(QuestionAddParam questionAddParam);

    void deleteQuestionById(Long questionId);
}
