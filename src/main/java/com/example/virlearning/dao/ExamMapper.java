package com.example.virlearning.dao;

import com.example.virlearning.entity.Exam;
import com.example.virlearning.entity.User;
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
}
