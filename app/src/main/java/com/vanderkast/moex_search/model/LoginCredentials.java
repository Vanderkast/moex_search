package com.vanderkast.moex_search.model;

public class LoginCredentials {
    private String email;
    private String password;
    private boolean needSave;

    public LoginCredentials(String email, String password, boolean needSave) {
        this.email = email;
        this.password = password;
        this.needSave = needSave;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isNeedSave() {
        return needSave;
    }
}
