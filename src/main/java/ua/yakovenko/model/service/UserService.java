package ua.yakovenko.model.service;

import ua.yakovenko.model.dao.DaoFactory;
import ua.yakovenko.model.dao.UserDao;
import ua.yakovenko.model.entity.Role;
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

    public void addUser(String username, String password) throws SQLException {
        User newUser = User.builder()
                .username(username)
                .password(password)
                .role(Role.USER)
                .active(true)
                .accountMoney(0L)
                .build();

        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.add(newUser);
        }
    }

    public Optional<User> findUser(String username, String password){
        Optional<User> user = Optional.ofNullable(userDao.findByUsername(username));

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }

        return Optional.empty();
    }

    public Optional<User> findById(Long editId) {
        Optional<User> user = Optional.ofNullable(userDao.findById(editId));

        if (!user.isPresent()) {
            return Optional.empty();
        }

        return user;
    }
}
