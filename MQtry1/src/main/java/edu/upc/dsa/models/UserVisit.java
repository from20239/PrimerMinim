package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class UserVisit {
    private String userId;
    private PointOfInterest point;

    // Constructor
    public UserVisit(String userId, PointOfInterest point) {
        this.userId = userId;
        this.point = point;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PointOfInterest getPoint() {
        return point;
    }

    public void setPoint(PointOfInterest point) {
        this.point = point;
    }


    // Methods to access coordinates directly
    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    // Example main method with some UserVisit objects
    public static void main(String[] args) {
        // Creating some Points of Interest (POI)
        PointOfInterest poi1 = new PointOfInterest(10, 20, PointOfInterest.ElementType.TREE);
        PointOfInterest poi2 = new PointOfInterest(15, 25, PointOfInterest.ElementType.POTION);
        PointOfInterest poi3 = new PointOfInterest(30, 40, PointOfInterest.ElementType.DOOR);

        // Creating some UserVisits for each POI
        UserVisit visit1 = new UserVisit("1", poi1); // User 1 visits a tree at (10, 20)
        UserVisit visit2 = new UserVisit("2", poi2); // User 2 visits a potion at (15, 25)
        UserVisit visit3 = new UserVisit("3", poi3); // User 3 visits a door at (30, 40)

        // Adding visits to a list
        List<UserVisit> userVisits = new ArrayList<>();
        userVisits.add(visit1);
        userVisits.add(visit2);
        userVisits.add(visit3);

        // Print all visits and the associated coordinates
        for (UserVisit visit : userVisits) {
            System.out.println("User ID: " + visit.getUserId() + " visited " +
                    visit.getPoint().getType() + " at (" + visit.getX() + ", " + visit.getY() + ")");
        }
    }
}