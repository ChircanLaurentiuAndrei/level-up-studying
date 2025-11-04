package com.levelup.interfaces;

import com.levelup.model.Achievement;
import com.levelup.model.Task;

public interface Rewardable {
    void rewardUser(Task task);
    void rewardAchievement(Achievement achievement);
}
