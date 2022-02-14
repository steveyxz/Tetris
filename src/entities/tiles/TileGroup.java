package entities.tiles;

import engineTester.MainGameLoop;
import entities.Entity;
import enums.EntityType;
import enums.PlayState;
import enums.Rotation;
import enums.TileGroupType;
import generation.TileGenerator;
import gui.PlayerInfo;
import models.TexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import textures.ModelTexture;
import util.EntityHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TileGroup {

    private TileGroupType type;
    private String tileID;
    private ArrayList<FallingTile> tiles = new ArrayList<>();
    private Loader loader;
    private boolean startMoving;
    private int timer;
    private boolean hasStopped = false;
    private int fastMoveTimer = 0;
    private int leftRightTimer = 0;
    private int rotationTimer = 0;
    private boolean hasLastTickHeldLeftRight = false;
    private boolean hasLastTickHeldDown = false;
    private boolean hasLastTickRotate = false;
    private EntityHandler handler;
    private Rotation r = Rotation.DEG0;
    private int x;
    private int y;

    public TileGroup(TileGroupType type, Loader loader, String tileID, EntityHandler handler) {
        this.type = type;
        this.loader = loader;
        this.tileID = tileID;
        this.handler = handler;
    }

    public void addTile(FallingTile tile) {
        tiles.add(tile);
        TileGenerator.tiles[tile.getY()][tile.getX()] = tile;
    }

    public void addTiles(int[][] tiles, int x, int y) {
        this.x = x;
        this.y = y;
        float[] vertices = {
                -0.04f, 0.04f, 0f,//v0
                -0.04f, -0.04f, 0f,//v1
                0.04f, -0.04f, 0f,//v2
                0.04f, 0.04f, 0f,//v3
        };

        int[] indices = {
                0,1,3,//top left triangle (v0, v1, v3)
                3,1,2//bottom right triangle (v3, v1, v2)
        };

        float[] textures = {
                0, 0,
                0, 1,
                1, 1,
                1, 0
        };
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 1) {
                    FallingTile tile = new FallingTile(new TexturedModel(loader.loadToVAO(vertices, textures, indices), new ModelTexture(loader.loadTexture(this.type.getColorPath()))), new Vector3f(0, 0, 0), 0, 0, 0, 1, j + x, 15 - i - y, EntityType.FALLING_TILE, this);
                    this.tiles.add(tile);
                    TileGenerator.tiles[tile.getY()][tile.getX()] = tile;
                }
            }
        }
    }

    public boolean isHasStopped() {
        return hasStopped;
    }

    public void setHasStopped(boolean hasStopped) {
        this.hasStopped = hasStopped;
    }

    public String getTileID() {
        return tileID;
    }

    public void setTileID(String tileID) {
        this.tileID = tileID;
    }

    public Rotation getR() {
        return r;
    }

    public void setR(Rotation r) {
        this.r = r;
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

    public void checkLoss() {
        for (Entity e : handler.entities) {
            if (e instanceof FallingTile) {
                FallingTile t = (FallingTile) e;
                if (t.getY() > 14 && t.getGroup().isHasStopped()) {
                    handler.removeEntities(EntityType.FALLING_TILE);
                    TileGenerator.tiles = new FallingTile[16][11];
                    PlayerInfo.addScore(-PlayerInfo.SCORE);
                    return;
                }
            }
        }
    }

    public void moveTiles(int x, int y) {
        if (isHasStopped()) {
            return;
        }
        for (FallingTile t : tiles) {
            if (t.getY() < -y) {
                setHasStopped(true);
                clearFilledRows();
                PlayerInfo.addScore(10);
                checkLoss();
                return;
            }
            if (t.getY() > 15 - y) {
                setHasStopped(true);
                clearFilledRows();
                PlayerInfo.addScore(10);
                checkLoss();
                return;
            }
            if (t.getX() < -x) {
                return;
            }
            if (t.getX() > 10 - x) {
                return;
            }
            if (TileGenerator.tiles[t.getY() + y][t.getX() + x] != null && !TileGenerator.tiles[t.getY() + y][t.getX() + x].getGroup().getTileID().equals(t.getGroup().getTileID())) {
                if (y == -1) {
                    setHasStopped(true);
                    PlayerInfo.addScore(10);
                    clearFilledRows();
                    checkLoss();
                    MainGameLoop.playState = PlayState.TILE_GENERATE;
                }
                return;
            }
        }

        for (FallingTile t : tiles) {
            TileGenerator.tiles[t.getY()][t.getX()] = null;
        }
        for (FallingTile t : tiles) {
            t.increaseX(x);
            t.increaseY(y);
            TileGenerator.tiles[t.getY()][t.getX()] = t;
            if (t.getY() == 0) {
                setHasStopped(true);
                clearFilledRows();
                PlayerInfo.addScore(10);
                checkLoss();
            }
        }

        this.x += x;
        this.y += y;
    }

    public void rotate() {
        //The next position
        int[][] nextPosition = getType().getNextRotation(r);
        //The solid points of that position (4 block positions - x, y)
        int[][] solidPoints = new int[4][2];
        //Count
        int count = 0;
        //Loop through the next position
        for (int i = 0; i < nextPosition.length; i++) {
            for (int j = 0; j < nextPosition[i].length; j++) {
                if (nextPosition[i][j] == 1) {
                    solidPoints[count][0] = j;
                    solidPoints[count][1] = i;
                    count++;
                }
            }
        }
        for (int i = 0; i < solidPoints.length; i++) {
            if (15 - solidPoints[i][1] + y < 0 || 15 - solidPoints[i][1] + y > 15) {
                return;
            }
            if (solidPoints[i][0] + x > 10 || solidPoints[i][0] + x < 0) {
                return;
            }
            if (TileGenerator.tiles[15 - solidPoints[i][1] + y][solidPoints[i][0] + x] != null && !Objects.equals(TileGenerator.tiles[15 - solidPoints[i][1] + y][solidPoints[i][0] + x].getGroup().getTileID(), getTileID())) {
                return;
            }
        }
        r = r.getNext();
        count = 0;
        for (FallingTile t : tiles) {
            t.setX(solidPoints[count][0] + x);
            t.setY(15 - solidPoints[count][1] + y);
            count++;
        }
        count = 0;
        for (Entity e : handler.entities) {
            if (e instanceof FallingTile) {
                FallingTile t  = (FallingTile) e;
                if (t.getGroup() == this) {
                    t.setX(tiles.get(count).getX());
                    t.setY(tiles.get(count).getY());
                    count++;
                }
            }
        }

        TileGenerator.tiles = new FallingTile[16][11];
        for (Entity e : handler.entities) {
            if (e instanceof FallingTile) {
                FallingTile t = (FallingTile) e;
                TileGenerator.tiles[t.getY()][t.getX()] = t;
            }
        }

    }

    public String toFunText(FallingTile[][] tiles) {
        StringBuilder b = new StringBuilder();
        b.append("__");
        for (int i = 0; i < tiles[0].length; i++) {
            b.append("_");
        }
        b.append("\n");
        for (FallingTile[] tile : tiles) {
            b.append("|");
            for (FallingTile fallingTile : tile) {
                if (fallingTile == null) {
                    b.append("0");
                } else {
                    b.append("1");
                }
            }
            b.append("|\n");
        }
        b.append("--");
        for (int i = 0; i < tiles[0].length; i++) {
            b.append("-");
        }
        b.append("\n");
        return new String(b);
    }

    public void tick() {
        if (startMoving) {

            if (!isHasStopped()) {
                if (leftRightTimer > 2 || !hasLastTickHeldLeftRight) {
                    if (!hasLastTickHeldLeftRight) {
                        leftRightTimer = 0;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                        this.moveTiles(-1, 0);
                        hasLastTickHeldLeftRight = true;
                    } else if (Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                        this.moveTiles(1, 0);
                        hasLastTickHeldLeftRight = true;
                    } else {
                        hasLastTickHeldLeftRight = false;
                    }
                    leftRightTimer = 0;
                }
                if (fastMoveTimer > 2 || !hasLastTickHeldDown) {
                    if (!hasLastTickHeldDown) {
                        fastMoveTimer = 0;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                        this.moveTiles(0, -1);
                        PlayerInfo.addScore(1);
                        hasLastTickHeldDown = true;
                    } else {
                        hasLastTickHeldLeftRight = false;
                    }
                    fastMoveTimer = 0;
                }

                if (rotationTimer > 3 || !hasLastTickRotate) {
                    if (!hasLastTickRotate) {
                        rotationTimer = 0;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                        rotate();
                        hasLastTickRotate = true;
                    } else {
                        hasLastTickRotate = false;
                    }
                    rotationTimer = 0;
                }
            }
            fastMoveTimer++;
            leftRightTimer++;
            timer++;
            rotationTimer++;
            if (timer == 10) {
                this.moveTiles(0, -1);
                timer = 0;
            }
        }
    }

    public void addTilesToEntityList(EntityHandler handler) {
        for (FallingTile t : tiles) {
            handler.addEntity(t);
        }
        startMoving = true;
    }

    public TileGroupType getType() {
        return type;
    }

    public void setType(TileGroupType type) {
        this.type = type;
    }

    public void clearFilledRows() {
        //Loop through rows
        for (int i = 0; i < TileGenerator.tiles.length; i++) {
            //Check if filled
            FallingTile[] row = TileGenerator.tiles[i];
            if (!(new ArrayList<>(Arrays.asList(row)).contains(null))) {
                deleteRow(i);
                clearFilledRows();
                return;
            }
        }
    }

    public void deleteRow(int rowNumber) {
        TileGenerator.tiles = new FallingTile[16][11];
        for (Entity entity : handler.getEntities(EntityType.FALLING_TILE)) {
            FallingTile tile = (FallingTile) entity;
            if (tile.getY() == rowNumber) {
                handler.removeEntity(entity);
            }
        }
        for (Entity entity : handler.getEntities(EntityType.FALLING_TILE)) {
            FallingTile tile = (FallingTile) entity;
            if (tile.getY() > rowNumber) {
                tile.increaseY(-1);
            }
        }
        for (Entity entity : handler.getEntities(EntityType.FALLING_TILE)) {
            FallingTile tile = (FallingTile) entity;
            TileGenerator.tiles[tile.getY()][tile.getX()] = tile;
        }
        PlayerInfo.addScore(50);
    }
}
