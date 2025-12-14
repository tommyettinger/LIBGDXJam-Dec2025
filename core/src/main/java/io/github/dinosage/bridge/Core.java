package io.github.dinosage.bridge;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.box2d.Box2D;

import io.github.dinosage.bridge.screens.GameOverScreen;
import io.github.dinosage.bridge.screens.GameScreen;
import io.github.dinosage.bridge.screens.HomeScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core extends Game {

    public static final int SCREEN_MENU = 0;
    public static final int SCREEN_GAME = 1;
    public static final int SCREEN_GAME_OVER = 2;

    public HomeScreen homeScreen;
    private GameScreen gameScreen;
    private GameOverScreen gameOverScreen;


    @Override
    public void create() {
        Box2D.init();
        homeScreen = new HomeScreen(this);
        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        switchScreen(SCREEN_MENU);
    }

    @Override
    public void render() {
        super.render();
    }

    public void switchScreen(int screen) {
        switch (screen) {
            case SCREEN_MENU:
                setScreen(homeScreen);
                break;
            case SCREEN_GAME:
                setScreen(gameScreen);
                break;
            case SCREEN_GAME_OVER:
                setScreen(gameOverScreen);
                break;
            default:
                break;
        }
    }
}
