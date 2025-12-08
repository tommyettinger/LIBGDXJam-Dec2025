package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

import java.util.Map;

import io.github.dinosage.bridge.GameScreen;
import io.github.dinosage.bridge.Mappers;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.SpriteComponent;

public class DrawSpriteSystem extends EntitySystem {

    // Variables
    ImmutableArray<Entity> entities;
    GameScreen gameScreen;

    public DrawSpriteSystem(int priority, GameScreen gameScreen){
        super(priority);
        this.gameScreen = gameScreen;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(SpriteComponent.class, PositionComponent.class).get());
    }

    @Override
    public void removedFromEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(SpriteComponent.class, PositionComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        gameScreen.batch.setProjectionMatrix(gameScreen.viewport.getCamera().combined);
        gameScreen.batch.begin();

        for (Entity entity : entities) {
            SpriteComponent sc = Mappers.sm.get(entity);
            PositionComponent pc = Mappers.pm.get(entity);

            sc.sprite.setPosition(pc.px, pc.py);
            sc.sprite.draw(gameScreen.batch);
        }

        gameScreen.batch.end();
    }

}
