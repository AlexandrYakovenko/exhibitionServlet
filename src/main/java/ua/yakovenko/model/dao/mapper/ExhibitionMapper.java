package ua.yakovenko.model.dao.mapper;

import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ExhibitionMapper implements GenericMapper<Exhibition> {
    private UserService userService = new UserService();

    @Override
    public Exhibition extractFromResultSet(ResultSet rs) throws SQLException {
        return Exhibition.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .showroom(rs.getString("showroom"))
                .description(rs.getString("description"))
                .author(userService.findByUsername(rs.getString("author")))
                .price(rs.getLong("price"))
                .date(rs.getDate("date"))
                .build();
    }

    @Override
    public Exhibition makeUnique(Map<Long, Exhibition> cache, Exhibition exhibition) {
        cache.putIfAbsent(exhibition.getId(), exhibition);
        return cache.get(exhibition.getId());
    }
}
