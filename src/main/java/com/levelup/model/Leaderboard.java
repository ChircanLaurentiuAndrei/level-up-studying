package com.levelup.model;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private List<User> userList;

    public Leaderboard() {
        this.userList = new ArrayList<>();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void sortLeaderboard() {
        this.userList.sort((u1, u2) -> Integer.compare(u2.getXp(), u1.getXp()));
    }

    @Override
    public String toString() {
        return userList.toString();
    }
}
