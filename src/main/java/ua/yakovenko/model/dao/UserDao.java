package ua.yakovenko.model.dao;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;

import java.sql.SQLException;

public interface UserDao extends GenericDao<User> {

    User findByUsernameAndPassword (String username, String password);

    void buyTicket(User user, Exhibition exhibition) throws SQLException;

    void updateBalance(User user, Long value);
}
