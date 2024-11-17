package edu.upc.dsa;

import edu.upc.dsa.models.PointOfInterest;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.UserVisit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class CampusGameManagerTest {

    private CampusGameManagerImpl campusGameService;

    @Before
    public void setUp() {
        this.campusGameService = new CampusGameManagerImpl();

        // Add some users and points of interest for testing
        this.campusGameService.addUser("101", "Eve", "Johnson", "eve@example.com", String.valueOf(new Date()));
        this.campusGameService.addUser("102", "Mark", "Taylor", "mark@example.com", String.valueOf(new Date()));

        this.campusGameService.addPointOfInterest(15, 25, PointOfInterest.ElementType.TREE);
        this.campusGameService.addPointOfInterest(35, 45, PointOfInterest.ElementType.BRIDGE);
    }

    @After
    public void tearDown() {
        // Cleanup data if necessary
        campusGameService = null;
    }

    @Test
    public void addUserTest() {
        Assert.assertEquals(2, campusGameService.getUsers().size());

        this.campusGameService.addUser("103", "Sophia", "Lopez", "sophia@example.com", String.valueOf(new Date()));

        Assert.assertEquals(3, campusGameService.getUsers().size());
    }

    @Test
    public void listUsersTest() {
        // Retrieve all users
        List<User> users = campusGameService.getUsers();


        // The second user should be "Eve Johnson"
        Assert.assertEquals("Eve", users.get(0).getName());
        Assert.assertEquals("Johnson", users.get(0).getSurname());

        // The third user should be "Mark Taylor"
        Assert.assertEquals("Mark", users.get(1).getName());
        Assert.assertEquals("Taylor", users.get(1).getSurname());
    }

    @Test
    public void getUserTest() {
        User user = campusGameService.getUser("101");
        Assert.assertEquals("Eve", user.getName());
        Assert.assertEquals("Johnson", user.getSurname());

        // Test for a non-existent user
        Assert.assertNull(campusGameService.getUser("999"));
    }

    @Test
    public void addPointOfInterestTest() {
        Assert.assertEquals(1, campusGameService.getPointsByType(PointOfInterest.ElementType.TREE).size());

        this.campusGameService.addPointOfInterest(55, 65, PointOfInterest.ElementType.COIN);

        Assert.assertEquals(1, campusGameService.getPointsByType(PointOfInterest.ElementType.COIN).size());
    }

    @Test
    public void userVisitPointTest() {
        this.campusGameService.registerUserVisit("102", 15, 25);

        List<UserVisit> visits = campusGameService.getUserVisits("102");
        Assert.assertEquals(1, visits.size());
        Assert.assertEquals(15, visits.get(0).getX());
        Assert.assertEquals(25, visits.get(0).getY());

        // Test user visit record
        UserVisit userVisit = campusGameService.getUserVisits("102").get(0);
        Assert.assertNotNull(userVisit);
        Assert.assertEquals("102", userVisit.getUserId());
        Assert.assertEquals(15, userVisit.getPoint().getX());
        Assert.assertEquals(25, userVisit.getPoint().getY());
    }

    @Test
    public void getUsersByPointTest() {
        // 添加兴趣点并确保访问
        this.campusGameService.registerUserVisit("101", 15, 25);

        List<User> usersAtPoint = campusGameService.getUsersByPoint(15, 25);
        Assert.assertEquals(1, usersAtPoint.size());
        Assert.assertEquals("Eve", usersAtPoint.get(0).getName());

        // 测试不存在的兴趣点
        try {
            this.campusGameService.getUsersByPoint(100, 200);
            Assert.fail("Expected IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Point of interest not found", e.getMessage());
        }
    }

    @Test
    public void getPointsByTypeTest() {
        List<PointOfInterest> points = campusGameService.getPointsByType(PointOfInterest.ElementType.BRIDGE);
        Assert.assertEquals(1, points.size());
        Assert.assertEquals(35, points.get(0).getX());
        Assert.assertEquals(45, points.get(0).getY());

        // Test for a non-existent type
        points = campusGameService.getPointsByType(PointOfInterest.ElementType.SWORD);
        Assert.assertTrue(points.isEmpty());
    }
}