package io.github.dinosage.bridge.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BoxShapeComponent implements Component {
    public float width;
    public float height;
    public Color color;
    public boolean border = false;

    public BoxShapeComponent(float width, float height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public BoxShapeComponent(float width, float height, Color color, boolean border) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.border = border;
    }
}
