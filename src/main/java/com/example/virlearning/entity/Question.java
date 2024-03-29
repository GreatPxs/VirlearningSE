package com.example.virlearning.entity;


import lombok.Data;

@Data
public class Question {
    private long questionId;

    private String description;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String answer;

    private Boolean is_deleted;
}
