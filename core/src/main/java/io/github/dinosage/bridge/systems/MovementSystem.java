package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import io.github.dinosage.bridge.GameAttr;
import io.github.dinosage.bridge.GameScreen;
import io.github.dinosage.bridge.Maps;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    GameScreen gameScreen;

    public MovementSystem(int priority, GameScreen gameScreen) {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get(), priority);
        this.gameScreen = gameScreen;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Maps.POSITION.get(entity);
        VelocityComponent velocity = Maps.VELOCITY.get(entity);

        position.px += velocity.vx * deltaTime;
        position.py += velocity.vy * deltaTime;

        // check if out of bounds
        if (position.deleteIfOutOfBounds) {
            if (position.px < 0 - GameAttr.OOB_BUFFER) {
                gameScreen.engine.removeEntity(entity);
            }
        }
    }
}
