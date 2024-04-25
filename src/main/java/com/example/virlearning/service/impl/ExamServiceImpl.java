package com.example.virlearning.service.impl;

import com.example.virlearning.dao.ExamMapper;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Exam;
import com.example.virlearning.entity.User;
import com.example.virlearning.service.ExamService;
import com.example.virlearning.util.PageQueryUtil;
import com.example.virlearning.util.PageResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
@Service

public class ExamServiceImpl implements ExamService {
    @Resource
    ExamMapper examDao;
    public List<Exam> getfindbyName(String name){return examDao.getfindbyName(name);}
    public Integer insertExam(Exam exam){return examDao.insertExam(exam);}
    public Integer deleteExam(Exam exam){
        examDao.deleteExamUser(exam,null);
        return examDao.deleteExam(exam);
    }
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
    public Integer updateUserAnswerScore(Exam exam,User user,String userAnswer,Integer score) {
        return examDao.updateUserAnswer(exam.getExamId(),user.getUserId(),userAnswer,score);
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
    public Integer updateStartExamTime(Exam exam,User user,String time){
        return examDao.updateStartExamTime(exam.getExamId(),user.getUserId(),time);
    }
    public Integer updateEndExamTime(Exam exam,User user,String time){
        return examDao.updateEndExamTime(exam.getExamId(),user.getUserId(),time);
    }
    public Integer getExamLimitTime(Exam exam){
        return examDao.getExamLimitTime(exam.getExamId());
    }

    public PageResult getExamPage(PageQueryUtil pageUtil) {
        List<Exam> exam = examDao.findExamList(pageUtil);
        int total = examDao.getTotalExam(pageUtil);
        PageResult pageResult = new PageResult(exam, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public PageResult getUserExamHistoryPage(PageQueryUtil pageUtil,Long userId) {
        List<Exam> exam = examDao.findUserExamHistoryList(pageUtil,userId);
        int total = examDao.getTotalUserExamHistory(pageUtil,userId);
        PageResult pageResult = new PageResult(exam, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public PageResult getUserExamTodoPage(PageQueryUtil pageUtil,Long userId) {
        List<Exam> exam = examDao.findUserExamTodoList(pageUtil, userId);
        int total = examDao.getTotalUserExamTodo(pageUtil, userId);

        Iterator<Exam> iterator = exam.iterator();
        while (iterator.hasNext()) {
            Exam e = iterator.next();

            String endTimeString = e.getEndTime(); // 获取结束时间的字符串表示
            LocalDateTime endTime = LocalDateTime.parse(endTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime currentTime = LocalDateTime.now();

            if (currentTime.isAfter(endTime)) {
                int n = examDao.getPaperTotalNum(e.getPaperId());
                String EAnswer = new String(new char[n]).replace('\0', 'E');
                examDao.updateUserAnswer(e.getExamId(), userId, EAnswer, 0);

                // 从列表中删除超时的考试
                iterator.remove();
                total -= 1;
            }
        }
        PageResult pageResult = new PageResult(exam, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public PageResult getExamUserPage(PageQueryUtil pageUtil,Integer examId) {
        List<User> user = examDao.findExamUserList(pageUtil,examId);
        int total = examDao.getTotalExamUser(pageUtil,examId);
        PageResult pageResult = new PageResult(user, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public PageResult getNoExamUserPage(PageQueryUtil pageUtil,Integer examId) {
        int cnt = examDao.getTotalExamUser(pageUtil,examId);
        List<User> user;
        if(cnt != 0){
            user = examDao.findNoExamUserList(pageUtil,examId);
        }
        else {
            user = examDao.findAllUserList(pageUtil);
        }
        //List<User> user = examDao.findNoExamUserList(pageUtil,examId);
        int total = examDao.getTotalUser() - cnt;
        PageResult pageResult = new PageResult(user, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public String getUserExamStartTime(User user,Exam exam) {
        return examDao.getUserExamStartTime(user.getUserId(),exam.getExamId());
    }
    public String getUserExamEndTime(User user,Exam exam) {
        return examDao.getUserExamEndTime(user.getUserId(),exam.getExamId());
    }
}
