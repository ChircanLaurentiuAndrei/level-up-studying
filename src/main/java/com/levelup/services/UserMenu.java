package com.levelup.services;

import com.levelup.model.Task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserMenu {
    private TaskTracker taskTracker;
    private String username;
    private Scanner scanner;
    public UserMenu(TaskTracker taskTracker, String username) {
        this.taskTracker = taskTracker;
        this.username = username;
        this.scanner = new Scanner(System.in);
    }

    public int getChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 5)
                return choice;
            else {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
            return -1;
        }
    }

    public void runMenuChoice(int choice) {
        switch (choice) {
            case 1:
                clearScreen();
                listTasks();
                backToMenu();
                break;
            case 2:
                clearScreen();
                listLeaderboard();
                backToMenu();
                break;
            case 3:
                clearScreen();
                listAchievements();
                backToMenu();
                break;
            case 4:
                clearScreen();
                listTasks();
                completeTask();
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayMenu() {
        System.out.println("Welcome to LevelUp!");
        System.out.println("Current user: " + username);
        System.out.println("1. List tasks that you can complete");
        System.out.println("2. List leaderboard");
        System.out.println("3. List your achievements");
        System.out.println("4. Complete task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    public void backToMenu() {
        System.out.println("Press anything to return to the main menu");
        scanner.nextLine();
    }

    public void listTasks() {
        System.out.println("Your current tasks:");
        for (int i = 1; i <= taskTracker.getUser(username).getTaskList().size(); i++) {
            System.out.println(i + ". " + taskTracker.getUser(username).getTaskList().get(i - 1));
        }
    }

    public void listLeaderboard() {
        if(taskTracker.getLeaderboard() != null) {
            taskTracker.getLeaderboard().getUserList().forEach(System.out::println);
        }
    }

    public void listAchievements() {
        if(taskTracker.getUser(username) != null) {
            taskTracker.getUser(username).getAchievementList().forEach(System.out::println);
        }
    }

    public void completeTask() {
        if (taskTracker.getUser(username).getTaskList().isEmpty()) {
            System.out.println("No tasks available to complete.");
            return;
        }
        System.out.println("Enter the index of the task you would like to complete");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 1 && index <= taskTracker.getUser(username).getTaskList().size()) {
                Task task = taskTracker.getUser(username).getTaskList().get(index - 1);
                taskTracker.rewardUser(taskTracker.getUser(username), task);
                System.out.println("Task completed!");
            } else {
                System.out.println("Invalid index. Please enter a valid number.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }

    }
}
