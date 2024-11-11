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

        // 添加一些用户和兴趣点以便测试
        this.campusGameService.addUser("1", "Alice", "Smith", "alice@example.com", new Date());
        this.campusGameService.addUser("2", "Jane", "Doe", "jane@example.com", new Date());

        this.campusGameService.addPointOfInterest(10, 20, PointOfInterest.ElementType.BRIDGE);
        this.campusGameService.addPointOfInterest(30, 40, PointOfInterest.ElementType.SWORD);
    }

    @After
    public void tearDown() {
        // 清理操作（如果需要）
        // 在这个例子中，数据清理由CampusGameManagerImpl类完成
    }

    @Test
    public void addUserTest() {
        Assert.assertEquals(2, campusGameService.getUsers().size());

        this.campusGameService.addUser("3", "Carlos", "Perez", "carlos@example.com", new Date());

        Assert.assertEquals(3, campusGameService.getUsers().size());
    }

    @Test
    public void listUsersTest() {
        // 获取所有用户
        List<User> users = campusGameService.getUsers();

        // 根据姓氏排序后，第一个用户应该是 "Jane Doe"
        Assert.assertEquals("Jane", users.get(0).getName());
        Assert.assertEquals("Doe", users.get(0).getSurname());

        // 第二个用户应该是 "Alice Smith"
        Assert.assertEquals("Alice", users.get(1).getName());
        Assert.assertEquals("Smith", users.get(1).getSurname());
    }

    @Test
    public void getUserTest() {
        User user = campusGameService.getUser("1");
        Assert.assertEquals("Alice", user.getName());
        Assert.assertEquals("Smith", user.getSurname());

        // 测试不存在的用户
        Assert.assertNull(campusGameService.getUser("999"));
    }

    @Test
    public void addPointOfInterestTest() {
        Assert.assertEquals(1, campusGameService.getPointsByType(PointOfInterest.ElementType.BRIDGE).size());

        this.campusGameService.addPointOfInterest(50, 60, PointOfInterest.ElementType.TREE);

        Assert.assertEquals(1, campusGameService.getPointsByType(PointOfInterest.ElementType.TREE).size());
    }

    @Test
    public void userVisitPointTest() {
        this.campusGameService.registerUserVisit("1", 10, 20);

        List<PointOfInterest> visits = campusGameService.getUserVisits("1");
        Assert.assertEquals(1, visits.size());
        Assert.assertEquals(10, visits.get(0).getX());
        Assert.assertEquals(20, visits.get(0).getY());
    }

    @Test
    public void getUsersByPointTest() {
        // 用户1访问了位置(10, 20)
        this.campusGameService.registerUserVisit("1", 10, 20);

        List<User> usersAtPoint = campusGameService.getUsersByPoint(10, 20);
        Assert.assertEquals(1, usersAtPoint.size());
        Assert.assertEquals("Alice", usersAtPoint.get(0).getName());

        // 测试兴趣点不存在的情况
        List<User> noUsers = campusGameService.getUsersByPoint(100, 100);
        Assert.assertTrue(noUsers.isEmpty());
    }

    @Test
    public void getPointsByTypeTest() {
        List<PointOfInterest> points = campusGameService.getPointsByType(PointOfInterest.ElementType.BRIDGE);
        Assert.assertEquals(1, points.size());
        Assert.assertEquals(10, points.get(0).getX());
        Assert.assertEquals(20, points.get(0).getY());

        // 测试不存在的类型
        points = campusGameService.getPointsByType(PointOfInterest.ElementType.COIN);
        Assert.assertTrue(points.isEmpty());
    }
}