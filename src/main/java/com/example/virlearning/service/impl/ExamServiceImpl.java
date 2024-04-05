package com.example.virlearning.service.impl;

import com.example.virlearning.dao.ExamMapper;
import com.example.virlearning.entity.Exam;
import com.example.virlearning.entity.User;
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
    public List<User> getExamUser(Exam exam){return examDao.getExamUser(exam);}
    public Integer insertExamUser(Exam exam,User user){return examDao.insertExamUser(exam,user);}
    public Integer deleteExamUser(Exam exam,User user){return examDao.deleteExamUser(exam,user);}
    public Integer calculateExamScore(Exam exam,User user,String userAnswer){
        int i = 0,scoreSum=0;
        int paper_id = examDao.getPaperId(exam.getExamId());
        int paper_question_num = examDao.getPaperTotalNum(paper_id);
        //System.out.println(paper_question_num);
        for(int j=0;j<paper_question_num;j++){
            i++;
            char userAns = userAnswer.charAt(i-1);
            //System.out.println(userAns);
            char Ans = examDao.getPaperQuestionAnswer(paper_id,i);
            //System.out.println(Ans);
            Integer score = examDao.getPaperQuestionScore(paper_id,i);
            //System.out.println(score);
            if(userAns == Ans)scoreSum += score;
        }
        return scoreSum;
    }
    public Integer updateUserAnswer(Exam exam,User user,String userAnswer) {
        return examDao.updateUserAnswer(exam.getExamId(),user.getUserId(),userAnswer);
    }
    public Integer updateUserScore(Exam exam,User user,Integer score) {
        return examDao.updateUserScore(exam.getExamId(),user.getUserId(),score);
    }
    public List<Exam> getUserExam(User user){
        return examDao.getUserExam(user.getUserId());
    }
    public Integer getExamScore(Exam exam,User user){
        return examDao.getExamScore(exam.getExamId(),user.getUserId());
    }
    public String getExamUserAnswer(Exam exam,User user){
        return examDao.getExamUserAnswer(exam.getExamId(),user.getUserId());
    }
}
