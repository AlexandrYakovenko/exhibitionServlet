package ua.yakovenko.model.dao.impl;

import ua.yakovenko.model.dao.ExhibitionDao;
import ua.yakovenko.model.dao.mapper.ExhibitionMapper;
import ua.yakovenko.model.entity.Exhibition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExhibitionJdbcDao implements ExhibitionDao {
    private Connection connection;
    private ExhibitionMapper mapper;

    public ExhibitionJdbcDao(Connection connection) {
        this.connection = connection;
        mapper = new ExhibitionMapper();
    }

    @Override
    public void add(Exhibition entity) throws SQLException {
        try(PreparedStatement ps =
                connection.prepareStatement(
                        QUERY_EXHIBITION_ADD)
        ) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getShowroom());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getAuthor().getUsername());
            ps.setLong(5, entity.getPrice());
            ps.setDate(6, entity.getDate());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Exhibition findByUsername(String name) {
        try (PreparedStatement ps =
                    connection.prepareStatement(
                            QUERY_EXHIBITION_FIND_BY_NAME)
        ) {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Didn't find exhibition");
        }
        return null;
    }

    @Override
    public List<Exhibition> findAll() {
        List<Exhibition> resultList = new ArrayList<>();

        try(Statement statement =
                connection.createStatement()
        ){
           ResultSet rs = statement.executeQuery(QUERY_EXHIBITION_FIND_ALL);

           while (rs.next()) {
               Exhibition ex = mapper.extractFromResultSet(rs);
               resultList.add(ex);
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public void update(Exhibition entity) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_UPDATE)
        ) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getShowroom());
            ps.setString(3, entity.getDescription());
            ps.setString(4, entity.getAuthor().getUsername());
            ps.setLong(5, entity.getPrice());
            ps.setDate(6, entity.getDate());
            ps.setLong(7, entity.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("");
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement ps =
                     connection.prepareStatement(
                             QUERY_EXHIBITION_DELETE_BY_ID) 
        ) {
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String QUERY_EXHIBITION_ADD =
            "INSERT INTO exhibition (name , showroom , description, author, price, date) VALUES (? ,? ,?, ?, ?, ?)";
    private static final String QUERY_EXHIBITION_FIND_BY_NAME =
            "SELECT * FROM exhibition WHERE name = ?";
    private static final String QUERY_EXHIBITION_FIND_ALL =
            "SELECT * FROM exhibition";
    private static final String QUERY_EXHIBITION_UPDATE =
            "UPDATE exhibition SET name = ? , showroom = ?, description = ?, author = ?, price = ?, date = ? WHERE id = ?";
    private static final String QUERY_EXHIBITION_DELETE_BY_ID =
            "DELETE FROM exhibition  WHERE id = ?";

}
