package com.example.foreverfvckid.ui.form;

public class LoginForm {

    private final String username;
    private final String password;

    public LoginForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String validateUsername() {
        if (username.trim().isEmpty()) return "Username required";
        return null;
    }

    public String validatePassword() {
        if (password.trim().isEmpty()) return  "Password required";
        return null;
    }
}