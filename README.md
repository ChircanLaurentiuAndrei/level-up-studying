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
  + Achievement(String, String, int) 
  - String description
  - int numberOfTasksRequired
  - String name
  + getDescription() String
  + getNumberOfTasksRequired() int
  + getName() String
  + toString() String
}
class DuplicateUserException {
  + DuplicateUserException(String) 
}
class FileManager {
  + FileManager() 
  - Gson gson
  - String FILE_NAME_ACHIEVEMENTS
  - String FILE_NAME_TASK
  - String FILE_NAME_LEADERBOARD
  + saveAchievement(List~Achievement~) boolean
  + saveTasks(List~Task~) boolean
  + loadAchievement() List~Achievement~
  + saveLeaderboard(Leaderboard) boolean
  + loadTasks() List~Task~
  + loadLeaderboard() Leaderboard
}
class Leaderboard {
  + Leaderboard() 
  - List~User~ userList
  + getUserList() List~User~
  + sortLeaderboard() void
  + setUserList(List~User~) void
  + toString() String
}
class Main {
  + Main() 
  + main(String[]) void
}
class Rewardable {
<<Interface>>
  + rewardUser(Task) void
  + rewardAchievement(Achievement) void
}
class Task {
  + Task(String, int) 
  - int xp
  - String name
  + getName() String
  + toString() String
  + getXp() int
}
class TaskTracker {
  + TaskTracker() 
  - FileManager fm
  - List~User~ userList
  - Map~String, User~ userMap
  - List~Task~ taskList
  - Leaderboard lb
  - List~Achievement~ achievements
  + initialize() void
  + addUser(String) void
  + getLeaderboard() Leaderboard
  + toString() String
  + randomTasks() List~Task~
  - isValidUsername(String) boolean
  + checkAchievements(User, List~Achievement~) void
  + getUser(String) User
  + rewardUser(User, Task) void
}
class Trackable {
<<Interface>>
  + toString() String
  + initialize() void
}
class User {
  + User(String, int, List~Task~, Set~Achievement~, int) 
  - String name
  - Set~Achievement~ achievementList
  - int xp
  - List~Task~ taskList
  ~ int numberOfTasksCompleted
  + getNumberOfTasks() int
  + getXp() int
  + rewardAchievement(Achievement) void
  + setTaskList(List~Task~) void
  + addTask(Task) void
  + toString() String
  + getAchievementList() Set~Achievement~
  + rewardUser(Task) void
  + getName() String
  + getTaskList() List~Task~
}
class UserMenu {
  + UserMenu(TaskTracker, String) 
  - TaskTracker taskTracker
  - Scanner scanner
  - String username
  + clearScreen() void
  + backToMenu() void
  + displayMenu() void
  + listLeaderboard() void
  + runMenuChoice(int) void
  + getChoice() int
  + completeTask() void
  + listTasks() void
  + listAchievements() void
}
class UserNotFoundException {
  + UserNotFoundException(String) 
}

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
