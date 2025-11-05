package com.levelup.model;

import java.util.Objects;

public class Task {
    private String name;
    private int xp;

    public Task(String name, int xp) {
        this.name = name;
        this.xp = xp;
    }

    public String getName() {
        return name;
    }
    public int getXp() {
        return xp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return xp == task.xp && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, xp);
    }

    @Override
    public String toString() {
        return name + " for " + xp + " xp";
    }
}
