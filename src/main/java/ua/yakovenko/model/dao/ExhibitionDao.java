package ua.yakovenko.model.dao;

import ua.yakovenko.model.entity.Exhibition;

import java.util.List;

public interface ExhibitionDao extends GenericDao<Exhibition> {
    List<Exhibition> findByShowroom(String username);
}
