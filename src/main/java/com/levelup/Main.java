package com.levelup;

import com.levelup.exceptions.UserNotFoundException;
import com.levelup.model.Achievement;
import com.levelup.model.Leaderboard;
import com.levelup.model.Task;
import com.levelup.services.FileManager;
import com.levelup.services.TaskTracker;
import com.levelup.services.UserMenu;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments were given, type -h for help");
            System.exit(1);
        }

        TaskTracker taskTracker = new TaskTracker();

        switch (args[0]) {
            case "-h":
                System.out.println("<-init> to initialize with an example data set");
                System.out.println("<-add> <userName> to add a user to the database");
                System.out.println("<-launch> <userName> to use the application with the user name chosen");
                System.out.println("Optional system properties to configure file paths: -Dlevelup.leaderboard=path -Dlevelup.tasks=path -Dlevelup.achievements=path");
                break;

            case "-init":
                taskTracker.initialize();
                FileManager fm = new FileManager();
                List<Task> tasks = new ArrayList<>();
                tasks.add(new Task("Study 30 minutes for the DB course", 20));
                tasks.add(new Task("Study 30 minutes for the P3 course", 30));
                tasks.add(new Task("Study 30 minutes for the GTC course", 50));
                tasks.add(new Task("Study 30 minutes for the PTS course", 40));
                tasks.add(new Task("Study 30 minutes for the OS course", 70));
                tasks.add(new Task("Study 30 minutes for the Spanish course", 20));
                tasks.add(new Task("Work 30 minutes for the Individual Project", 30));
                tasks.add(new Task("Work 30 minutes for the EC Project", 40));
                tasks.add(new Task("Practice 30 minutes for the OS Lab", 60));
                tasks.add(new Task("Practice 30 minutes for the DB Lab", 40));
                tasks.add(new Task("Practice 30 minutes for the P3 Lab", 40));
                tasks.add(new Task("Practice 30 minutes for the GTC Lab", 60));
                tasks.add(new Task("Practice 30 minutes for the PTS Lab", 60));
                tasks.add(new Task("Exercise 20 minutes", 40));
                tasks.add(new Task("Rest your eyes for 10 minutes", 30));
                tasks.add(new Task("Take a walk for 30 minutes", 40));
                tasks.add(new Task("Wash dishes", 30));
                tasks.add(new Task("Clean your desk", 20));
                tasks.add(new Task("Make your bed", 10));
                tasks.add(new Task("Do your laundry", 20));
                tasks.add(new Task("Buy groceries", 10));
                fm.saveTasks(tasks);

                List<Achievement> ach = new ArrayList<>();
                ach.add(new Achievement("Starter", "Complete 1 task", 1));
                ach.add(new Achievement("Baby steps", "Complete 3 tasks", 3));
                ach.add(new Achievement("Focused", "Complete 5 tasks", 5));
                ach.add(new Achievement("Grinder", "Complete 10 tasks", 10));
                ach.add(new Achievement("Master", "Complete 20 tasks", 20));
                ach.add(new Achievement("Pro", "Complete 30 tasks", 30));
                ach.add(new Achievement("Champion", "Complete 50 tasks", 50));
                ach.add(new Achievement("Legendary", "Complete 100 tasks", 100));
                ach.add(new Achievement("Godlike", "Complete 200 tasks", 200));
                fm.saveAchievement(ach);

                Leaderboard lb = new Leaderboard();
                fm.saveLeaderboard(lb);
                System.out.println("Initialized example tasks, achievements and leaderboard.");
                break;

            case "-add":
                if (args.length < 2) {
                    System.out.println("No user name was given, use -h for help");
                    System.exit(2);
                }
                taskTracker.initialize();
                taskTracker.addUser(args[1]);
                break;

            case "-launch":
                if (args.length < 2) {
                    System.out.println("No user name was given, use -h for help");
                    System.exit(3);
                }
                boolean run = true;
                taskTracker.initialize();
                try {
                    if (taskTracker.getUser(args[1]) == null) {
                        throw new UserNotFoundException("User '" + args[1] + "' does not exist");
                    }
                } catch (UserNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.exit(4);
                }
                System.out.println("Welcome " + args[1]);
                UserMenu menu = new UserMenu(taskTracker, args[1]);
                while (run) {
                    menu.clearScreen();
                    menu.displayMenu();
                    int choice = menu.getChoice();
                    if (choice == 5)
                        run = false;
                    else if (choice == -1)
                        break;
                    else {
                        menu.runMenuChoice(choice);
                    }
                }
                break;
            default:
                System.out.println("Unknown command. Use -h for help");
        }
        System.exit(0);

    }
}