package ua.yakovenko.model.dao.impl;

import ua.yakovenko.model.dao.ExhibitionDao;
import ua.yakovenko.model.entity.Exhibition;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ExhibitionDaoImpl implements ExhibitionDao {
    private Connection connection;

    public ExhibitionDaoImpl(Connection connection) { this.connection = connection; }

    @Override
    public void add(Exhibition entity) throws SQLException {

    }

    @Override
    public Exhibition findByUsername(String username) {
        return null;
    }

    @Override
    public List<Exhibition> findAll() {
        return null;
    }

    @Override
    public void update(Exhibition entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
