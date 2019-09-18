package ua.yakovenko.model.dao.impl;

public interface Query {
    String QUERY_ADD = "INSERT INTO user (username , password , role) VALUES (? ,? ,?)";
    String QUERY_FIND_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    String QUERY_FIND_ALL = "SELECT * FROM user";
    String QUERY_UPDATE_USER = "UPDATE user SET username = ? , password = ?, role = ? WHERE id = ?";
    String QUERY_DELETE_USER_BY_ID = "DELETE FROM user  WHERE id = ?";
}
