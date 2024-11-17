package edu.upc.dsa.models;

public class PointOfInterest {

    private int x;
    private int y;
    private ElementType type;

    // Constructor
    public PointOfInterest(int x, int y, ElementType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Getters and Setters
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

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    // Enum for ElementType
    public enum ElementType {
        DOOR, WALL, BRIDGE, POTION, SWORD, COIN, GRASS, TREE
    }
    // Method to get default points for each type as example
    public static PointOfInterest getDefaultPoint(ElementType type) {
        switch (type) {
            case DOOR:
                return new PointOfInterest(0, 0, ElementType.DOOR);  // Default coordinates for DOOR
            case WALL:
                return new PointOfInterest(1, 1, ElementType.WALL);  // Default coordinates for WALL
            case BRIDGE:
                return new PointOfInterest(2, 2, ElementType.BRIDGE);  // Default coordinates for BRIDGE
            case POTION:
                return new PointOfInterest(3, 3, ElementType.POTION);  // Default coordinates for POTION
            case SWORD:
                return new PointOfInterest(4, 4, ElementType.SWORD);  // Default coordinates for SWORD
            case COIN:
                return new PointOfInterest(5, 5, ElementType.COIN);  // Default coordinates for COIN
            case GRASS:
                return new PointOfInterest(6, 6, ElementType.GRASS);  // Default coordinates for GRASS
            case TREE:
                return new PointOfInterest(7, 7, ElementType.TREE);  // Default coordinates for TREE
            default:
                return null;  // If an unknown type, return null
        }
    }
}