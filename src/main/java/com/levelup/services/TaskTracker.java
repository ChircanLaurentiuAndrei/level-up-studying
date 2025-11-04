package com.levelup.services;

import com.levelup.model.*;
import java.util.ArrayList;
import java.util.List;

public class TaskTracker {
    private Leaderboard lb;
    private List<User> userList;
    private List<Task> taskList;
    private List<Achievement> achievements;
    private FileManager fm;

    public TaskTracker() {
        lb = new Leaderboard();
        userList = new ArrayList<>();
        taskList = new ArrayList<>();
        achievements = new ArrayList<>();
        fm = new FileManager();
    }


    public void initialize() {
        lb = fm.loadLeaderboard();
        if (lb == null) {
            System.out.println("Warning: Could not load leaderboard. Initializing new leaderboard.");
            lb = new Leaderboard();
            lb.setUserList(new ArrayList<>());
            fm.saveLeaderboard(lb);
        }

        userList = lb.getUserList();
        if (userList == null) {
            userList = new ArrayList<>();
            lb.setUserList(userList);
        }

        taskList = fm.loadTasks();

        if (taskList == null) {
            System.out.println("Warning: Could not load tasks. Initializing empty task list.");
            taskList = new ArrayList<>();
        }

        achievements = fm.loadAchievement();

        if (achievements == null) {
            System.out.println("Warning: Could not load achievements. Initializing empty achievements.");
            achievements = new ArrayList<>();
        }

        lb.sortLeaderboard();
    }

    public List<Task> randomTasks() {
        List<Task> randomTasks = new ArrayList<>();
        while (randomTasks.size() < 3){
            Task randomTask = taskList.get((int) (Math.random() * taskList.size()));
            if (!randomTasks.contains(randomTask))
                randomTasks.add(randomTask);
        }
        return randomTasks;
    }


    public void addUser(String username) {
        if (userList.stream().anyMatch(u -> u.getName().equals(username))) {
            System.out.println("User already exists");
        }
        else {
            User user = new User(username, 0, randomTasks(), new ArrayList<>(), 0);
            userList.add(user);
            lb.setUserList(userList);
            fm.saveLeaderboard(lb);
            System.out.println("User added");
        }
    }

    public void checkAchievements(User user, List<Achievement> achievements) {
        for (Achievement achievement : achievements) {
            if (achievement.getNumberOfTasksRequired() <= user.getNumberOfTasks())
                user.rewardAchievement(achievement);
        }
    }

    public void rewardUser(User user, Task task) {
        user.rewardUser(task);
        checkAchievements(user, achievements);
        lb.setUserList(userList);
        lb.sortLeaderboard();
        fm.saveLeaderboard(lb);

    }

    public User getUser(String username) {
        return userList.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
    }

    public Leaderboard getLeaderboard() {
        return lb;
    }


    @Override

    public String toString() {
        return "TaskTracker{" +
                "lb=" + lb +
                ", userList=" + userList +
                ", achievements=" + achievements +
                '}';
    }
}
