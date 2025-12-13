package io.github.dinosage.bridge;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;

import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PlayerAttributes;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class PlayerInputProcessor implements InputProcessor {

    // Variables
    Entity player;
    GameScreen gameScreen;


    public PlayerInputProcessor(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        player = gameScreen.player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            // move player up
            VelocityComponent vc = Maps.VELOCITY.get(player);
            vc.vy += PlayerAttributes.moveSpeedY;
            return true;
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            // move player down
            VelocityComponent vc = Maps.VELOCITY.get(player);
            vc.vy -= PlayerAttributes.moveSpeedY;
            return true;
        } else if (keycode == Input.Keys.SPACE) {
            // spawn floorboard
            Entity floorBoard = new Entity();
            gameScreen.engine.addEntity(floorBoard);

            floorBoard.add(new PositionComponent(2, 1));
            floorBoard.add(new VelocityComponent(-5f, 0));
            floorBoard.add(new BoxShapeComponent(3, 0.5f, Color.BROWN));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            // stop moving player up
            VelocityComponent vc = Maps.VELOCITY.get(player);
            vc.vy -= PlayerAttributes.moveSpeedY;
            return true;
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            // stop moving player down
            VelocityComponent vc = Maps.VELOCITY.get(player);
            vc.vy += PlayerAttributes.moveSpeedY;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
