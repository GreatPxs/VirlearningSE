package com.example.virlearning.dao;

import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PaperMapper {
    //根据name查试卷
    List<Paper> getfindbyName(String name);
    Integer insertPaper(Paper paper);
    Integer deletePaper(Paper paper);
    Integer countPaperInExam(Integer paperId);
    Integer deleteAllPaperQuestion(Paper paper);
    Integer modifyPaper(Paper paper);
    //查一个试卷的具体信息，哪些题目
    List<Question> getPaperInf(@Param("paperId")Integer paperId);
    List<Integer> getPaperQuestionScoreList(Integer paperId);
    Integer getPaperTotal(Integer paperId);
    Integer getPaperTotalScore(Integer paperId);
    //在一个试卷中增加题目
    Integer insertPaperQuestion(@Param("paperId")Integer paperId,@Param("questionId")Integer questionId,@Param("totalNum")Integer totalNum,@Param("pqScore")Integer pqScore);
    Integer updateTotalNum(Integer paperId,Integer totalNum);
    Integer updateTotalScore(Integer paperId,Integer totalScore);
    //在一个试卷中删除题目
    Integer deletePaperQuestion(Paper paper,Question question);
    Integer updateQuestionIds(Paper paper,Question question);

    List<Paper> findPaperList(PageQueryUtil pageUtil);

    int getTotalPaper(PageQueryUtil pageUtil);
    List<Question> findPaperQuestionList(PageQueryUtil pageUtil,Integer paperId,String type,String description);

    int getTotalPaperQuestion(PageQueryUtil pageUtil,Integer paperId,String type,String description);
    List<Question> findNoPaperQuestionList(PageQueryUtil pageUtil,Integer paperId,String type,String description);

    //int getTotalNoPaperQuestion(PageQueryUtil pageUtil,Integer paperId);
    int getTotalQuestion();
    int getPaperQuestionScore(Integer paperId,Integer questionId);
}
