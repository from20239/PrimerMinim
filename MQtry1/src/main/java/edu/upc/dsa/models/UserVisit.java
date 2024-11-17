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
        User user1 = new User("1", "Alice", "Smith", "alice.smith@email.com", "1990/01/01");
        User user2 = new User("2", "Bob", "Johnson", "bob.johnson@email.com", "1985/03/15");
        User user3 = new User("3", "Charlie", "Williams", "charlie.williams@email.com", "1992/07/21");

        // 使用之前创建的兴趣点
        PointOfInterest poi1 = PointOfInterest.getDefaultPoint(PointOfInterest.ElementType.TREE);  // 假设树的默认位置是 (7, 7)
        PointOfInterest poi2 = PointOfInterest.getDefaultPoint(PointOfInterest.ElementType.POTION);  // 假设药水的默认位置是 (3, 3)
        PointOfInterest poi3 = PointOfInterest.getDefaultPoint(PointOfInterest.ElementType.DOOR);    // 假设门的默认位置是 (0, 0)

        // 创建 UserVisit 对象
        UserVisit visit1 = new UserVisit(user1.getId(), poi1);  // Alice 访问树
        UserVisit visit2 = new UserVisit(user2.getId(), poi2);  // Bob 访问药水
        UserVisit visit3 = new UserVisit(user3.getId(), poi3);  // Charlie 访问门

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