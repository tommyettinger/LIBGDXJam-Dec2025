package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.systems.IntervalSystem;

import io.github.dinosage.bridge.GameAttr;

public class DifficultySystem extends IntervalSystem {

    public DifficultySystem(int priority) {
        super(5f, priority);
    }

    @Override
    protected void updateInterval() {
        GameAttr.PLANK_POWER_BOOST += 1;
        GameAttr.PLANK_INTERVAL -= 0.1f;
        GameAttr.OBSTACLE_INTERVAL -= 0.2f;
        GameAttr.BRIDGE_VEL_X -= 0.1f;
        GameAttr.OBSTACLE_VEL_X -= 0.1f;
        GameAttr.PLANK_VEL_X -= 0.1f;
        GameAttr.SCORE_MULTIPLIER += 100f;

    }
}
