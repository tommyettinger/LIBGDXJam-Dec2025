package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.math.Rectangle;


import io.github.dinosage.bridge.GameAttr;
import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.CollisionComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.screens.GameScreen;

public class CollisionSystem extends EntitySystem {

    GameScreen gameScreen;
    Family family;
    ImmutableArray<Entity> entities;

    public CollisionSystem(int priority, GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        family = Family.all(PositionComponent.class, CollisionComponent.class, BoxShapeComponent.class).get();
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        entities = null;
    }

    @Override
    public void update(float deltaTime) {
        PositionComponent player_pc = gameScreen.player.getComponent(PositionComponent.class);
        BoxShapeComponent player_bsc = gameScreen.player.getComponent(BoxShapeComponent.class);
        Rectangle player_rect = new Rectangle(player_pc.px, player_pc.py, player_bsc.width, player_bsc.height);

        for (Entity entity : entities) {
            PositionComponent pc = entity.getComponent(PositionComponent.class);
            BoxShapeComponent bsc = entity.getComponent(BoxShapeComponent.class);

            Rectangle rect = new Rectangle(pc.px, pc.py, bsc.width, bsc.height);
            if (player_rect.overlaps(rect)) {
                gameScreen.plankCount += GameAttr.PLANK_POWER_BOOST;
                getEngine().removeEntity(entity);
            }
        }
    }
}
