package edu.upc.dsa;

import edu.upc.dsa.models.PointOfInterest;
import edu.upc.dsa.models.User;

import java.util.Date;
import java.util.List;

public interface CampusGameManager {

    // 添加用户
    void addUser(String id, String name, String surname, String email, Date birthDate);

    // 获取所有用户
    List<User> getUsers();

    // 通过用户 ID 获取用户信息
    User getUser(String id);

    // 添加兴趣点，使用 PointOfInterest.ElementType 来指定类型
    void addPointOfInterest(int x, int y, PointOfInterest.ElementType type);

    // 注册用户访问兴趣点
    void registerUserVisit(String userId, int x, int y);

    // 获取用户访问的所有兴趣点
    List<PointOfInterest> getUserVisits(String userId);

    // 根据坐标获取用户
    List<User> getUsersByPoint(int x, int y);

    // 根据兴趣点类型获取兴趣点
    List<PointOfInterest> getPointsByType(PointOfInterest.ElementType type);  // 新增方法
}