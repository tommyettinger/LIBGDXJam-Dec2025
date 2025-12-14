package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

import java.util.LinkedList;

import io.github.dinosage.bridge.GameAttr;
import io.github.dinosage.bridge.screens.GameScreen;
import io.github.dinosage.bridge.Maps;
import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class BridgeSystem extends EntitySystem {

    LinkedList<Entity> planks;
    GameScreen gameScreen;
    BridgeCleanupListener bridgeCleanup;

    public BridgeSystem(int priority, GameScreen gameScreen) {
        super(priority);
        planks = new LinkedList<>();
        this.gameScreen = gameScreen;
    }

    @Override
    public void addedToEngine(Engine engine) {
        bridgeCleanup = new BridgeCleanupListener(planks);
        engine.addEntityListener(bridgeCleanup);
    }

    @Override
    public void removedFromEngine(Engine engine) {
        engine.removeEntityListener(bridgeCleanup);
    }

    @Override
    public void update(float deltaTime) {
        PositionComponent plank_pc = Maps.POSITION.get(planks.getLast());
        BoxShapeComponent plank_bsc = Maps.BOX_SHAPE.get(planks.getLast());

        PositionComponent player_pc = Maps.POSITION.get(gameScreen.player);
        BoxShapeComponent player_bsc = Maps.BOX_SHAPE.get(gameScreen.player);

        float playerRightBound = player_pc.px + player_bsc.width;
        float plankRightBound = plank_pc.px + plank_bsc.width;

        if (playerRightBound - plankRightBound > player_bsc.width / 2) {
            // create new plank
            float height = plank_pc.py;
            if (Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
              height += plank_bsc.height;
              player_pc.py += plank_bsc.height;
            } else if (Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.W)) {
              height -= plank_bsc.height;
              player_pc.py -= plank_bsc.height;
            }

            createPlank(plankRightBound, height);
            gameScreen.plankCount--;
            if (gameScreen.plankCount < 0) {
                gameScreen.gameOver();
            }
        }

        Gdx.app.log("TEST", "Plank count: " + planks.size());
    }

    public void createPlank(float px, float py) {
        Entity plank = new Entity();
        gameScreen.engine.addEntity(plank);

        plank.add(new PositionComponent(px, py, true));
        plank.add(new VelocityComponent(GameAttr.PLANK_VEL_X, 0));
        plank.add(new BoxShapeComponent(GameAttr.PLANK_WIDTH, GameAttr.PLANK_HEIGHT, Color.BROWN, true));

        planks.add(plank);
    }
}
