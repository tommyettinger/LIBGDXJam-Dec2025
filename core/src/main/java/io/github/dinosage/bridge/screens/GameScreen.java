package io.github.dinosage.bridge.screens;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

import io.github.dinosage.bridge.Core;
import io.github.dinosage.bridge.GameAttr;
import io.github.dinosage.bridge.Maps;
import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.VelocityComponent;
import io.github.dinosage.bridge.systems.BridgeSystem;
import io.github.dinosage.bridge.systems.CollisionSystem;
import io.github.dinosage.bridge.systems.DrawShapeSystem;
import io.github.dinosage.bridge.systems.DrawSpriteSystem;
import io.github.dinosage.bridge.systems.MovementSystem;
import io.github.dinosage.bridge.systems.PlankItemSystem;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {

    // Variables
    public Engine engine;
    public Viewport gameView;
    public Entity player;


    // game - related
    public Core game;
    public int score;
    public int plankCount;

    // ui elements
    Label plankCountValue;
    Label scoreValue;

    // disposables
    public SpriteBatch batch;
    public Stage stage;
    public Skin skin;
    public BitmapFont font;
    public ShapeRenderer shapeRenderer;


    public GameScreen(Core core) {
        this.game = core;
        this.engine = new Engine();
        this.gameView = new ExtendViewport(32, 18);
    }

    @Override
    public void show() {
        // Prepare your screen here.
        this.stage = new Stage(new ScreenViewport());
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();

        setupGame();
        setupUI();
    }

    @Override
    public void render(float delta) {
        // clear screen
        ScreenUtils.clear(Color.BLACK);

        engine.update(delta);
        updateUI(delta);
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your screen here. The parameters represent the new window size.
        gameView.update(width, height, true);
        stage.getViewport().update(width, height);
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
        engine.removeAllEntities();
        engine.removeAllSystems();
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

    // Private Functions
    private void setupGame() {
        // create and add systems
        MovementSystem movementSystem = new MovementSystem(-3, this);
        engine.addSystem(movementSystem);

        PlankItemSystem plankItemSystem = new PlankItemSystem(-2, this);
        engine.addSystem(plankItemSystem);

        BridgeSystem bridgeSystem = new BridgeSystem(-1, this);
        engine.addSystem(bridgeSystem);

        CollisionSystem collisionSystem = new CollisionSystem(0, this);
        engine.addSystem(collisionSystem);

        DrawShapeSystem shapeSystem = new DrawShapeSystem(1, this);
        engine.addSystem(shapeSystem);

        DrawSpriteSystem spriteSystem = new DrawSpriteSystem(2, this);
        engine.addSystem(spriteSystem);

        // create and add player entity
        player = new Entity();
        engine.addEntity(player);

        player.add(new PositionComponent(7, 1));
        player.add(new VelocityComponent(0, 0));
        player.add(new BoxShapeComponent(1, 1, Color.WHITE));

        // create first plank
        PositionComponent player_pc = Maps.POSITION.get(player);
        bridgeSystem.createPlank(player_pc.px, player_pc.py - 0.5f);

        plankCount = GameAttr.START_PLANK_COUNT;
    }

    private void setupUI() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        font = new BitmapFont(Gdx.files.internal("added-fonts/arial-b-20.fnt"));

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(GameAttr.DEBUG_UI);
        table.top().left().pad(50);
        stage.addActor(table);

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        style.font = font;

        Label plankCountText = new Label("Planks: ", style);
        table.add(plankCountText).left();

        plankCountValue = new Label("0", style);
        table.add(plankCountValue).left();

        table.row();

        Label scoreText = new Label("Score: ", style);
        table.add(scoreText).left();

        scoreValue = new Label("0", style);
        table.add(scoreValue).left();
    }

    private void updateUI(float delta) {
        plankCountValue.setText(plankCount);
        scoreValue.setText(score);

        stage.act(delta);
        stage.draw();
    }

    // Public Functions
    public void gameOver() {
        game.score = score;
        game.switchScreen(Core.SCREEN_GAME_OVER);
    }
}
