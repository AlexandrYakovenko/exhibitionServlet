package ua.yakovenko.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T> extends AutoCloseable {

    void add(T entity) throws SQLException;

    T findByUsername(String username);

    T findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(Long id);

    void close();
}
