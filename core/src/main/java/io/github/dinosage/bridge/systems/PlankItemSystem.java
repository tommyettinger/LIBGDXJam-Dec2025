package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import io.github.dinosage.bridge.GameAttr;
import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;
import io.github.dinosage.bridge.screens.GameScreen;

public class PlankItemSystem extends IntervalSystem {

    GameScreen gameScreen;

    public PlankItemSystem(int priority, GameScreen gameScreen) {
        super(GameAttr.PLANK_SPAWN_INTERVAL, priority);
        this.gameScreen = gameScreen;
    }

    @Override
    protected void updateInterval() {
        // spawn plank item off screen

        Entity plankItem = new Entity();
        getEngine().addEntity(plankItem);

        float height = MathUtils.random(0, gameScreen.gameView.getWorldHeight());
        plankItem.add(new PositionComponent(gameScreen.gameView.getWorldWidth() * 1.5f, height, true));
        plankItem.add(new VelocityComponent(GameAttr.PLANK_ITEM_VEL_X, 0));
        plankItem.add(new BoxShapeComponent(2f, 2f, Color.BROWN, true));
    }
}
