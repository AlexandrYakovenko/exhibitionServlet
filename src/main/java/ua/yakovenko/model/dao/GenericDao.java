package ua.yakovenko.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void add(T entity) throws SQLException;

    T findByUsername(String username);

    List<T> findAll();

    void update(T entity);

    void delete(int id);

    void close();
}
