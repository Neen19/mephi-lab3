package ru.sarmosov.dao;

import ru.sarmosov.model.Info;

public class InfoDAO extends AbstractDAO<Info> {

    private int id = 0;

    public InfoDAO() {
        super(Info.class);
    }

    public void save(Info info) {
        info.setId(++id);
        save(String.valueOf(id), info);
    }

    public void update(Info info) {
        cache.put(String.valueOf(info.getId()), info);
    }

}
