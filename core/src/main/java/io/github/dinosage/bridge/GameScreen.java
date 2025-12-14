package io.github.dinosage.bridge;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.SpriteComponent;
import io.github.dinosage.bridge.components.VelocityComponent;
import io.github.dinosage.bridge.systems.BridgeSystem;
import io.github.dinosage.bridge.systems.DrawShapeSystem;
import io.github.dinosage.bridge.systems.DrawSpriteSystem;
import io.github.dinosage.bridge.systems.MovementSystem;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    // Variables
    public Engine engine;
    public Viewport gameView;
    public Entity player;

    // disposables
    public SpriteBatch batch;
    public Stage stage;
    public Skin skin;
    public BitmapFont font;
    public ShapeRenderer shapeRenderer;


    public GameScreen() {
        this.engine = new Engine();
        this.gameView = new ExtendViewport(16, 9);
        this.stage = new Stage(new ScreenViewport());
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {
        // Prepare your screen here.
        setupGame();
        setupUI();
    }

    @Override
    public void render(float delta) {
        // clear screen
        ScreenUtils.clear(Color.BLACK);

        engine.update(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        gameView.update(width, height, true);
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
        shapeRenderer.dispose();
        batch.dispose();
        stage.dispose();
        skin.dispose();
        font.dispose();
    }

    // Custom Functions
    public void setupGame() {
        // create and add systems
        MovementSystem movementSystem = new MovementSystem(-2, this);
        engine.addSystem(movementSystem);

        BridgeSystem bridgeSystem = new BridgeSystem(-1, this);
        engine.addSystem(bridgeSystem);

        DrawShapeSystem shapeSystem = new DrawShapeSystem(1, this);
        engine.addSystem(shapeSystem);

        DrawSpriteSystem spriteSystem = new DrawSpriteSystem(2, this);
        engine.addSystem(spriteSystem);

        // create and add player entity
        player = new Entity();
        engine.addEntity(player);

        player.add(new PositionComponent(7, 1));
        player.add(new VelocityComponent(0, 0));
        player.add(new SpriteComponent(new Texture("bucket.png"), 1, 1));
        player.add(new BoxShapeComponent(1, 1, Color.WHITE));

        // create first plank
        PositionComponent player_pc = Maps.POSITION.get(player);
        bridgeSystem.createPlank(player_pc.px, player_pc.py - 0.5f);

        // setup player input
        PlayerInputProcessor inputProcessor = new PlayerInputProcessor(this);
        //Gdx.input.setInputProcessor(inputProcessor);
    }

    public void setupUI() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        font = new BitmapFont(new FileHandle("added-fonts/arial-b-20.fnt"));

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.top().pad(50);
        stage.addActor(table);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        style.font = font;
        Label plankLabel = new Label("Planks: ", style);
        table.add(plankLabel).expandX().left();
    }
}
