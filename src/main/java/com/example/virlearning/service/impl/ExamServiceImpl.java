package com.example.virlearning.service.impl;

import com.example.virlearning.dao.ExamMapper;
import com.example.virlearning.entity.Exam;
import com.example.virlearning.service.ExamService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ExamServiceImpl implements ExamService {
    @Resource
    ExamMapper examDao;
    public List<Exam> getfindbyName(String name){return examDao.getfindbyName(name);}
    public Integer insertExam(Exam exam){return examDao.insertExam(exam);}
    public Integer deleteExam(Exam exam){return examDao.deleteExam(exam);}
    public Integer modifyExam(Exam exam){return examDao.modifyExam(exam);}
}
