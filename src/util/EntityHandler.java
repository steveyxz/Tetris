package util;

import entities.Camera;
import entities.Entity;
import enums.EntityType;
import renderEngine.Renderer;
import shaders.texture.TextureShader;

import java.util.ArrayList;

public class EntityHandler {

    private Camera camera;
    private TextureShader shader;
    private Renderer renderer;

    public ArrayList<Entity> entities = new ArrayList<>();

    public EntityHandler(Camera camera, TextureShader shader, Renderer renderer) {
        this.camera = camera;
        this.shader = shader;
        this.renderer = renderer;
    }

    public void tick() {
        for (Entity entity : entities) {
            entity.tick();
        }
    }

    public void render() {
        for (Entity entity : entities) {
            renderer.render(entity, shader);
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void clearEntities() {
        entities.clear();
    }

    public ArrayList<Entity> getEntities(EntityType type) {
        ArrayList<Entity> returned = new ArrayList<>();
        for (Entity e : entities) {
            if (e.getEntityType() == type) {
                returned.add(e);
            }
        }
        return returned;
    }

    public void removeEntities(EntityType type) {
        ArrayList<Entity> removed = new ArrayList<>();
        for (Entity e : entities) {
            if (e.getEntityType() == type) {
                removed.add(e);
            }
        }
        for (Entity e : removed) {
            entities.remove(e);
        }
    }

}
