package agh.cs.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;
    public Animal[][] mapOfAnimals2d;
    private final List<Animal> animalsList;
    private final MapVisualiser mapVisualiser;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        mapOfAnimals2d = new Animal[width][height];
        initListOfAnimals2d();
        animalsList = new ArrayList<>();
        mapVisualiser = new MapVisualiser(this);
    }

    private void initListOfAnimals2d() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                mapOfAnimals2d[i][j] = null;
            }
        }

    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width - 1, height - 1))
                && !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (animal.getPosition() != null && mapOfAnimals2d[animal.getPosition().x][animal.getPosition().y] == null) {
            mapOfAnimals2d[animal.getPosition().x][animal.getPosition().y] = animal;
            if (!animalsList.contains(animal))
                animalsList.add(animal);
            return true;
        }
        return false;
    }

    private void clearPosition(Vector2d position) {
        mapOfAnimals2d[position.x][position.y] = null;
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int i = 0;
        int j = 0;

        while (i < animalsList.size() && j < directions.size()) {
            MoveDirection moveDirection = directions.get(j++);
            Animal tmp_animal = animalsList.get(i++);
            Vector2d prev_position = tmp_animal.getPosition();
            tmp_animal.move(moveDirection);
            if (place(tmp_animal)) {
                clearPosition(prev_position);
            }
            if (i == animalsList.size()) {
                i = 0;
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return mapOfAnimals2d[position.x][position.y] != null;
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        return Optional.ofNullable(mapOfAnimals2d[position.x][position.y]);
    }

    @Override
    public String toString() {
        return mapVisualiser.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }
}
