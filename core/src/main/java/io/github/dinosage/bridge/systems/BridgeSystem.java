package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

import org.w3c.dom.css.Rect;

import java.util.LinkedList;

import io.github.dinosage.bridge.GameScreen;
import io.github.dinosage.bridge.Maps;
import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class BridgeSystem extends EntitySystem {

    LinkedList<Entity> planks;
    GameScreen gameScreen;


    public BridgeSystem(int priority, GameScreen gameScreen) {
        super(priority);
        planks = new LinkedList<>();
        this.gameScreen = gameScreen;
    }

    @Override
    public void update(float deltaTime) {
        PositionComponent plank_pc = Maps.POSITION.get(planks.getLast());
        BoxShapeComponent plank_bsc = Maps.BOX_SHAPE.get(planks.getLast());

        PositionComponent player_pc = Maps.POSITION.get(gameScreen.player);
        BoxShapeComponent player_bsc = Maps.BOX_SHAPE.get(gameScreen.player);

        float playerRightBound = player_pc.px + player_bsc.width;
        float plankRightBound = plank_pc.px + plank_bsc.width;

        if (playerRightBound - plankRightBound > 0) {
            // create new plank
            float height = plank_pc.py;
            if (Gdx.input.isKeyPressed(Input.Keys.W) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
              height += plank_bsc.height;
              player_pc.py += plank_bsc.height;
            } else if (Gdx.input.isKeyPressed(Input.Keys.S) && !Gdx.input.isKeyPressed(Input.Keys.W)) {
              height -= plank_bsc.height;
              player_pc.py -= plank_bsc.height;
            }

            Gdx.app.log("TEST", "Creating new plank: " + plankRightBound);
            createPlank(plankRightBound, height);
        }
    }

    public void createPlank(float px, float py) {
        Entity plank = new Entity();
        gameScreen.engine.addEntity(plank);

        plank.add(new PositionComponent(px, py));
        plank.add(new VelocityComponent(-5f, 0));
        plank.add(new BoxShapeComponent(3, 0.5f, Color.BROWN));

        planks.add(plank);
    }
}
