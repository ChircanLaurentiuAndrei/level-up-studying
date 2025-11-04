package com.levelup;

import com.levelup.services.TaskTracker;
import com.levelup.services.UserMenu;


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
                if (taskTracker.getUser(args[1]) == null)
                    System.out.println("User does not exist");
                else {
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
                }
        }
        System.exit(0);

    }
}