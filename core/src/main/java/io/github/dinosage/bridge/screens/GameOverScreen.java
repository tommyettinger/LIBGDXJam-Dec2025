package io.github.dinosage.bridge.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import io.github.dinosage.bridge.Core;
import io.github.dinosage.bridge.GameAttr;

public class GameOverScreen implements Screen {

    private final Core game;
    private Stage stage;
    private Skin skin;
    private BitmapFont font_30;
    private BitmapFont font_20;

    public GameOverScreen(Core core) {
        this.game = core;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(GameAttr.DEBUG_UI);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        font_20 = new BitmapFont(Gdx.files.internal("added-fonts/arial-b-20.fnt"));
        font_30 = new BitmapFont(Gdx.files.internal("added-fonts/arial-b-30.fnt"));

        Label.LabelStyle style = new Label.LabelStyle();
        style.fontColor = Color.WHITE;
        style.font = font_30;

        Label gameOverLabel = new Label("Game Over", style);
        table.add(gameOverLabel).colspan(2);
        table.row();

        Label.LabelStyle style2 = new Label.LabelStyle();
        style2.fontColor = Color.WHITE;
        style2.font = font_20;

        Label scoreLabel = new Label("Score: ", style2);
        table.add(scoreLabel);

        Label scoreValue = new Label(String.valueOf(game.score), style2);
        table.add(scoreValue);

        table.row();

        TextButton button = new TextButton("Replay", skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.switchScreen(Core.SCREEN_MENU);
            }
        });
        table.add(button).width(200f).height(40f).colspan(2);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.app.postRunnable(this::dispose);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        font_20.dispose();
        font_30.dispose();
    }
}
