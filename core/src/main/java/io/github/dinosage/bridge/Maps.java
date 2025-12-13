package io.github.dinosage.bridge;

import com.badlogic.ashley.core.ComponentMapper;

import io.github.dinosage.bridge.components.BoxShapeComponent;
import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.SpriteComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class Maps {
    public static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<SpriteComponent> SPRITE = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<BoxShapeComponent> BOX_SHAPE = ComponentMapper.getFor(BoxShapeComponent.class);


}
