package com.example.virlearning.service;

import com.example.virlearning.entity.Paper;
import com.example.virlearning.entity.Question;

import java.util.List;

public interface PaperService {
    List<Paper> getfindbyName(String name);
    Integer insertPaper(Paper paper);
    Integer deletePaper(Paper paper);
    Integer modifyPaper(Paper paper);
    List<Question> getPaperInf(Paper paper);
    Integer insertPaperQuestion(Paper paper,Question question,Integer pqScore);
    Integer deletePaperQuestion(Paper paper,Question question);
}
