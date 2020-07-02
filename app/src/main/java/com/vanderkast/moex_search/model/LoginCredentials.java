package com.vanderkast.moex_search.model;

public class LoginCredentials {
    private String email;
    private String password;
    private boolean save;

    public LoginCredentials(String email, String password, boolean save) {
        this.email = email;
        this.password = password;
        this.save = save;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSave() {
        return save;
    }
}
