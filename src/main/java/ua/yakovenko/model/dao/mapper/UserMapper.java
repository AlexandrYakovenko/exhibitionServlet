package ua.yakovenko.model.dao.mapper;

import ua.yakovenko.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements GenericMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User teacher) {
        return null;
    }
}
