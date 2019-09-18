package ua.yakovenko.model.service;

import ua.yakovenko.model.dao.DaoFactory;
import ua.yakovenko.model.dao.UserDao;
import ua.yakovenko.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDao userDao = daoFactory.createUserDao();

    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    public void addUser(User user) throws SQLException {
        userDao.add(user);
    }

    public Optional<User> findUser(String username, String password){
        Optional<User> user = Optional.ofNullable((userDao.findByUsername(username)));

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }

        return Optional.empty();
    }
}
