package entities.entity;

import entities.Entity;
import enums.EntityType;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Obstacle extends Entity {
    public Obstacle(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, EntityType entityType) {
        super(model, position, rotX, rotY, rotZ, scale, entityType);
    }

    @Override
    public void tick() {

    }
}
