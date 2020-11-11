package agh.cs.lab5_6.objects;

/**
 * Helps us to group Objects by mobility
 * As for now:
 * Animals - can move
 * Grass - can't move
 */
public interface IMapElement {
    String toString();

    boolean equals(Object o);

    int hashCode();

    boolean isMovable();

}
