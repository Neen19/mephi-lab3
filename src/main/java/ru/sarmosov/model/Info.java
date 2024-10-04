package ru.sarmosov.model;

public class Info {

    private int id;

    private String info;

    public Info(int id) {
        this.id = id;
    }

    public Info(String info) {
        this.info = info;
    }

    public Info(int id, String info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
