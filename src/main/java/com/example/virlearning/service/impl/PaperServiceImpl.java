package com.example.virlearning.service.impl;

import com.example.virlearning.dao.PaperMapper;
import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;
import com.example.virlearning.service.PaperService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PaperServiceImpl implements PaperService {
    @Resource
    PaperMapper paperDao;
    public List<Paper> getfindbyName(String name){return paperDao.getfindbyName(name);}
    public Integer insertPaper(Paper paper){return paperDao.insertPaper(paper);}
    public Integer deletePaper(Paper paper){return paperDao.deletePaper(paper);}
    public Integer modifyPaper(Paper paper){return paperDao.modifyPaper(paper);}
    public List<Question> getPaperInf(Paper paper){return paperDao.getPaperInf(paper);}
    public Integer insertPaperQuestion(Paper paper,Question question){return paperDao.insertPaperQuestion(paper,question);}
    public Integer deletePaperQuestion(Paper paper,Question question){return paperDao.deletePaperQuestion(paper,question);}
}
