package edu.upc.dsa;

import edu.upc.dsa.models.PointOfInterest;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserVisit;

import java.util.List;

public interface CampusGameManager {

    // 添加用户Add a user
    void addUser(String id, String name, String surname, String email, String birthDate);

    // 获取所有用户Get all users
    List<User> getUsers();

    // 通过用户 ID 获取用户信息Get user information by user ID
    User getUser(String id);

    // 添加兴趣点，使用 PointOfInterest.ElementType 来指定类型
    // Add a point of interest, using PointOfInterest.ElementType to specify the type
    void addPointOfInterest(int x, int y, PointOfInterest.ElementType type);

    // 注册用户访问兴趣点Register a user's visit to a point of interest
    void registerUserVisit(String userId, int x, int y);

    // 获取用户访问的所有兴趣点Get all points of interest visited by a user
    List<UserVisit> getUserVisits(String userId);

    // 根据坐标获取用户Get users by coordinates
    List<User> getUsersByPoint(int x, int y);

    // 根据兴趣点类型获取兴趣点Get points of interest by type
    List<PointOfInterest> getPointsByType(PointOfInterest.ElementType type);

}