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
// DB - raw data - entity - DAO (aka repository) - service (model) - service - .... - (usecase) - listener or servlet or controller (dto)
}
