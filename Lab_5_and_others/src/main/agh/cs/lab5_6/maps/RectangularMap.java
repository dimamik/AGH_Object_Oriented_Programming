package agh.cs.lab5_6.maps;

import agh.cs.lab5_6.movement.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    /**
     * Constructor using parent constructor to fill
     *
     * @param width  - OX in Cartesian
     * @param height - OY in Cartesian
     */
    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    /**
     * Indicate whether object (not grass) that can move directly to
     * Given position
     *
     * @param position The position checked for the movement possibility.
     * @return Truth if can, else False
     */
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width - 1, height - 1))
                && !isOccupied(position);
    }

    /**
     * Help function for toString(),
     * Calculates the left bottom and right top borders of map
     *
     * @return List of Vector2d where zero element is left_bottom corner
     * and first element is right_top corner
     */
    @Override
    protected List<Vector2d> getBorders() {
        List<Vector2d> to_ret = new ArrayList<>();
        to_ret.add(new Vector2d(0, 0));
        to_ret.add(new Vector2d(width - 1, height - 1));
        return to_ret;
    }
}
