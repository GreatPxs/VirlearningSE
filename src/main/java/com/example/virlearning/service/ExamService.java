package com.example.virlearning.service;

import com.example.virlearning.entity.Exam;

import java.util.List;

public interface ExamService {
    List<Exam> getfindbyName(String name);
    Integer insertExam(Exam exam);
    Integer deleteExam(Exam exam);
    Integer modifyExam(Exam exam);
}
