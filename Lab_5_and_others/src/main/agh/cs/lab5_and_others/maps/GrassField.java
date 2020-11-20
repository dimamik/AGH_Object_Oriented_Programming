package agh.cs.lab5_and_others.maps;

import agh.cs.lab5_and_others.movement.Vector2d;
import agh.cs.lab5_and_others.objects.Animal;
import agh.cs.lab5_and_others.objects.Grass;
import agh.cs.lab5_and_others.objects.IMapElement;

import java.util.HashMap;
import java.util.Optional;
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
     * MapVisualiser mapVisualiser;
     */
    public GrassField(int grassAmount) {
        super();
        this.grassAmount = grassAmount;
        grassHashMap = new HashMap<>();
        placeGrass();
        mapBoundary = new MapBoundary(grassHashMap);
    }

    /**
     * Place NEW Animal on map
     *
     * @param animal - new animal to position
     *               Differs from place that it only works with new elements
     */
    public void initial_place(Animal animal) {
        if (animal.getPosition() != null && canMoveTo(animal.getPosition())) {
            animalHashMap.put(animal.getPosition(), animal);
            animal.addObserver(this);
            animal.addObserver(mapBoundary);
            mapBoundary.positionAdded(animal, animal.getPosition());
        }
    }

    /**
     * Help function to generate random int
     *
     * @param grassAmount - amount of grass
     * @return random int
     */
    private int generateRandomInt(int grassAmount) {
        return ThreadLocalRandom.current().nextInt(0, (int) (Math.sqrt(grassAmount * 10)) + 1);
    }

    /**
     * Help function to process random distribution
     */
    private void placeGrass() {
        int i = 0;
        while (i++ < grassAmount) {
            int randomNum = generateRandomInt(grassAmount);
            int randomNum2 = generateRandomInt(grassAmount);
            Grass toAdd = new Grass(new Vector2d(randomNum, randomNum2));
            if (!grassHashMap.containsKey(toAdd.getPosition()))
                grassHashMap.put(toAdd.getPosition(), toAdd);
            else i--;
        }
    }

    /**
     * @param position The position checked for the movement possibility.
     * @return - if can move to given position
     */
    @Override
    public boolean canMoveTo(Vector2d position) {
        if ((objectAt(position).isEmpty())) return true;
        else {
            IMapElement imapElement = (IMapElement) (objectAt(position).get());
            return !imapElement.isMovable();
        }
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
     * Function that needs to be deleted before final release
     *
     * @return - grass hashmap
     * TODO DELETE_Before_final
     */
    public HashMap<Vector2d, Grass> getGrassHashMap() {
        return grassHashMap;
    }
}
