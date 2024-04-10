package com.example.virlearning.dao;

import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Exam;
import com.example.virlearning.entity.User;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExamMapper {
    List<Exam> getfindbyName(String name);
    Integer insertExam(Exam exam);
    Integer deleteExam(Exam exam);
    Integer modifyExam(Exam exam);

    //查看考试有哪些用户参加
    //@Select("UPDATE dishes SET status=#{status} WHERE name=#{name} AND size=#{size,javaType=String,jdbcType=VARCHAR}")

    List<User> getExamUser(Exam exam);
    //在考试中添加用户
    Integer insertExamUser(Exam exam,User user);
    //删除
    Integer deleteExamUser(Exam exam,User user);
    Integer getPaperId(Integer examId);
    //得到试卷中题号为i的题目正确答案
    char getPaperQuestionAnswer(Integer paperId,int i);
    //得到试卷的题目总数
    Integer getPaperTotalNum(Integer paperId);
    Integer getPaperQuestionScore(Integer paperId,int i);
    Integer updateUserAnswer(Integer examId,Long userId,String userAnswer,Integer score);
    Integer updateUserScore(Integer examId,Long userId, Integer score);
    List<Exam> getUserExam(Long userId);
    Integer getExamScore(Integer examId,Long userId);
    String getExamUserAnswer(Integer examId,Long userId);

    List<Exam> findExamList(PageQueryUtil pageUtil);

    int getTotalExam(PageQueryUtil pageUtil);
    List<Exam> findUserExamHistoryList(PageQueryUtil pageUtil,Integer userId);

    int getTotalUserExamHistory(PageQueryUtil pageUtil,Integer userId);
    List<Exam> findUserExamTodoList(PageQueryUtil pageUtil,Integer userId);

    int getTotalUserExamTodo(PageQueryUtil pageUtil,Integer userId);
    List<User> findExamUserList(PageQueryUtil pageUtil,Integer examId);

    int getTotalExamUser(PageQueryUtil pageUtil,Integer examId);
    List<User> findNoExamUserList(PageQueryUtil pageUtil,Integer examId);
    List<User> findAllUserList(PageQueryUtil pageUtil);

    int getTotalNoExamUser(PageQueryUtil pageUtil,Integer examId);
    int getTotalUser();

}
