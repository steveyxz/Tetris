package enums;

import java.util.Random;

public enum TileGroupType {
    L("orange_tile", new int[][] {
            {0, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    },
            new int[][] {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, 0, 0},
                    {1, 1, 1, 0},
                    {1, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
            }),
    I("green_tile", new int[][] {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    },
            new int[][] {
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0}
            },
            new int[][] {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            },
            new int[][] {
                    {0, 0, -2, 0, 1, 0, -2, -1, 1, 2},
                    {0, 0, 2, 0, -1, 0, 2, 1, -1, -2},
                    {0, 0, -1, 0, 2, 0, -1, 2, 2, -1},
                    {0, 0, 1, 0, -2, 0, 1, -2, -2, 1},
                    {0, 0, 2, 0, -1, 0, 2, 1, -1, -2},
                    {0, 0, -2, 0, 1, 0, -2, -1, 1, 2},
                    {0, 0, 1, 0, -2, 0, 1, -2, -2, 1},
                    {0, 0, -1, 0, 2, 0, -1, 2, 2, -1}
            }),
    Z("red_tile", new int[][] {
            {1, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    },
            new int[][] {
                    {0, 0, 1, 0},
                    {0, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {1, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
            }),
    Z_INVERTED("gray_tile", new int[][] {
            {0, 1, 1, 0},
            {1, 1, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    },
            new int[][] {
                    {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, 0, 0},
                    {0, 1, 1, 0},
                    {1, 1, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {1, 0, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
            }),
    SQUARE("blue_tile", new int[][] {
            {0, 1, 1, 0},
            {0, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    },
            new int[][] {
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {

            }),
    T("yellow_tile", new int[][] {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    },
            new int[][] {
                    {0, 1, 0, 0},
                    {0, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, 0, 0},
                    {1, 1, 1, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 1, 0, 0},
                    {1, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 0, 0, 0}
            },
            new int[][] {
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, 1, 0, 1, -1, 0, 2, 1, 2},
                    {0, 0, -1, 0, -1, 1, 0, -2, -1, -2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, -1, 0, -1, -1, 0, 2, -1, 2},
                    {0, 0, 1, 0, 1, 1, 0, -2, 1, -2},
            });

    private String colorPath;
    private int[][] shape;
    private int[][] rotation1;
    private int[][] rotation2;
    private int[][] rotation3;
    private int[][] wallKickData;

    TileGroupType(String colorPath, int[][] shape, int[][] rotation1, int[][] rotation2, int[][] rotation3, int[][] wallKickData) {
        this.colorPath = colorPath;
        this.shape = shape;
        this.rotation1 = rotation1;
        this.rotation2 = rotation2;
        this.rotation3 = rotation3;
        this.wallKickData = wallKickData;
    }

    public void setShape(int[][] shape) {
        this.shape = shape;
    }

    public int[][] getRotation1() {
        return rotation1;
    }

    public void setRotation1(int[][] rotation1) {
        this.rotation1 = rotation1;
    }

    public int[][] getRotation2() {
        return rotation2;
    }

    public void setRotation2(int[][] rotation2) {
        this.rotation2 = rotation2;
    }

    public int[][] getRotation3() {
        return rotation3;
    }

    public void setRotation3(int[][] rotation3) {
        this.rotation3 = rotation3;
    }

    public int[][] getWallKickData() {
        return wallKickData;
    }

    public void setWallKickData(int[][] wallKickData) {
        this.wallKickData = wallKickData;
    }

    public int[][] getShape() {
        return shape;
    }

    public String getColorPath() {
        return colorPath;
    }

    public void setColorPath(String colorPath) {
        this.colorPath = colorPath;
    }

    public TileGroupType getRandomTile() {
        switch (new Random().nextInt(6)) {
            case 1 -> {
                return L;
            }
            case 2 -> {
                return I;
            }
            case 3 -> {
                return Z;
            }
            case 4 -> {
                return Z_INVERTED;
            }
            case 5 -> {
                return SQUARE;
            }
            case 0 -> {
                return T;
            }
        }
        return L;
    }

    public int[][] getRotation(Rotation r) {
        switch (r) {
            case DEG0 -> {
                return getShape();
            }
            case DEG90 -> {
                return getRotation1();
            }
            case DEG180 -> {
                return getRotation2();
            }
            case DEG270 -> {
                return getRotation3();
            }
        }
        return getShape();
    }

    public int[][] getNextRotation(Rotation r) {
        switch (r) {
            case DEG0 -> {
                return getRotation(Rotation.DEG90);
            }
            case DEG90 -> {
                return getRotation(Rotation.DEG180);
            }
            case DEG180 -> {
                return getRotation(Rotation.DEG270);
            }
            case DEG270 -> {
                return getRotation(Rotation.DEG0);
            }
        }
        return getShape();
    }
}
