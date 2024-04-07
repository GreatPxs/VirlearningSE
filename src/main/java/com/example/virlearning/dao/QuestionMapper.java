package com.example.virlearning.dao;

import com.example.virlearning.entity.Question;
import com.example.virlearning.util.PageQueryUtil;

import java.util.List;

public interface QuestionMapper {
    List<Question> getfindbyTypeDescription(String type,String description);
    Integer insertQuestion(Question question);
    Integer deleteQuestion(Question question);
    Integer modifyQuestion(Question question);
    List<Question> findQuestionList(PageQueryUtil pageUtil);
    int getTotalQuestion(PageQueryUtil pageUtil);

}
