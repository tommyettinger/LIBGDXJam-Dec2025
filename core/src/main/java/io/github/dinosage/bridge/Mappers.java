package io.github.dinosage.bridge;

import com.badlogic.ashley.core.ComponentMapper;

import io.github.dinosage.bridge.components.PositionComponent;
import io.github.dinosage.bridge.components.SpriteComponent;
import io.github.dinosage.bridge.components.VelocityComponent;

public class Mappers {
    public static final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);


}
