package ua.yakovenko.model.dao.impl;

public interface Query {
    //Query for users
    String QUERY_USER_ADD = "INSERT INTO user (username , password , role, active, account_money) VALUES (? ,? ,?, ?, ?)";
    String QUERY_USER_FIND_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    String QUERY_USER_FIND_ALL = "SELECT * FROM user";
    String QUERY_USER_UPDATE_USER = "UPDATE user SET username = ? , password = ?, role = ? WHERE id = ?";
    String QUERY_USER_DELETE_USER_BY_ID = "DELETE FROM user  WHERE id = ?";

    //Query for exhibitions
    String QUERY_EXHIBITION_ADD = "INSERT INTO user (username , password , role) VALUES (? ,? ,?)";
    String QUERY_EXHIBITION_FIND_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
    String QUERY_EXHIBITION_FIND_ALL = "SELECT * FROM user";
    String QUERY_EXHIBITION_UPDATE_USER = "UPDATE user SET username = ? , password = ?, role = ? WHERE id = ?";
    String QUERY_EXHIBITION_DELETE_USER_BY_ID = "DELETE FROM user  WHERE id = ?";
}
