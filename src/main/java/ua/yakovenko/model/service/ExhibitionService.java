package ua.yakovenko.model.service;

import ua.yakovenko.model.dao.DaoFactory;
import ua.yakovenko.model.dao.ExhibitionDao;
import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class ExhibitionService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao();

    public void add(Exhibition exhibition) throws SQLException {
        exhibitionDao.add(exhibition);
    }

    public List<Exhibition> findAll() {
       return exhibitionDao.findAll();
    }

    public Exhibition findById(Long exhibitionId) {
        return exhibitionDao.findById(exhibitionId);
    }

    public void deleteById(Long idForDelete) {
        exhibitionDao.delete(idForDelete);
    }

    public void update(Exhibition exhibition) {
        exhibitionDao.update(exhibition);
    }

    public List<Exhibition> findByShowroom(String showroom) {
        return exhibitionDao.findByShowroom(showroom);
    }

    public List<Exhibition> findByAuthor(User author) {
        return exhibitionDao.findByAuthor(author);
    }
}
