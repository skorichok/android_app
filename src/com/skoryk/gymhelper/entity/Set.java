package com.skoryk.gymhelper.entity;

public class Set {
    private Integer programExerciseId;
    private Integer setNumber;
    private Integer reps;
    private Integer weight;

    public Integer getProgramExerciseId() {
        return programExerciseId;
    }

    public void setProgramExerciseId(Integer programExerciseId) {
        this.programExerciseId = programExerciseId;
    }

    public Integer getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(Integer setNumber) {
        this.setNumber = setNumber;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
