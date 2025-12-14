package io.github.dinosage.bridge.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;

import java.util.LinkedList;

public class BridgeCleanupListener implements EntityListener {

    LinkedList<Entity> planks;

    public BridgeCleanupListener(LinkedList<Entity> planks) {
        this.planks = planks;
    }

    @Override
    public void entityAdded(Entity entity) {
    }

    @Override
    public void entityRemoved(Entity entity) {
        this.planks.remove(entity);
    }
}
