package com.example.virlearning.entity;

import lombok.Data;

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
        private Integer isDelete;//是否删除，0-未删除，1-已删除
        public Integer getId(){
                return case_id;
        }



}
