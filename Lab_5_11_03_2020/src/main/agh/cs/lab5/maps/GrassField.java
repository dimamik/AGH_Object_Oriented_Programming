package agh.cs.lab5.maps;

import agh.cs.lab5.movement.Vector2d;
import agh.cs.lab5.objects.Animal;
import agh.cs.lab5.objects.Grass;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class representing infinite map with randomly distributed grass objects,
 * where Animals can be placed. Could be easily modified that not only animals
 * can be placed there (by grouping animals via interface for example
 * IMovingObjects)
 */
public class GrassField extends AbstractWorldMap {
    private final int grassAmount;
    private final HashMap<Vector2d, Grass> grassHashMap;

    /**
     * Constructor using parent constructor to fill
     *
     * @param grassAmount - Amount of grass objects to be randomly distributed
     * @AncestorData protected HashMap<Vector2d, Animal> animalHashMap; protected
     *               MapVisualiser mapVisualiser;
     */
    public GrassField(int grassAmount) {
        super();
        this.grassAmount = grassAmount;
        grassHashMap = new HashMap<>();
        placeGrass();

    }

    /**
     * Help function to process random distribution
     */
    private void placeGrass() {
        for (int i = 0; i < grassAmount; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(grassAmount * 10)) + 1);
            int randomNum2 = ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(grassAmount * 10)) + 1);
            Grass toAdd = new Grass(new Vector2d(randomNum, randomNum2));
            grassHashMap.put(toAdd.getPosition(), toAdd);
        }
    }

    /**
     * Indicate whether object that can move directly (not grass) can move to Given
     * position
     *
     * @param position The position checked for the movement possibility.
     * @return Truth if can, else False
     */
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position).orElse(null) instanceof Animal);
    }

    /**
     * Using ancestor in "ugly" way to represent childhood.
     * <p>
     * Return true if given position on the map is occupied. Should not be confused
     * with canMove since there might be empty positions where the animal cannot
     * move.
     *
     * @param position Position to check.
     * @return Truth is grass or Animal is on position
     */
    @Override
    public boolean isOccupied(Vector2d position) {
        if (!grassHashMap.containsKey(position))
            return super.isOccupied(position);
        return true;
    }

    /**
     * Return an object at a given position.
     *
     * @param position The position of the object.
     * @return Object or empty Optional if the position is not occupied.
     * @implNote Depends on objects on map (which can be grouped by interface)
     */
    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if (animalHashMap.containsKey(position)) {
            return super.objectAt(position);
        } else {
            return Optional.ofNullable(grassHashMap.get(position));
        }
    }

    /**
     * Help function for toString(), Calculates the left bottom and right top
     * borders of map
     *
     * @return List of Vector2d where zero element is left_bottom corner and first
     *         element is right_top corner
     */
    protected List<Vector2d> getBorders() {
        List<Vector2d> to_ret = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Iterator<Vector2d> iterator = animalHashMap.keySet().iterator();
        Iterator<Vector2d> iterator2 = grassHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            Vector2d fk = iterator.next();
            min = Math.min(min, fk.x);
            min = Math.min(min, fk.y);
            max = Math.max(max, fk.x);
            max = Math.max(max, fk.y);
            if (!iterator.hasNext()) {
                iterator = iterator2;
            }
        }
        Vector2d f1 = new Vector2d(min, min);
        Vector2d s1 = new Vector2d(max, max);
        to_ret.add(f1);
        to_ret.add(s1);
        return to_ret;
    }

    public HashMap<Vector2d, Grass> getGrassHashMap() {
        return grassHashMap;
    }
}
