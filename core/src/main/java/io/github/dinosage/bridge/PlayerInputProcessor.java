package io.github.dinosage.bridge;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import io.github.dinosage.bridge.components.PlayerAttributes;
import io.github.dinosage.bridge.components.VelocityComponent;

public class PlayerInputProcessor implements InputProcessor {

    // Variables
    Entity player;

    public PlayerInputProcessor(Entity player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            VelocityComponent vc = Mappers.vm.get(player);
            vc.vy += PlayerAttributes.moveSpeedY;
            return true;
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            VelocityComponent vc = Mappers.vm.get(player);
            vc.vy -= PlayerAttributes.moveSpeedY;
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP) {
            VelocityComponent vc = Mappers.vm.get(player);
            vc.vy -= PlayerAttributes.moveSpeedY;
            return true;
        } else if (keycode == Input.Keys.S || keycode == Input.Keys.DOWN) {
            VelocityComponent vc = Mappers.vm.get(player);
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
