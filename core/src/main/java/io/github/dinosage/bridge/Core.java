package io.github.dinosage.bridge;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Box2D;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {

    @Override
    public void create() {
        Box2D.init();
        setScreen(new GameScreen());
    }

    @Override
    public void render() {
        super.render();
    }
}
