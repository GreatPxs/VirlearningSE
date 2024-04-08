package com.example.virlearning.dao;

import com.example.virlearning.entity.Chat;
import com.example.virlearning.entity.Department;
import com.example.virlearning.entity.Drug;
import com.example.virlearning.util.PageQueryUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ChatMapper {
    List<Chat> findChatList(Integer user_id,Date beforedate,Date afterdate);
    /**
     * 添加对话
     * @param chat
     * @return
     */
    Integer insertChat(Chat  chat);
}
