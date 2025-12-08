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

import io.github.dinosage.bridge.components.PosVelComponent;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    // Variables
    private Engine engine;
    private Viewport viewport;
    private SpriteBatch batch;
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

        // actual render
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(texture, 0, 0, 1, 1);
        batch.end();
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
        // create player entity
        Entity player = new Entity();
        engine.addEntity(player);

        player.add(new PosVelComponent());
    }
}
