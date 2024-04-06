package com.example.virlearning.entity;


import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "department_role")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Integer id;
    private String name;
    private String dep_inf;
    private String play_role;
    private String fileurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getPlay_role(){return play_role;}


    public void setName(String name) {
        this.name = name;
    }
    public void setPlay_role(String role) {
        this.play_role = role;
    }

    public String getrole() {
        return play_role;
    }
    public String getDep_inf() {
        return dep_inf;
    }



    public void setDep_inf(String dep_inf) {
        this.dep_inf = dep_inf;
    }
}
