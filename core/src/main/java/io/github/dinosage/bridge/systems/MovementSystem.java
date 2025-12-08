package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import java.util.Map;

import io.github.dinosage.bridge.Mappers;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class MovementSystem extends IteratingSystem {

    // Variables

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = Mappers.pm.get(entity);
        VelocityComponent velocity = Mappers.vm.get(entity);

        position.px += velocity.vx * deltaTime;
        position.py += velocity.vy * deltaTime;
    }
}
