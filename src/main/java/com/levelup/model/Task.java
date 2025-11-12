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
    public String toString() {
        return name + " for " + xp + " xp";
    }
}
