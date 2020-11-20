package agh.cs.lab5_and_others.objects;

/**
 * Helps us to group Objects by mobility
 * As for now:
 * Animals - can move
 * Grass - can't move
 */
public interface IMapElement {
    boolean isMovable();
}
