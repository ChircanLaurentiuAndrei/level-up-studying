package com.levelup.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement that = (Achievement) o;
        return numberOfTasksRequired == that.numberOfTasksRequired && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, numberOfTasksRequired);
    }

    @Override
    public String toString() {
        return name + "(" + description + ")";
    }
}
