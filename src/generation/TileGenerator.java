package generation;

import engineTester.MainGameLoop;
import entities.tiles.FallingTile;
import entities.tiles.TileGroup;
import entities.tiles.WallTile;
import enums.EntityType;
import enums.GameState;
import enums.PlayState;
import enums.TileGroupType;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import textures.ModelTexture;
import util.EntityHandler;

import java.util.ArrayList;
import java.util.Random;

public class TileGenerator {
    private EntityHandler handler;
    private Loader loader;
    private int timer;
    private ArrayList<TileGroup> groups = new ArrayList<>();
    private float tileCount = 0;

    public static FallingTile[][] tiles = new FallingTile[16][11];

    public TileGenerator(EntityHandler handler, Loader loader) {
        this.handler = handler;
        this.loader = loader;
        generateWalls();
    }

    private void generateWalls() {
        float x = MainGameLoop.xOffset - MainGameLoop.tileWidth;
        float y = MainGameLoop.yOffset + MainGameLoop.tileWidth*15;
        float[] vertices = {
                -0.04f, 0.04f, 0f,//v0
                -0.04f, -0.04f, 0f,//v1
                0.04f, -0.04f, 0f,//v2
                0.04f, 0.04f, 0f,//v3
        };

        int[] indices = {
                0, 1, 3,//top left triangle (v0, v1, v3)
                3, 1, 2//bottom right triangle (v3, v1, v2)
        };

        float[] textures = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        for (int i = 0; i < 16; i++) {
            WallTile tile = new WallTile(new TexturedModel(loader.loadToVAO(vertices, textures, indices), new ModelTexture(loader.loadTexture("wall"))), new Vector3f(x, y, -1), 0, 0, 0, 1, EntityType.WALL_TILE);
            handler.addEntity(tile);
            y -= MainGameLoop.tileWidth;
        }
        for (int i = 0; i < 12; i++) {
            WallTile tile = new WallTile(new TexturedModel(loader.loadToVAO(vertices, textures, indices), new ModelTexture(loader.loadTexture("wall"))), new Vector3f(x, y, -1), 0, 0, 0, 1, EntityType.WALL_TILE);
            handler.addEntity(tile);
            x += MainGameLoop.tileWidth;
        }
        for (int i = 0; i < 17; i++) {
            WallTile tile = new WallTile(new TexturedModel(loader.loadToVAO(vertices, textures, indices), new ModelTexture(loader.loadTexture("wall"))), new Vector3f(x, y, -1), 0, 0, 0, 1, EntityType.WALL_TILE);
            handler.addEntity(tile);
            y += MainGameLoop.tileWidth;
        }
    }

    public void tick() {
        if (MainGameLoop.gameState == GameState.PLAY) {
            for (TileGroup group : groups) {
                group.tick();
            }
            if (MainGameLoop.playState == PlayState.TILE_GENERATE) {
                generateRandomTiles();
                MainGameLoop.playState = PlayState.TILE_FALLING;
            }
        }
    }

    private void generateRandomTiles() {
        TileGroupType type = TileGroupType.T.getRandomTile();
        TileGroup group = new TileGroup(type, loader, Float.toString(tileCount), handler);
        tileCount++;

        int x = new Random().nextInt(8);
        int y = 0;

        group.addTiles(type.getShape(), x, y);
        group.addTilesToEntityList(handler);
        groups.add(group);
    }

}
