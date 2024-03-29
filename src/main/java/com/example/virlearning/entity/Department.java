package com.example.virlearning.entity;


import javax.persistence.*;
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    private Integer id;
    private String name;
    private String dep_inf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDep_inf() {
        return dep_inf;
    }

    public void setDep_inf(String dep_inf) {
        this.dep_inf = dep_inf;
    }
}
