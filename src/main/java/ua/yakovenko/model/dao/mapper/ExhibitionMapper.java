package ua.yakovenko.model.dao.mapper;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExhibitionMapper implements GenericMapper<Exhibition> {
    @Override
    public Exhibition extractFromResultSet(ResultSet rs) throws SQLException {
        return Exhibition.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .showroom(rs.getString("showroom"))
                .description(rs.getString("description"))
                .author((User)rs.getObject("author"))
                .price(rs.getLong("price"))
                .date(rs.getDate("date"))
                .build();
    }

    @Override
    public Exhibition makeUnique(Map<Long, Exhibition> cache, Exhibition teacher) {
        return null;
    }
}
