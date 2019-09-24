package ua.yakovenko.model.dao.impl;

import ua.yakovenko.model.dao.UserDao;
import ua.yakovenko.model.dao.mapper.UserMapper;
import ua.yakovenko.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao {
    private Connection connection;
    private UserMapper mapper;

    public UserJdbcDao(Connection connection) {
        this.connection = connection;
        mapper = new UserMapper();
    }

    @Override
    public void add(User entity) throws SQLException {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                                    QUERY_USER_ADD)
        ) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole().name());
            ps.setBoolean(4, entity.isActive());
            ps.setLong(5, entity.getAccountMoney());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Invalid input");
        }
    }

    @Override
    public User findByUsername(String username) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_FIND_BY_USERNAME)
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User findById(Long id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_FIND_BY_ID)
        ) {
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();

        try (Statement statement =
                     connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(QUERY_USER_FIND_ALL);

            while (rs.next()) {
                User result = mapper.extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_USER_UPDATE)
        ) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setString(3, entity.getRole().name());
            ps.setBoolean(4, entity.isActive());
            ps.setLong(5, entity.getAccountMoney());
            ps.setLong(6, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                            QUERY_USER_DELETE_BY_ID)
        ) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String QUERY_USER_ADD =
            "INSERT INTO user (username , password , role, active, account_money) VALUES (? ,? ,?, ?, ?)";
    private static final String QUERY_USER_FIND_BY_USERNAME =
            "SELECT * FROM user WHERE username = ?";
    private static final String QUERY_USER_FIND_BY_ID =
            "SELECT * FROM user WHERE id = ?";
    private static final String QUERY_USER_FIND_ALL =
            "SELECT * FROM user";
    private static final String QUERY_USER_UPDATE =
            "UPDATE user SET username = ? , password = ?, role = ?, active = ?, account_money = ? WHERE id = ?";
    private static final String QUERY_USER_DELETE_BY_ID =
            "DELETE FROM user  WHERE id = ?";
}
