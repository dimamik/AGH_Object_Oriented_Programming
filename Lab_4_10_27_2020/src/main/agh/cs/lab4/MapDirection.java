package agh.cs.lab4;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;

    @Override
    public String toString() {
        return switch (this) {
            case EAST -> ">";
            case WEST -> "<";
            case SOUTH -> "v";
            case NORTH -> "^";
        };
    }

    public MapDirection next() {
        return switch (this) {
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case SOUTH -> WEST;
            case NORTH -> EAST;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case EAST -> NORTH;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case NORTH -> WEST;
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case EAST -> new Vector2d(1, 0);
            case WEST -> new Vector2d(-1, 0);
            case SOUTH -> new Vector2d(0, -1);
            case NORTH -> new Vector2d(0, 1);
        };
    }
}
