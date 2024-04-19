package com.example.virlearning.service;

import com.example.virlearning.entity.Question;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;

import java.util.List;

public interface QuestionService {
    List<Question> getfindbyTypeDescription(String type, String description);
    Integer insertQuestion(Question question);
    Integer deleteQuestion(Question question);
    List<String> getPapersQuestion(Question question);
    Integer modifyQuestion(Question question);
    PageResult getQuestionPage(PageQueryUtil pageUtil);

}
