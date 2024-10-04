package ru.sarmosov.repository;

import ru.sarmosov.dao.AbstractDAO;
import ru.sarmosov.dao.InfoDAO;

public class InfoDAORepository extends AbstractDAO<InfoDAO> {

    private static InfoDAORepository instance;

    private InfoDAORepository() {
        super(InfoDAO.class);
    }

    public static InfoDAORepository getInstance() {
        if (instance == null) {
            synchronized (InfoDAORepository.class) {
                if (instance == null) {
                    instance = new InfoDAORepository();
                }
            }
        } return instance;
    }

}
