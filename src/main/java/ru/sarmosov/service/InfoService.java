package ru.sarmosov.service;

import ru.sarmosov.dao.InfoDAO;
import ru.sarmosov.model.Info;
import ru.sarmosov.repository.InfoDAORepository;

import java.util.List;

public class InfoService {

    private final InfoDAO dao;

    public InfoService(String login) {
        InfoDAORepository repository = InfoDAORepository.getInstance();
        dao = repository.findById(login);
    }

    public Info get(int id) {
        return dao.findById(String.valueOf(id));
    }

    public List<Info> getAll() {
        return dao.findAll();
    }

    public void save(Info info) {
        dao.save(info);
    }

    public void update(Info info) {
        dao.update(info);
    }

    public void delete(int id) {
        dao.delete(String.valueOf(id));
    }

    public InfoDAO getDao() {
        return dao;
    }
}
