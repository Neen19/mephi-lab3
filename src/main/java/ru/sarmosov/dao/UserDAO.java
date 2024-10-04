package ru.sarmosov.dao;

import ru.sarmosov.model.User;

public class UserDAO extends AbstractDAO<User> {

    private static UserDAO instance;

    private UserDAO() {
        super(User.class);
    }

    public void save(User user) {
        save(user.getName(), user);
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        } return instance;
    }

}
