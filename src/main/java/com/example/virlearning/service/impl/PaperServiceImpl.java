package com.example.virlearning.service.impl;

import com.example.virlearning.dao.PaperMapper;
import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.service.PaperService;
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
    public Integer deletePaper(Paper paper){return paperDao.deletePaper(paper);}
    public Integer modifyPaper(Paper paper){return paperDao.modifyPaper(paper);}
    public List<Question> getPaperInf(Paper paper){return paperDao.getPaperInf(paper);}
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
        paperDao.updateQuestionIds(paper,question);
        int deleteNum = paperDao.deletePaperQuestion(paper,question);
        if(deleteNum == 1){
            paperDao.updateTotalNum(paper.getPaperId(), totalNum-1);
        }
        return deleteNum;
    }
}
