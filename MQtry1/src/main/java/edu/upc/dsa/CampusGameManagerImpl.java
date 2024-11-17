package edu.upc.dsa;

import edu.upc.dsa.models.PointOfInterest;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserVisit;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class CampusGameManagerImpl implements CampusGameManager {

    private static final Logger logger = Logger.getLogger(CampusGameManagerImpl.class);
    private static CampusGameManagerImpl instance;

    private Map<String, User> users = new HashMap<>(); // 用户存储
    private List<PointOfInterest> points = new ArrayList<>(); // 兴趣点存储
    private List<UserVisit> visits = new ArrayList<>(); // 访问记录存储

    // 单例模式
    public CampusGameManagerImpl() {
    }

    public static CampusGameManagerImpl getInstance() {
        if (instance == null) {
            instance = new CampusGameManagerImpl();
        }
        return instance;
    }

    @Override
    public void addUser(String id, String name, String surname, String email, String birthDate) {
        logger.info(String.format("addUser - Parameters: id=%s, name=%s, surname=%s, email=%s, birthDate=%s", id, name, surname, email, birthDate));
        User user = new User(id, name, surname, email, birthDate);
        users.put(id, user);
        logger.info(String.format("addUser - User %s added successfully", id));
    }

    @Override
    public List<User> getUsers() {
        logger.info("getUsers - Listing all users");
        return users.values().stream()
                .sorted(Comparator.comparing(User::getSurname).thenComparing(User::getName))
                .collect(Collectors.toList());
    }

    @Override
    public User getUser(String id) {
        logger.info(String.format("getUser - Parameters: id=%s", id));
        return users.get(id);
    }

    @Override
    public void addPointOfInterest(int x, int y, PointOfInterest.ElementType type) {
        logger.info(String.format("addPointOfInterest - Parameters: x=%d, y=%d, type=%s", x, y, type));
        PointOfInterest point = new PointOfInterest(x, y, type);
        points.add(point);
        logger.info(String.format("addPointOfInterest - Point (%d, %d) of type %s added successfully", x, y, type));
    }

    @Override
    public void registerUserVisit(String userId, int x, int y) {
        logger.info(String.format("registerUserVisit - Parameters: userId=%s, x=%d, y=%d", userId, x, y));
        PointOfInterest point = points.stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findFirst()
                .orElse(null);

        if (point == null) {
            logger.error(String.format("registerUserVisit - Point (%d, %d) not found", x, y));
            throw new IllegalArgumentException("Point of interest not found");
        }
        if (!users.containsKey(userId)) {
            logger.error(String.format("registerUserVisit - User %s not found", userId));
            throw new IllegalArgumentException("User not found");
        }

        visits.add(new UserVisit(userId, point));
        logger.info(String.format("registerUserVisit - User %s visited point (%d, %d)", userId, x, y));
    }

    @Override
    public List<UserVisit> getUserVisits(String userId) {
        logger.info(String.format("getUserVisits - Parameters: userId=%s", userId));
        if (!users.containsKey(userId)) {
            logger.error(String.format("getUserVisits - User %s not found", userId));
            throw new IllegalArgumentException("User not found");
        }
        List<UserVisit> userVisits = visits.stream()
                .filter(visit -> visit.getUserId().equals(userId))
                .collect(Collectors.toList());
        logger.info(String.format("getUserVisits - User %s has %d visits", userId, userVisits.size()));
        return userVisits;
    }

    @Override
    public List<User> getUsersByPoint(int x, int y) {
        logger.info(String.format("getUsersByPoint - Parameters: x=%d, y=%d", x, y));
        PointOfInterest point = points.stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findFirst()
                .orElse(null);

        if (point == null) {
            logger.error(String.format("getUsersByPoint - Point (%d, %d) not found", x, y));
            throw new IllegalArgumentException("Point of interest not found");
        }

        List<String> userIds = visits.stream()
                .filter(visit -> visit.getPoint().equals(point))
                .map(UserVisit::getUserId)
                .distinct()
                .collect(Collectors.toList());

        List<User> resultUsers = userIds.stream()
                .map(users::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        logger.info(String.format("getUsersByPoint - Point (%d, %d) visited by %d users", x, y, resultUsers.size()));
        return resultUsers;
    }

    @Override
    public List<PointOfInterest> getPointsByType(PointOfInterest.ElementType type) {
        logger.info(String.format("getPointsByType - Parameters: type=%s", type));
        return points.stream()
                .filter(p -> p.getType() == type)
                .collect(Collectors.toList());
    }
}