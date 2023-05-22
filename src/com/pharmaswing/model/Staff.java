package com.pharmaswing.model;

public class Staff {
    private int id;
    private String name;
    private String position;
    private String username;
    private String password;
    private boolean active;

    public Staff(int id, String name, String position, String username, String password, boolean active) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffname() {
        return name;
    }

    public void setStaffname(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

