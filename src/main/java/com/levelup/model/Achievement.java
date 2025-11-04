package com.levelup.model;

public class Achievement {
    private String name;
    private String description;
    private int numberOfTasksRequired;

    public Achievement(String name, String description, int numberOfTasksRequired) {
        this.name = name;
        this.description = description;
        this.numberOfTasksRequired = numberOfTasksRequired;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getNumberOfTasksRequired() {
        return numberOfTasksRequired;
    }


    @Override
    public String toString() {
        return name + "(" + description + ")";
    }
}
