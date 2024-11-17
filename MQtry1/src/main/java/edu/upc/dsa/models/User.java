package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String birthDate;  // 改为 String 类型

    // Constructor
    public User(String id, String name, String surname, String email, String birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthDate = birthDate;  // 直接设置为字符串
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        // Create new users with String birthDate format "yyyy/MM/dd"
        User user1 = new User("1", "Alice", "Smith", "alice.smith@email.com", "1990/01/01");
        User user2 = new User("2", "Bob", "Johnson", "bob.johnson@email.com", "1985/03/15");
        User user3 = new User("3", "Charlie", "Williams", "charlie.williams@email.com", "1992/07/21");
        User user4 = new User("4", "David", "Brown", "david.brown@email.com", "1988/09/10");
        User user5 = new User("5", "Eve", "Davis", "eve.davis@email.com", "1995/11/05");

        // Add them to a list
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        // Print the users
        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", Name: " + user.getName() + " " + user.getSurname() +
                    ", Email: " + user.getEmail() + ", Birthdate: " + user.getBirthDate());
        }
    }
}