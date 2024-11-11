package edu.upc.dsa.services;

import edu.upc.dsa.CampusGameManager;
import edu.upc.dsa.models.PointOfInterest;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserVisit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Api(value = "/campusGame", description = "Campus Game Service")
@Path("/campusGame")
public class CampusGameService {

    private static final Logger logger = Logger.getLogger(CampusGameService.class);
    private CampusGameManager manager;

    // 单例模式实现
    private static CampusGameService instance;

    // 私有构造函数
    private CampusGameService(CampusGameManager manager) {
        this.manager = manager;
    }

    // 获取单例实例
    public static CampusGameService getInstance(CampusGameManager manager) {
        if (instance == null) {
            instance = new CampusGameService(manager);
        }
        return instance;
    }

    @ApiOperation(value = "Add a new user", response = Void.class)
    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(@ApiParam(value = "User details", required = true) User user) {
        logger.info(String.format("addUser - Parameters: id=%s, name=%s, surname=%s, email=%s, birthDate=%s",
                user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getBirthDate()));
        try {
            this.manager.addUser(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getBirthDate());
            logger.info(String.format("addUser - User added: %s", user.getId()));
        } catch (Exception e) {
            logger.error(String.format("addUser - Error adding user: %s", user.getId()), e);
        }
    }

    @ApiOperation(value = "Get all users", response = List.class)
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        logger.info("getUsers - No parameters");
        try {
            List<User> users = this.manager.getUsers();
            logger.info(String.format("getUsers - Returning %d users", users.size()));
            return users;
        } catch (Exception e) {
            logger.error("getUsers - Error retrieving users", e);
            return null;
        }
    }

    @ApiOperation(value = "Get user by ID", response = User.class)
    @GET
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@ApiParam(value = "User ID", required = true) @PathParam("userId") String userId) {
        logger.info(String.format("getUser - Parameters: id=%s", userId));
        try {
            User user = this.manager.getUser(userId);
            logger.info(String.format("getUser - Returning user: %s", user != null ? user.getId() : "not found"));
            return user;
        } catch (Exception e) {
            logger.error(String.format("getUser - Error retrieving user with id: %s", userId), e);
            return null;
        }
    }

    @ApiOperation(value = "Add point of interest", response = Void.class)
    @POST
    @Path("/pointsOfInterest")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPointOfInterest(@ApiParam(value = "Point of interest details", required = true) PointOfInterest pointOfInterest) {
        logger.info(String.format("addPointOfInterest - Parameters: x=%d, y=%d, type=%s",
                pointOfInterest.getX(), pointOfInterest.getY(), pointOfInterest.getType()));
        try {
            this.manager.addPointOfInterest(pointOfInterest.getX(), pointOfInterest.getY(), pointOfInterest.getType());
            logger.info(String.format("addPointOfInterest - Point added at (%d, %d) of type %s",
                    pointOfInterest.getX(), pointOfInterest.getY(), pointOfInterest.getType()));
        } catch (Exception e) {
            logger.error(String.format("addPointOfInterest - Error adding point at (%d, %d) of type %s",
                    pointOfInterest.getX(), pointOfInterest.getY(), pointOfInterest.getType()), e);
        }
    }

    @ApiOperation(value = "Get points of interest by type", response = List.class)
    @GET
    @Path("/pointsOfInterest")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PointOfInterest> getPointsByType(@ApiParam(value = "Point type", required = true) @QueryParam("type") PointOfInterest.ElementType type) {
        logger.info(String.format("getPointsByType - Parameters: type=%s", type));
        try {
            List<PointOfInterest> points = this.manager.getPointsByType(type);
            logger.info(String.format("getPointsByType - Returning %d points of type %s", points.size(), type));
            return points;
        } catch (Exception e) {
            logger.error(String.format("getPointsByType - Error retrieving points of type %s", type), e);
            return null;
        }
    }

    @ApiOperation(value = "Register a user visit to a point of interest", response = Void.class)
    @POST
    @Path("/userVisits")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerUserVisit(@ApiParam(value = "User visit details", required = true) UserVisit userVisit) {
        logger.info(String.format("registerUserVisit - Parameters: userId=%s, x=%d, y=%d",
                userVisit.getUserId(), userVisit.getX(), userVisit.getY()));
        try {
            this.manager.registerUserVisit(userVisit.getUserId(), userVisit.getX(), userVisit.getY());
            logger.info(String.format("registerUserVisit - User %s visited point (%d, %d)", userVisit.getUserId(), userVisit.getX(), userVisit.getY()));
        } catch (Exception e) {
            logger.error(String.format("registerUserVisit - Error registering visit for user %s at (%d, %d)", userVisit.getUserId(), userVisit.getX(), userVisit.getY()), e);
        }
    }
}