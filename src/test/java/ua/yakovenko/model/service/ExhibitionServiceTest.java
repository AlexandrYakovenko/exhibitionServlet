package ua.yakovenko.model.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.yakovenko.model.dao.ExhibitionDao;
import ua.yakovenko.model.dao.impl.ExhibitionJdbcDao;
import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ExhibitionServiceTest {

    private static final String USER = "roots";

    private static final String PASSWORD = "roots";

    private static final String URL = "jdbc:mysql://localhost:3306/exhibition_servlet_db_test?useTimezone=true&serverTimezone=UTC";

    private ExhibitionDao exhibitionDao;

    private Connection connection;

    @Before
    public void before() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            exhibitionDao = new ExhibitionJdbcDao(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add() {
        final String name = "name0";
        final User user = User.builder().id(1L).build();
        final Date date = new Date(0);
        final Exhibition expected = Exhibition.builder()
                .name(name)
                .showroom("")
                .description("")
                .author(user)
                .price(0L)
                .date(date)
                .build();

        exhibitionDao.add(expected);

        final Exhibition actual = exhibitionDao.findByName(name);

        exhibitionDao.deleteEntity(actual);

        Assert.assertNotNull(actual);
    }

    @Test
    public void findAll() {
        final String name = "name1";
        final User user = User.builder().id(1L).build();
        final Date date = new Date(0);
        final Exhibition exhibition = Exhibition.builder()
                .name(name)
                .showroom("")
                .description("")
                .author(user)
                .price(0L)
                .date(date)
                .build();

        exhibitionDao.add(exhibition);

        List<Exhibition> list = exhibitionDao.findAll();

        exhibitionDao.deleteEntity(list.get(0));

        Assert.assertNotNull(list);
    }

    @Test
    public void deleteById() {
        final String name = "name2";
        final User user = User.builder().id(1L).build();
        final Date date = new Date(0);
        final Exhibition exhibition = Exhibition.builder()
                .name(name)
                .showroom("")
                .description("")
                .author(user)
                .price(0L)
                .date(date)
                .build();

        exhibitionDao.add(exhibition);

        final Exhibition tmp = exhibitionDao.findByName(name);

        exhibitionDao.deleteEntity(tmp);

        final Exhibition expected = exhibitionDao.findByName(name);

        Assert.assertNull(expected);
    }

    @Test
    public void update() {
        final String name = "name3";
        final User user = User.builder().id(1L).build();
        final Date date = new Date(0);
        final Exhibition exhibition = Exhibition.builder()
                .name(name)
                .showroom("")
                .description("")
                .author(user)
                .price(0L)
                .date(date)
                .build();

        exhibitionDao.add(exhibition);

        Exhibition actual = exhibitionDao.findByName(name);
        actual.setPrice(100L);

        exhibitionDao.update(actual);

        Exhibition expected = exhibitionDao.findByName(name);

        Assert.assertEquals(actual.getPrice(), expected.getPrice());
    }

    @Test
    public void findByShowroom() {
        final String showroom = "showroom";
        final User user = User.builder().id(1L).build();
        final Date date = new Date(0);
        final Exhibition exhibition = Exhibition.builder()
                .name("")
                .showroom(showroom)
                .description("")
                .author(user)
                .price(0L)
                .date(date)
                .build();

        exhibitionDao.add(exhibition);

        List<Exhibition> list = exhibitionDao.findByShowroom(showroom);

        Assert.assertEquals(showroom, list.get(0).getShowroom());
    }
}