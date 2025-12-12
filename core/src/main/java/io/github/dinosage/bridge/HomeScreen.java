package io.github.dinosage.bridge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javax.swing.ViewportLayout;
import javax.swing.text.View;

public class HomeScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private final Core game;

    public HomeScreen(Core core) {
        this.game = core;
    }

    @Override
    public void show() {
        stage = new Stage();
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        Gdx.input.setInputProcessor(stage);

        // setup ui
        TextButton button = new TextButton("Start Game", skin);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.switchScreen(Core.SCREEN_GAME);
            }
        });

        table.add(button).width(200f).height(40f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        stage.act(delta);
        stage.draw();

        Gdx.app.log("Test", "In Home Screen");
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
