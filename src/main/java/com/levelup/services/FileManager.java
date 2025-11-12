package com.levelup.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.levelup.model.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME_LEADERBOARD = "leaderboard.json";
    private static final String FILE_NAME_ACHIEVEMENTS = "achievement.json";
    private static final String FILE_NAME_TASK ="task.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public boolean saveLeaderboard(Leaderboard leaderboard) {
        try (FileWriter writer = new FileWriter(FILE_NAME_LEADERBOARD)) {
            gson.toJson(leaderboard, writer);
            System.out.println("Leaderboard data saved to file");
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found while saving leaderboard: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Error saving leaderboard: " + e.getMessage());
            return false;
        }
    }

    public Leaderboard loadLeaderboard() {
        try (FileReader reader = new FileReader(FILE_NAME_LEADERBOARD)) {
            Leaderboard leaderboard = gson.fromJson(reader, Leaderboard.class);
            return leaderboard != null ? leaderboard : new Leaderboard();
        } catch (FileNotFoundException e) {
            System.err.println("Leaderboard file not found: " + e.getMessage());
            return new Leaderboard();
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON in leaderboard file: " + e.getMessage());
            return new Leaderboard();
        } catch (IOException e) {
            System.err.println("Error loading leaderboard: " + e.getMessage());
            return new Leaderboard();
        }
    }

    public List<Achievement> loadAchievement() {
        try (FileReader reader = new FileReader(FILE_NAME_ACHIEVEMENTS)) {
            Type type = new TypeToken<List<Achievement>>(){}.getType();
            List<Achievement> achievements = gson.fromJson(reader, type);
            return achievements != null ? achievements : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.err.println("Achievements file not found: " + e.getMessage());
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON in achievements file: " + e.getMessage());
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading achievements: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean saveAchievement(List<Achievement> achievements) {
        try (FileWriter writer = new FileWriter(FILE_NAME_ACHIEVEMENTS)) {
            gson.toJson(achievements, writer);
            System.out.println("Achievement data saved to file");
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found while saving achievements: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Error saving achievements: " + e.getMessage());
            return false;
        }
    }

    public List<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_NAME_TASK)) {
            Type type = new TypeToken<List<Task>>(){}.getType();
            List<Task> tasks = gson.fromJson(reader, type);
            return tasks != null ? tasks : new ArrayList<>();
        } catch (FileNotFoundException e) {
            System.err.println("Tasks file not found: " + e.getMessage());
            return new ArrayList<>();
        } catch (JsonSyntaxException e) {
            System.err.println("Invalid JSON in tasks file: " + e.getMessage());
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean saveTasks(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_NAME_TASK)) {
            gson.toJson(tasks, writer);
            System.out.println("Task data saved to file");
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("File not found while saving tasks: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
            return false;
        }
    }
}
