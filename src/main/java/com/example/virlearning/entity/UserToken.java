package com.example.virlearning.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserToken {
    private Long userId;

    private String token;

    private Date updateTime;

    private Date expireTime;
}