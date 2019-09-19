package ua.yakovenko.model.dao.mapper;

import ua.yakovenko.model.entity.Exhibition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExhibitionMapper implements GenericMapper<Exhibition> {
    @Override
    public Exhibition extractFromResultSet(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Exhibition makeUnique(Map<Long, Exhibition> cache, Exhibition teacher) {
        return null;
    }
}
