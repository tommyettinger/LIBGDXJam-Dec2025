package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import io.github.dinosage.bridge.Maps;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Maps.POSITION.get(entity);
        VelocityComponent velocity = Maps.VELOCITY.get(entity);

        position.px += velocity.vx * deltaTime;
        position.py += velocity.vy * deltaTime;
    }
}
