package io.github.dinosage.bridge;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.text.View;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    // Variables
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Viewport viewport;
    private SpriteBatch batch;
    private Texture texture;

    public GameScreen() {
        this.world = new World(new Vector2(0, -10f), true);
        this.debugRenderer = new Box2DDebugRenderer();
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

        // step physics simulation
        world.step(1/60f, 6, 2);

        // actual render
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(texture, 0, 0, 1, 1);
        batch.end();

        // debug physics render
        debugRenderer.render(world, viewport.getCamera().combined);
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
        world.dispose();
        debugRenderer.dispose();
    }

    // Custom Functions
    public void setupGame() {
        // create platform bodydef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2);

        // create platform body
        Body body = world.createBody(bodyDef);

        // create fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(viewport.getWorldWidth()/2, 0.5f);
        body.createFixture(shape, 0f);

        shape.dispose();
    }
}
