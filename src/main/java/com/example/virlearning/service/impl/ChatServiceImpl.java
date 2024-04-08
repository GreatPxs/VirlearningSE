package com.example.virlearning.service.impl;


import com.example.virlearning.config.InsertException;
import com.example.virlearning.dao.ChatMapper;

import com.example.virlearning.entity.Chat;
import com.example.virlearning.entity.Department;
import com.example.virlearning.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatMapper chatMapper;



    /**
     * 添加对话
     * @param record


     * @throws InsertException
     */
    @Override
    public void addChat(Chat record) throws  InsertException {

        chatMapper.insertChat(record);
    }
    public List<Chat> getchat(Integer id,Date beforedate, Date afterdate){

        return chatMapper.findChatList(id,beforedate,afterdate);
    }
}
