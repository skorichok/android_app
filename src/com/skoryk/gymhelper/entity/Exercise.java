package com.skoryk.gymhelper.entity;

public class Exercise {
    private Integer id;
    private String name;

    public Exercise(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Exercise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
