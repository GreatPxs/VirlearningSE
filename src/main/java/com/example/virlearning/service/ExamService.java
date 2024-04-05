package com.example.virlearning.service;

import com.example.virlearning.entity.Exam;
import com.example.virlearning.entity.User;

import java.util.List;

public interface ExamService {
    List<Exam> getfindbyName(String name);
    Integer insertExam(Exam exam);
    Integer deleteExam(Exam exam);
    Integer modifyExam(Exam exam);
    List<User> getExamUser(Exam exam);
    Integer insertExamUser(Exam exam,User user);
    Integer deleteExamUser(Exam exam,User user);
}
