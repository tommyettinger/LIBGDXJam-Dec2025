package io.github.dinosage.bridge.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent implements Component {
    public float px;
    public float py;
    public boolean deleteIfOutOfBounds = false;


    public PositionComponent(float x, float y) {
        this.px = x;
        this.py = y;
    }

    public PositionComponent(float x, float y, boolean deleteIfOutOfBounds) {
        this.px = x;
        this.py = y;
        this.deleteIfOutOfBounds = deleteIfOutOfBounds;
    }
}
