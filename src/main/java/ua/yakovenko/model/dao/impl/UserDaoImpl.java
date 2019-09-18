package ua.yakovenko.model.dao.impl;

import ua.yakovenko.model.dao.UserDao;
import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ua.yakovenko.model.dao.impl.Query.*;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(User entity) throws SQLException {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                                    QUERY_ADD)
        ) {
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            ps.setInt(3, Arrays.asList(Role.values()).indexOf(entity.getRole()));

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
                             QUERY_FIND_BY_USERNAME)
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return extractFromResultSet(rs);
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
            ResultSet rs = statement.executeQuery(QUERY_FIND_ALL);

            while (rs.next()) {
                User result = extractFromResultSet(rs);
                resultList.add(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(User user) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_UPDATE_USER)
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setObject(3, user.getRole());
            ps.setLong(4, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                            QUERY_DELETE_USER_BY_ID)
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

    private User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .role(Role.values()[rs.getInt("role")])
                .build();
    }
}
