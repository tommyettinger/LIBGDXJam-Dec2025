package io.github.dinosage.bridge.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {
    public Sprite sprite;

    public SpriteComponent(String internalPath, float width, float height) {
        sprite = new Sprite(new Texture(internalPath));
        sprite.setSize(width, height);
    }
}
