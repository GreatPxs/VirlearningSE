package com.example.virlearning.entity;

import javax.persistence.*;

@Entity
@Table(name = "/paper")
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "id")
    private Integer paperId;
    private String name;


    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
