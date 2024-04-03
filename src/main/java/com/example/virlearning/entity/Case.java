package com.example.virlearning.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Case {

        private Integer case_id;

        private String name;

        private String symptom;

        private String animal;

        private String cure_method;

        private Integer uploadUser;

        private String jzword;
        private String jzphoto;
        private String jzvideo;
        private String jcword;
        private String jcphoto;
        private String jcvideo;
        private String zdword;
        private String zdphoto;
        private String zdvideo;
        private String zlword;
        private String zlphoto;
        private String zlvideo;



}
