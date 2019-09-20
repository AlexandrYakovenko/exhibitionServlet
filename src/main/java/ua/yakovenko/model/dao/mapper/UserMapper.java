package ua.yakovenko.model.dao.mapper;

import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements GenericMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .role(Role.valueOf(rs.getString("role")))
                .active(rs.getBoolean("active"))
                .accountMoney(rs.getLong("account_money"))
                .build();
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User teacher) {
        return null;
    }
}
