package com.levelup.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.levelup.model.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME_LEADERBOARD = "leaderboard.json";
    private static final String FILE_NAME_ACHIEVEMENTS = "achievement.json";
    private static final String FILE_NAME_TASK = "task.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void saveLeaderboard(Leaderboard leaderboard) {
        try (FileWriter writer= new FileWriter(FILE_NAME_LEADERBOARD)) {
            gson.toJson(leaderboard, writer);
            System.out.println("Leaderboard data saved to file");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Leaderboard loadLeaderboard() {
        try (FileReader reader = new FileReader(FILE_NAME_LEADERBOARD)) {
            return gson.fromJson(reader, Leaderboard.class);
        } catch (IOException e) {
            return new Leaderboard();
        }
    }

    public List<Achievement> loadAchievement() {
        try (FileReader reader = new FileReader(FILE_NAME_ACHIEVEMENTS)) {
            Type type = new TypeToken<List<Achievement>>(){}.getType();
            List<Achievement> achievements = gson.fromJson(reader, type);
            return achievements != null ? achievements : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveAchievement(List<Achievement> achievements) {
        try (FileWriter writer = new FileWriter(FILE_NAME_ACHIEVEMENTS)) {
            gson.toJson(achievements, writer);
            System.out.println("Achievement data saved to file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> loadTasks() {
        try (FileReader reader = new FileReader(FILE_NAME_TASK)) {
            Type type = new TypeToken<List<Task>>(){}.getType();
            List<Task> tasks = gson.fromJson(reader, type);
            return tasks != null ? tasks : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void saveTasks(List<Task> tasks) {
        try (FileWriter writer = new FileWriter(FILE_NAME_TASK)) {
            gson.toJson(tasks, writer);
            System.out.println("Task data saved to file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
