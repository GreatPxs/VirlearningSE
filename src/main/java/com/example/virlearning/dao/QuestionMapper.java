package com.example.virlearning.dao;

import com.example.virlearning.entity.Question;
import com.example.virlearning.model.param.QuestionAddParam;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    void addQuestion(QuestionAddParam questionAddParam);

    void deleteQuestionById(Long questionId);

    int getTotalUsers(PageQueryUtil pageUtil);

    List<Question> findQuestionsByPage(PageQueryUtil pageUtil);
}
