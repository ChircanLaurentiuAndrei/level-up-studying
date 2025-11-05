package com.levelup.model;

import com.levelup.interfaces.Rewardable;

import java.util.List;
import java.util.Set;

public class User implements Rewardable {
    private String name;
    private int xp;
    private List<Task> taskList;
    private Set<Achievement> achievementList;
    int numberOfTasksCompleted;



    public User(String name, int xp, List<Task> taskList, Set<Achievement> achievementList, int numberOfTasksCompleted) {
        this.name = name;
        this.xp = xp;
        this.taskList = taskList;
        this.achievementList = achievementList;
        this.numberOfTasksCompleted = numberOfTasksCompleted;
    }
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getName() {
        return name;
    }

    public int getXp() {
        return xp;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Set<Achievement> getAchievementList() {
        return achievementList;
    }

    @Override
    public void rewardUser(Task task) {
        if (taskList.contains(task)) {
            taskList.remove(task);
            xp += task.getXp();
        }
        else
            System.out.println("You have already completed this task or it does not exist");
    }

    @Override
    public void rewardAchievement(Achievement achievement) {
        achievementList.add(achievement);
    }

    @Override
    public String toString() {
        return name + " has " + xp + " xp and has " + taskList.size() + " tasks left";
    }
}
