package ua.yakovenko.model.dao;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;

import java.util.List;

public interface ExhibitionDao extends GenericDao<Exhibition> {
    Exhibition findByName(String name);

    List<Exhibition> findByShowroom(String username);

    List<Exhibition> findByAuthor(User user);

    List<Exhibition> findBoughtTickets(User user);

    int countOfRecords();

    List<Exhibition> findDiapason(int from, int to);
}
