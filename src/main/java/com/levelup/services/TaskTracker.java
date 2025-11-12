package com.levelup.services;

import com.levelup.interfaces.Trackable;
import com.levelup.model.*;
import com.levelup.exceptions.DuplicateUserException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class TaskTracker implements Trackable {
    private Leaderboard lb;
    private List<User> userList;
    private Map<String, User> userMap;
    private List<Task> taskList;
    private List<Achievement> achievements;
    private FileManager fm;

    public TaskTracker() {
        lb = new Leaderboard();
        userList = new ArrayList<>();
        userMap = new HashMap<>();
        taskList = new ArrayList<>();
        achievements = new ArrayList<>();
        fm = new FileManager();
    }


    @Override

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
        userMap = new HashMap<>();
        for (User u : userList) {
            userMap.put(u.getName(), u);
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

        if (taskList == null || taskList.isEmpty()) {
            System.err.println("Warning: No tasks available to assign");
            return randomTasks;
        }
        if (taskList.size() <= 3) {
            return new ArrayList<>(taskList);
        }
        while (randomTasks.size() < 3) {
            Task randomTask = taskList.get((int) (Math.random() * taskList.size()));
            if (!randomTasks.contains(randomTask)) {
                randomTasks.add(randomTask);
            }
        }
        return randomTasks;
    }

    private boolean isValidUsername(String username) {
        return username != null && username.matches("[A-Za-z0-9_]{3,20}");
    }

    public void addUser(String username) {
        if (!isValidUsername(username)) {
            System.out.println("Invalid username. Use 3-20 alphanumeric characters or underscore.");
            return;
        }
        if (userMap.containsKey(username)) {
            try {
                throw new DuplicateUserException("User '" + username + "' already exists");
            } catch (DuplicateUserException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        List<Task> initialTasks = randomTasks();
        if (initialTasks.isEmpty()) {
            System.err.println("Warning: User created without initial tasks (no tasks available)");
        }
        User user = new User(username, 0, initialTasks, new HashSet<>(), 0);
        userList.add(user);
        userMap.put(username, user);
        lb.setUserList(userList);
        if (fm.saveLeaderboard(lb)) {
            System.out.println("User added");
        } else {
            System.out.println("User added but could not save to file");
        }
    }

    public void checkAchievements(User user, List<Achievement> achievements) {
        if (user == null || achievements == null) {
            return;
        }
        for (Achievement achievement : achievements) {
            if (achievement.getNumberOfTasksRequired() <= user.getNumberOfTasksCompleted() && !user.getAchievementList().contains(achievement)) {
                user.rewardAchievement(achievement);
            }
        }
    }

    public void rewardUser(User user, Task task) {
        user.rewardUser(task);
        checkAchievements(user, achievements);
        if (user.getTaskList().isEmpty()) {
            user.setTaskList(randomTasks());
        }
        lb.setUserList(userList);
        lb.sortLeaderboard();
        if (!fm.saveLeaderboard(lb)) {
            System.err.println("Warning: Failed to save progress to file");
        }
    }

    public User getUser(String username) {
        return userMap.get(username);
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
