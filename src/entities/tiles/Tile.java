package entities.tiles;

import entities.Entity;
import enums.EntityType;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public abstract class Tile extends Entity {
    public Tile(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, EntityType entityType) {
        super(model, position, rotX, rotY, rotZ, scale, entityType);
    }
}
