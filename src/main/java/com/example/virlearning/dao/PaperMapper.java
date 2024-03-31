package com.example.virlearning.dao;

import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;

import java.util.List;

public interface PaperMapper {
    //根据name查试卷
    List<Paper> getfindbyName(String name);
    Integer insertPaper(Paper paper);
    Integer deletePaper(Paper paper);
    Integer modifyPaper(Paper paper);
    //查一个试卷的具体信息，哪些题目
    List<Question> getPaperInf(Paper paper);
    //在一个试卷中增加题目
    Integer insertPaperQuestion(Paper paper,Question question);
    //在一个试卷中删除题目
    Integer deletePaperQuestion(Paper paper,Question question);
}
