package com.example.virlearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRecord {
    private String recordId;
    private String sessionId;
    private String records;
    private LocalDateTime createTime;
}
