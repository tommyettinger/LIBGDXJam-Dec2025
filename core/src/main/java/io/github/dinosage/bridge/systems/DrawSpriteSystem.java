package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import io.github.dinosage.bridge.screens.GameScreen;
import io.github.dinosage.bridge.Maps;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.SpriteComponent;

public class DrawSpriteSystem extends EntitySystem {

    // Variables
    ImmutableArray<Entity> entities;
    GameScreen gameScreen;
    public Family family;


    public DrawSpriteSystem(int priority, GameScreen gameScreen) {
        super(priority);
        this.gameScreen = gameScreen;
        family = Family.all(SpriteComponent.class, PositionComponent.class)
            .get();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void update(float deltaTime) {
        gameScreen.gameView.apply();
        gameScreen.batch.setProjectionMatrix(gameScreen.gameView.getCamera().combined);
        gameScreen.batch.begin();

        for (Entity entity : entities) {
            SpriteComponent sc = Maps.SPRITE.get(entity);
            PositionComponent pc = Maps.POSITION.get(entity);
            sc.sprite.setPosition(pc.px,pc.py);
            sc.sprite.draw(gameScreen.batch);
        }
        gameScreen.batch.end();
    }
}
