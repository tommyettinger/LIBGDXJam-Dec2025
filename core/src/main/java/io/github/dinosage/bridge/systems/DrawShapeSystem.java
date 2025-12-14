package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.dinosage.bridge.GameAttr;
import io.github.dinosage.bridge.screens.GameScreen;
import io.github.dinosage.bridge.Maps;
import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;

public class DrawShapeSystem extends EntitySystem {

    // Variables
    ImmutableArray<Entity> entities;
    GameScreen gameScreen;
    public Family family;


    public DrawShapeSystem(int priority, GameScreen gameScreen) {
        super(priority);
        this.gameScreen = gameScreen;
        family = Family.all(PositionComponent.class)
            .one(BoxShapeComponent.class)
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
        ShapeRenderer renderer = gameScreen.shapeRenderer;
        renderer.setProjectionMatrix(gameScreen.gameView.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Entity entity : entities) {
            BoxShapeComponent bsc = Maps.BOX_SHAPE.get(entity);
            PositionComponent pc = Maps.POSITION.get(entity);
            renderer.setColor(bsc.color);
            renderer.rect(pc.px, pc.py, bsc.width, bsc.height);

            if (bsc.border) {
                float left = pc.px;
                float right = pc.px + bsc.width;
                float bottom = pc.py;
                float top = pc.py + bsc.height;

                renderer.setColor(Color.GRAY);
                renderer.rectLine(left, bottom, right, bottom, GameAttr.SHAPE_BORDER);
                renderer.rectLine(right, bottom, right, top, GameAttr.SHAPE_BORDER);
                renderer.rectLine(right, top, left, top, GameAttr.SHAPE_BORDER);
                renderer.rectLine(left, top, left, bottom, GameAttr.SHAPE_BORDER);
            }

        }
        renderer.end();
    }
}
