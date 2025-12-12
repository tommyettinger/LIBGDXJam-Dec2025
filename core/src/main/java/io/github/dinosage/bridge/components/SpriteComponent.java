package io.github.dinosage.bridge.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {
    public Sprite sprite;

    public SpriteComponent(String internalPath, float width, float height) {
        Texture texture = new Texture(internalPath);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        texture.dispose();
    }
}
