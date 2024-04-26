package com.example.virlearning.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Integer id;
    private Integer isdeleted;
    private String name;
    private String feature;
    private String photo;
    private String video;
    private String dep;
}


