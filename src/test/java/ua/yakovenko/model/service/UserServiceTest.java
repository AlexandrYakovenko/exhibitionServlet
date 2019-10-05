package ua.yakovenko.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.yakovenko.model.dao.UserDao;
import ua.yakovenko.model.dao.impl.UserJdbcDao;
import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserServiceTest {
    private static final String USER = "roots";
    private static final String PASSWORD = "roots";
    private static final String URL = "jdbc:mysql://localhost:3306/exhibition_servlet_db_test?useTimezone=true&serverTimezone=UTC";

    private UserDao userDao;
    private Connection connection;

    @Before
    public void before() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            userDao = new UserJdbcDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findAllUsers() {
        final String username = "user0";
        final User user = User.builder()
                .username(username)
                .password("")
                .role(Role.USER)
                .active(true)
                .accountMoney(0L)
                .build();

        userDao.add(user);

        List<User> list = userDao.findAll();

        userDao.delete(list.get(0).getId());

        Assert.assertNotNull(list);
    }

    @Test
    public void addUser() {
        final String u = "user1";
        final String p = "";
        final User user = User.builder()
                .username(u)
                .password(p)
                .role(Role.USER)
                .active(true)
                .accountMoney(0L)
                .build();

        userDao.add(user);

        User expected = userDao.findByUsernameAndPassword(u, p);

        userDao.delete(expected.getId());

        Assert.assertNotNull(expected);
    }

    @Test
    public void updateBalance() {
        final String u = "user2";
        final String p = "";
        final User user = User.builder()
                .username(u)
                .password(p)
                .role(Role.USER)
                .active(true)
                .accountMoney(0L)
                .build();

        userDao.add(user);

        User tmp = userDao.findByUsernameAndPassword(u, p);
        userDao.updateBalance(tmp, 100L);
        User expected = userDao.findByUsernameAndPassword(u, p);

        userDao.delete(expected.getId());

        Assert.assertSame(100L, expected.getAccountMoney());
    }

    @Test
    public void findUser() {
        final String u = "user3";
        final String p = "";
        final User user = User.builder()
                .username(u)
                .password(p)
                .role(Role.USER)
                .active(true)
                .accountMoney(0L)
                .build();

        userDao.add(user);

        User expected = userDao.findByUsernameAndPassword(u, p);

        userDao.delete(expected.getId());

        Assert.assertNotNull(expected);
    }
}