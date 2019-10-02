package ua.yakovenko.model.service;

import ua.yakovenko.model.dao.DaoFactory;
import ua.yakovenko.model.dao.ExhibitionDao;
import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public class ExhibitionService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void add(Exhibition exhibition) throws SQLException {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.add(exhibition);
        }
    }

    public List<Exhibition> findAll() {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findAll();
        }
    }

    public Exhibition findById(Long exhibitionId) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findById(exhibitionId);
        }
    }

    public void deleteById(Long idForDelete) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.delete(idForDelete);
        }
    }

    public void update(Exhibition exhibition) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.update(exhibition);
        }
    }

    public List<Exhibition> findByShowroom(String showroom) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findByShowroom(showroom);
        }
    }

    public List<Exhibition> findByAuthor(User author) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findByAuthor(author);
        }
    }

    public List<Exhibition> findBoughtTickets(User user) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findBoughtTickets(user);
        }
    }
}
