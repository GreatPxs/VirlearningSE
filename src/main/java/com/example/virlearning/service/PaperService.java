package com.example.virlearning.service;

import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;

import java.util.List;

public interface PaperService {
    List<Paper> getfindbyName(String name);
    Integer insertPaper(Paper paper);
    Integer deletePaper(Paper paper);
    Integer modifyPaper(Paper paper);
    List<Question> getPaperInf(Paper paper);
    List<Integer> getPaperQuestionScoreList(Paper paper);
    Integer getPaperTotal(Paper paper);
    Integer getPaperTotalScore(Paper paper);
    Integer insertPaperQuestion(Paper paper,Question question,Integer pqScore);
    Integer deletePaperQuestion(Paper paper,Question question);
    PageResult getPaperPage(PageQueryUtil pageUtil);
    PageResult getPaperQuestionPage(PageQueryUtil pageUtil,Integer paperId,String type,String description);
    PageResult getNoPaperQuestionPage(PageQueryUtil pageUtil,Integer paperId,String type,String description);


}
