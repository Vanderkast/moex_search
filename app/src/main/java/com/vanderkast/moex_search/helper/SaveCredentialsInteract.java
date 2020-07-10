package com.vanderkast.moex_search.helper;

public class SaveCredentialsInteract extends FutureInteract<Void> {
    private final String email;
    private final String password;

    public SaveCredentialsInteract(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
