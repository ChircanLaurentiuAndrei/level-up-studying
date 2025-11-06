# LevelUp: A Gamified Habit & Time Management Tracker for Students

**LevelUp** is a Java console-based project designed to help students build productive habits and manage their time more effectively — while keeping motivation high through gamification 🎯

Every completed task earns XP, achievements, and leaderboard ranks — turning daily productivity into a fun, rewarding experience!

---

## 🚀 Features

- 👤 **User Accounts** — Add multiple users and track their progress individually  
- ✅ **Tasks System** — Create and complete tasks to earn XP  
- 🏆 **Achievements** — Unlock milestones for completing tasks  
- 📈 **Leaderboard** — Compare users by XP in real-time  
- 💾 **Persistent Data** — All data saved and loaded from a local JSON file  
- ⚙️ **Configurable** — Use CLI args and/or system properties to choose data files and startup mode  
- 🧯 **Robust Error Handling** — Handles multiple IO/parse/input exceptions and uses custom domain exceptions  

---

## ▶️ How to Run

Build a runnable JAR (already provided under `target/`), then run with one of the following modes:

- Help
  ```bash
  java -jar target/LevelUp-standalone.jar -h
  ```
- Initialize demo data (tasks, achievements, leaderboard)
  ```bash
  java -jar target/LevelUp-standalone.jar -init
  ```
- Add a user
  ```bash
  java -jar target/LevelUp-standalone.jar -add Alice
  ```
- Launch the app for a user
  ```bash
  java -jar target/LevelUp-standalone.jar -launch Alice
  ```

### Optional configuration (system properties)
You can override the default JSON file locations using Java system properties:
```
-Dlevelup.leaderboard=path/to/leaderboard.json 
-Dlevelup.tasks=path/to/task.json 
-Dlevelup.achievements=path/to/achievement.json
```
Example:
```bash
java -Dlevelup.tasks=data/tasks.json -jar target/LevelUp-standalone.jar -init
```

---

## 🧱 Technologies Used

| Component | Technology |
|------------|-------------|
| Language | Java 21 |
| Build Tool | Maven |
| JSON Library | Gson (by Google) |
| IDE (recommended) | IntelliJ IDEA |

---

## 🧩 Design Overview (Diagram)

The following class diagram outlines core classes, interfaces and relationships. It also highlights collections (List, Set, Map) usage.

```mermaid
classDiagram
direction BT
class Achievement {
  - int numberOfTasksRequired
  - String description
  - String name
  + hashCode() int
  + toString() String
  + getName() String
  + getDescription() String
  + equals(Object) boolean
  + getNumberOfTasksRequired() int
}
class DuplicateUserException
class FileManager {
  - String FILE_NAME_LEADERBOARD
  - String FILE_NAME_TASK
  - String FILE_NAME_ACHIEVEMENTS
  - Gson gson
  + saveLeaderboard(Leaderboard) boolean
  + loadAchievement() List~Achievement~
  + loadTasks() List~Task~
  + saveTasks(List~Task~) boolean
  + loadLeaderboard() Leaderboard
  + saveAchievement(List~Achievement~) boolean
}
class Leaderboard {
  - List~User~ userList
  + toString() String
  + getUserList() List~User~
  + setUserList(List~User~) void
  + sortLeaderboard() void
}
class Main {
  + main(String[]) void
}
class Rewardable {
<<Interface>>
  + rewardUser(Task) void
  + rewardAchievement(Achievement) void
}
class Task {
  - String name
  - int xp
  + equals(Object) boolean
  + getXp() int
  + getName() String
  + toString() String
  + hashCode() int
}
class TaskTracker {
  - List~User~ userList
  - List~Task~ taskList
  - FileManager fm
  - Map~String, User~ userMap
  - List~Achievement~ achievements
  - Leaderboard lb
  + initialize() void
  - isValidUsername(String) boolean
  + checkAchievements(User, List~Achievement~) void
  + randomTasks() List~Task~
  + toString() String
  + getUser(String) User
  + getLeaderboard() Leaderboard
  + rewardUser(User, Task) void
  + addUser(String) void
}
class Trackable {
<<Interface>>
  + initialize() void
  + toString() String
}
class User {
  - int xp
  - List~Task~ taskList
  - Set~Achievement~ achievementList
  - String name
  ~ int numberOfTasksCompleted
  + getNumberOfTasks() int
  + rewardUser(Task) void
  + getTaskList() List~Task~
  + rewardAchievement(Achievement) void
  + toString() String
  + getAchievementList() Set~Achievement~
  + getName() String
  + addTask(Task) void
  + setTaskList(List~Task~) void
  + getXp() int
}
class UserMenu {
  - TaskTracker taskTracker
  - String username
  - Scanner scanner
  + backToMenu() void
  + completeTask() void
  + runMenuChoice(int) void
  + listTasks() void
  + getChoice() int
  + displayMenu() void
  + clearScreen() void
  + listLeaderboard() void
  + listAchievements() void
}
class UserNotFoundException

Leaderboard "1" *--> "userList *" User 
TaskTracker "1" *--> "achievements *" Achievement 
TaskTracker "1" *--> "fm 1" FileManager 
TaskTracker "1" *--> "lb 1" Leaderboard 
TaskTracker "1" *--> "taskList *" Task 
TaskTracker  ..>  Trackable 
TaskTracker "1" *--> "userList *" User 
User "1" *--> "achievementList *" Achievement 
User  ..>  Rewardable 
User "1" *--> "taskList *" Task 
UserMenu "1" *--> "taskTracker 1" TaskTracker 

```

---

## ✅ Rubric Checklist Mapping

- 4+ classes and 2+ interfaces: `TaskTracker`, `FileManager`, `User`, `Task`, `Achievement`, `Leaderboard` + interfaces `Trackable`, `Rewardable`
- Uses at least two collections: `List`, `Set`, and `Map` are used
- Stores/reads data from files: JSON via `FileManager` (Gson)
- Config/program arguments: CLI modes (`-h`, `-init`, `-add`, `-launch`) and optional `-Dlevelup.*` properties
- Handles 3+ language exceptions: `FileNotFoundException`, `IOException`, `JsonSyntaxException`, `InputMismatchException`
- 2 custom exceptions: `DuplicateUserException`, `UserNotFoundException`
- Input validation: username format, menu choice validation, bounds checks on task selection

---

This project is open-source and available under the [MIT License](LICENSE)
