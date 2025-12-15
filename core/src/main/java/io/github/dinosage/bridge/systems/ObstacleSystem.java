package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import io.github.dinosage.bridge.GameAttr;
import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.CollisionComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;
import io.github.dinosage.bridge.screens.GameScreen;

public class ObstacleSystem extends IntervalSystem {

    GameScreen gameScreen;

    public ObstacleSystem(int priority, GameScreen gameScreen) {
        super(GameAttr.OBSTACLE_INTERVAL, priority);
        this.gameScreen = gameScreen;
    }

    @Override
    protected void updateInterval() {
        // spawn plank item off screen

        com.badlogic.ashley.core.Entity plankItem = new Entity();
        getEngine().addEntity(plankItem);

        float height = MathUtils.random(0, gameScreen.gameView.getWorldHeight());
        plankItem.add(new PositionComponent(gameScreen.gameView.getWorldWidth() * 1.5f, height, true));
        plankItem.add(new VelocityComponent(GameAttr.OBSTACLE_VEL_X, 0));
        plankItem.add(new BoxShapeComponent(GameAttr.OBSTACLE_WIDTH, GameAttr.OBSTACLE_HEIGHT, Color.DARK_GRAY, false));
        plankItem.add(new CollisionComponent());
    }
}
