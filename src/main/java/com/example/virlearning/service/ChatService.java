package com.example.virlearning.service;

import com.example.virlearning.config.InsertException;
import com.example.virlearning.entity.Chat;
import com.example.virlearning.entity.Department;

import java.util.Date;
import java.util.List;

public interface ChatService {
    /**
     * 添加对话
     * @param record

     * @throws InsertException
     */
    void addChat(Chat record) throws InsertException;
    List<Chat> getchat(Integer id,Date beforedate,Date afterdate);
}
