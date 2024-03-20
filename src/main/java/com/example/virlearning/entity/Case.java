package com.example.virlearning.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Case {

        private Integer caseId;

        private String name;

        private String symptom;

        private String animal;

        private String cureMethod;

        private Date time;

        private Integer uploadUser;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;

}
