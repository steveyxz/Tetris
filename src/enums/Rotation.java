package enums;

public enum Rotation {
    DEG0,
    DEG90,
    DEG180,
    DEG270;

    public Rotation getNext() {
        switch (this) {
            case DEG0 -> {
                return DEG90;
            }
            case DEG90 -> {
                return DEG180;
            }
            case DEG180 -> {
                return DEG270;
            }
            case DEG270 -> {
                return DEG0;
            }
        }
        return DEG0;
    }
}
