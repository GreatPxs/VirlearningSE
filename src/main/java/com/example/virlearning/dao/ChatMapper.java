package com.example.virlearning.dao;

import com.example.virlearning.entity.Chat;
import com.example.virlearning.entity.Department;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ChatMapper {
    List<Chat> findChatList(PageQueryUtil pageUtil, Date date);
}
