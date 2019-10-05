package ua.yakovenko.model.service;

import ua.yakovenko.model.dao.DaoFactory;
import ua.yakovenko.model.dao.ExhibitionDao;
import ua.yakovenko.model.entity.Exhibition;
import ua.yakovenko.model.entity.User;

import java.util.List;

public class ExhibitionService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    public void add(Exhibition exhibition) {
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

    public void deleteById(Long id) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            exhibitionDao.delete(id);
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

    public int countOfRecords() {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.countOfRecords();
        }
    }

    public List<Exhibition> findDiapason(int from, int count) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExhibitionDao()) {
            return exhibitionDao.findDiapason(from, count);
        }
    }
}
