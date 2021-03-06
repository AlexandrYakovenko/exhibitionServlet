package ua.yakovenko.model.service;

import ua.yakovenko.model.dao.DaoFactory;
import ua.yakovenko.model.dao.ExhibitionDao;
import ua.yakovenko.model.dao.UserDao;
import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.Role;
import ua.yakovenko.model.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public List<User> findAllUsers(){
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findAll();
        }
    }

    public void addUser(String username, String password) {
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
        try (UserDao userDao = daoFactory.createUserDao()) {
            Optional<User> user =
                    Optional.ofNullable(
                            userDao.findByUsernameAndPassword(
                                    username, password));
            return user;
        }
    }

    public User findById(Long editId) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            return userDao.findById(editId);
        }
    }

    public void update(User user) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.update(user);
        }
    }

    public void updateBalance(User user, Long value) {
        try (UserDao userDao = daoFactory.createUserDao()) {
            userDao.updateBalance(user, value);
        }
    }

    public void buyTicket(User user, Long ticketId) throws SQLException {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao();
             UserDao userDao = daoFactory.createUserDao()
        ) {
            Exhibition exhibition = exhibitionDao.findById(ticketId);
            Long userMoney = user.getAccountMoney();
            Long ticketPrice = exhibition.getPrice();

            if (userMoney >= ticketPrice) {
                userDao.buyTicket(user, exhibition);
            } else {
                throw new RuntimeException("Not enough money");
            }
        }
    }
}
