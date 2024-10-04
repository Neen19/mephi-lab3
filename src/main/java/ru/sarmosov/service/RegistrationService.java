package ru.sarmosov.service;

import org.apache.commons.codec.digest.DigestUtils;
import ru.sarmosov.dao.InfoDAO;
import ru.sarmosov.dao.UserDAO;
import ru.sarmosov.model.User;
import ru.sarmosov.repository.InfoDAORepository;

public class RegistrationService {

    private final InfoDAORepository infoRepository = InfoDAORepository.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();

    public void register(String login, String password) {
        User user = new User(login, DigestUtils.sha256Hex(password));
        infoRepository.save(login, new InfoDAO());
        userDAO.save(login, user);
    }

    public boolean isPresent(String login) {
        return userDAO.findById(login) != null;
    }

}
