package io.github.dinosage.bridge;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.SpriteComponent;
import io.github.dinosage.bridge.components.VelocityComponent;
import io.github.dinosage.bridge.systems.DrawSpriteSystem;
import io.github.dinosage.bridge.systems.MovementSystem;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    // Variables
    public Engine engine;
    public Viewport viewport;
    public SpriteBatch batch;
    private Texture texture;

    public GameScreen() {
        this.engine = new Engine();
        this.viewport = new ExtendViewport(16, 9);
        this.batch = new SpriteBatch();
        texture = new Texture("bucket.png");

        setupGame();
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // clear screen
        ScreenUtils.clear(Color.BLACK);

        engine.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
        dispose();
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }

    // Custom Functions
    public void setupGame() {
        // create and add systems
        MovementSystem movementSystem = new MovementSystem();
        engine.addSystem(movementSystem);

        DrawSpriteSystem spriteSystem = new DrawSpriteSystem(1, this);
        engine.addSystem(spriteSystem);

        // create player entity
        Entity player = new Entity();
        engine.addEntity(player);

        player.add(new PositionComponent());
        player.add(new VelocityComponent());
        player.add(new SpriteComponent("bucket.png", 1, 1));

        PositionComponent pc = Mappers.pm.get(player);
        pc.px = 1;
        pc.py = 1;

        VelocityComponent vc = Mappers.vm.get(player);
        vc.vx = 1;
        vc.vy = 1;
    }
}
