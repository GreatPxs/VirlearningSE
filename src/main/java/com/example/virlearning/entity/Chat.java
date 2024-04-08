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
    private String t_question;
    private String answer;



    public void setid(Integer id){
        this.user_id=id;
    }
    public Integer getid()
    {return user_id;}






    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    public Date getCreateTime() {
        return time;
    }

    public void setCreateTime(Date time) {
        this.time = time;
    }
}
