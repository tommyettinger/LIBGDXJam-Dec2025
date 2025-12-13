package io.github.dinosage.bridge;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.SpriteComponent;
import io.github.dinosage.bridge.components.VelocityComponent;
import io.github.dinosage.bridge.systems.DrawShapeSystem;
import io.github.dinosage.bridge.systems.DrawSpriteSystem;
import io.github.dinosage.bridge.systems.MovementSystem;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    // Variables
    public Engine engine;
    public Viewport viewport;
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    private Texture texture;

    public GameScreen() {
        this.engine = new Engine();
        this.viewport = new ExtendViewport(16, 9);
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        texture = new Texture("bucket.png");
    }

    @Override
    public void show() {
        // Prepare your screen here.
        setupGame();
    }

    @Override
    public void render(float delta) {
        // clear screen
        ScreenUtils.clear(Color.BLACK);

        engine.update(delta);
        Gdx.app.log("Test", "In Game Screen");
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

        DrawShapeSystem shapeSystem = new DrawShapeSystem(1, this);
        engine.addSystem(shapeSystem);

        DrawSpriteSystem spriteSystem = new DrawSpriteSystem(2, this);
        engine.addSystem(spriteSystem);

        // create and add player entity
        Entity player = new Entity();
        engine.addEntity(player);

        player.add(new PositionComponent(0, 0));
        player.add(new VelocityComponent());
        player.add(new SpriteComponent(new Texture("bucket.png"), 1, 1));
        player.add(new BoxShapeComponent(1, 1, Color.WHITE));

        // setup player input
        PlayerInputProcessor inputProcessor = new PlayerInputProcessor(player);
        Gdx.input.setInputProcessor(inputProcessor);
    }
}
