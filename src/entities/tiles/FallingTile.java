package entities.tiles;

import com.sun.tools.javac.Main;
import engineTester.MainGameLoop;
import enums.EntityType;
import enums.PlayState;
import generation.TileGenerator;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class FallingTile extends Tile {

    private int x;
    private int y;
    private TileGroup group;
    private int timer;
    private boolean doTick = true;

    public TileGroup getGroup() {
        return group;
    }

    public void setGroup(TileGroup group) {
        this.group = group;
    }

    public FallingTile(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, int x, int y, EntityType entityType, TileGroup group) {
        super(model, position, rotX, rotY, rotZ, scale, entityType);
        this.x = x;
        this.y = y;
        this.group = group;
    }

    public FallingTile(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, EntityType entityType, TileGroup group) {
        this(model, position, rotX, rotY, rotZ, scale, 0, 0, entityType, group);
    }

    @Override
    public void tick() {
        if (!doTick) {
            return;
        }
        if (this.getY() > -1) {
            this.setPosition(new Vector3f(MainGameLoop.xOffset + this.x * MainGameLoop.tileWidth, MainGameLoop.yOffset + this.y * MainGameLoop.tileWidth, -1));
        }
        if (this.getY() > 1) {
            if (TileGenerator.tiles[getY() - 1][getX()] != null) {
                if (!TileGenerator.tiles[getY() - 1][getX()].getGroup().getTileID().equals(getGroup().getTileID())) {
                    return;
                }
            }
        }
        if (this.getY() == 0){
            MainGameLoop.playState = PlayState.TILE_GENERATE;
            doTick = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseY(int inc) {
        this.y += inc;
    }

    public void increaseX(int inc) {
        this.x += inc;
    }
}
