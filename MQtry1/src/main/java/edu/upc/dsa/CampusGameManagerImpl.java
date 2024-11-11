package edu.upc.dsa;

import edu.upc.dsa.models.PointOfInterest;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserVisit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CampusGameManagerImpl implements CampusGameManager {

    private Map<String, User> users = new HashMap<>();
    private List<PointOfInterest> points = new ArrayList<>();
    private List<UserVisit> visits = new ArrayList<>();

    @Override
    public void addUser(String id, String name, String surname, String email, Date birthDate) {
        // 创建用户并加入users Map
        User user = new User(id, name, surname, email, birthDate);
        users.put(id, user);
    }

    @Override
    public List<User> getUsers() {
        // 按照姓氏和名字排序用户
        return users.values().stream()
                .sorted((u1, u2) -> {
                    int surnameComparison = u1.getSurname().compareTo(u2.getSurname());
                    return surnameComparison != 0 ? surnameComparison : u1.getName().compareTo(u2.getName());
                })
                .collect(Collectors.toList());
    }


    @Override
    public User getUser(String id) {
        // 根据ID获取用户
        return users.get(id);
    }

    @Override
    public void addPointOfInterest(int x, int y, PointOfInterest.ElementType type) {
        // 检查类型是否合法
        if (!isValidPointType(type)) {
            throw new IllegalArgumentException("Invalid point type: " + type);
        }

        // 创建兴趣点并加入points列表
        PointOfInterest point = new PointOfInterest(x, y, type);
        points.add(point);
    }

    // 检查兴趣点类型是否合法
    private boolean isValidPointType(PointOfInterest.ElementType type) {
        for (PointOfInterest.ElementType validType : PointOfInterest.ElementType.values()) {
            if (validType == type) {
                return true; // 类型合法
            }
        }
        return false; // 类型非法
    }

    @Override
    public void registerUserVisit(String userId, int x, int y) {
        // 查找匹配的兴趣点
        PointOfInterest point = points.stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findFirst()
                .orElse(null);

        // 如果找到兴趣点并且用户存在，则记录访问
        if (point != null && users.containsKey(userId)) {
            visits.add(new UserVisit(userId, point));
        } else {
            // 这里可以添加处理逻辑，如果没有找到兴趣点或用户不存在
            // 可以抛出异常或记录错误
        }
    }

    @Override
    public List<PointOfInterest> getUserVisits(String userId) {
        // 查找该用户访问的所有兴趣点
        return visits.stream()
                .filter(v -> v.getUserId().equals(userId))
                .map(UserVisit::getPoint)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUsersByPoint(int x, int y) {
        // 查找在某个兴趣点访问过的所有用户
        return visits.stream()
                .filter(v -> v.getPoint().getX() == x && v.getPoint().getY() == y)
                .map(v -> users.get(v.getUserId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PointOfInterest> getPointsByType(PointOfInterest.ElementType type) {
        // 查找所有特定类型的兴趣点
        return points.stream()
                .filter(p -> p.getType() == type)  // 根据类型过滤兴趣点
                .collect(Collectors.toList());
    }
}
