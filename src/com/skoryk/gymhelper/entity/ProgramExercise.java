package com.skoryk.gymhelper.entity;

import java.util.ArrayList;
import java.util.Date;

public class ProgramExercise {
    private Integer id;
    private Exercise exercise;
    private Date date;
    private String time;
    private Integer trainingId;
    private ArrayList<Set> sets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Integer trainingNumber) {
        this.trainingId = trainingNumber;
    }

    public ArrayList<Set> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }

    public int getSetsCount() {
        if (null != sets) {
            return sets.size();
        }

        return 0;
    }
}
