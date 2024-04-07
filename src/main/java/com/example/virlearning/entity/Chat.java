package com.example.virlearning.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Chat {
    private int dia_id;
    private Integer user_id;
    private Text t_question;
    private Text answer;











    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
}
