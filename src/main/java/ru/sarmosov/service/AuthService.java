package ru.sarmosov.service;

import org.apache.commons.codec.digest.DigestUtils;
import ru.sarmosov.dao.UserDAO;

public class AuthService {

    private final UserDAO userDAO = UserDAO.getInstance();

    private boolean isPresent(String login) {
        return userDAO.findById(login) != null;
    }

    public Boolean auth(String login, String password) {
        if (!isPresent(login)) {
            return false;
        }
        String pasHash = DigestUtils.sha256Hex(password);
        return pasHash.equals(userDAO.findById(login).getPassword());
    }
}
