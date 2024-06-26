package com.example.virlearning.service.impl;

import com.example.virlearning.dao.PaperMapper;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.service.PaperService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.awt.desktop.SystemEventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class PaperServiceImpl implements PaperService {
    @Resource
    PaperMapper paperDao;
    public List<Paper> getfindbyName(String name){return paperDao.getfindbyName(name);}
    public Integer insertPaper(Paper paper){return paperDao.insertPaper(paper);}
    public Integer deletePaper(Paper paper){
        int cnt = paperDao.countPaperInExam(paper.getPaperId());
        if(cnt == 0) {
            paperDao.deleteAllPaperQuestion(paper);
            return paperDao.deletePaper(paper);
        }
        else {return -1;}
    }
    public Integer modifyPaper(Paper paper){return paperDao.modifyPaper(paper);}
    public List<Question> getPaperInf(Paper paper){return paperDao.getPaperInf(paper.getPaperId());}
    public List<Integer> getPaperQuestionScoreList(Paper paper){return paperDao.getPaperQuestionScoreList(paper.getPaperId());}
    public Integer getPaperTotal(Paper paper){return paperDao.getPaperTotal(paper.getPaperId());}
    public Integer getPaperTotalScore(Paper paper){return paperDao.getPaperTotalScore(paper.getPaperId());}

    public Integer insertPaperQuestion(Paper paper,Question question,Integer pqScore){
        Integer totalNum = paperDao.getPaperTotal(paper.getPaperId());
        Integer totalScore = paperDao.getPaperTotalScore(paper.getPaperId());
        System.out.println(totalNum);

        int insertNum = paperDao.insertPaperQuestion(paper.getPaperId(),question.getId(),totalNum,pqScore);
        if(insertNum == 1){
            paperDao.updateTotalNum(paper.getPaperId(), totalNum+1);
            paperDao.updateTotalScore(paper.getPaperId(), totalScore+pqScore);
        }
        return insertNum;
    }
    public Integer deletePaperQuestion(Paper paper,Question question){
        Integer totalNum = paperDao.getPaperTotal(paper.getPaperId());
        Integer totalScore = paperDao.getPaperTotalScore(paper.getPaperId());
        paperDao.updateQuestionIds(paper,question);
        int pqScore = paperDao.getPaperQuestionScore(paper.getPaperId(),question.getId());
        int deleteNum = paperDao.deletePaperQuestion(paper,question);
        if(deleteNum == 1){
            paperDao.updateTotalNum(paper.getPaperId(), totalNum-1);
            paperDao.updateTotalScore(paper.getPaperId(), totalScore-pqScore);
        }
        return deleteNum;
    }
    public PageResult getPaperPage(PageQueryUtil pageUtil) {
        List<Paper> Paper = paperDao.findPaperList(pageUtil);
        int total = paperDao.getTotalPaper(pageUtil);
        PageResult pageResult = new PageResult(Paper, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public PageResult getPaperQuestionPage(PageQueryUtil pageUtil,Integer paperId,String type,String description) {
        List<Question> question = paperDao.findPaperQuestionList(pageUtil,paperId,type,description);
        int total = paperDao.getTotalPaperQuestion(pageUtil,paperId,type,description);
        PageResult pageResult = new PageResult(question, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public PageResult getNoPaperQuestionPage(PageQueryUtil pageUtil,Integer paperId,String type,String description) {
        List<Question> question = paperDao.findNoPaperQuestionList(pageUtil,paperId,type,description);
        int total = paperDao.getTotalQuestion() - paperDao.getTotalPaperQuestion(pageUtil,paperId,type,description);
        PageResult pageResult = new PageResult(question, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
