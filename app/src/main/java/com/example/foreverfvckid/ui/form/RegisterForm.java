package com.example.foreverfvckid.ui.form;

import com.example.foreverfvckid.data.model.User;
import com.example.foreverfvckid.utils.StringUtils;


public class RegisterForm {

    private final String fullName;
    private final String username;
    private final String password;
    private final String confPassword;

    public RegisterForm(String fullName, String username, String password, String confPassword) {
        this.fullName = StringUtils.capitalizeEachWord(fullName);
        this.username = username;
        this.password = password;
        this.confPassword = confPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String usernameValidation() {
        String error = null;
        if (username.trim().isEmpty()) {
            error = "Username required";
        } else if (username.length() < User.USERNAME_MIN_LENGTH || username.length() > User.USERNAME_MAX_LENGTH) {
            error = "Username must be between " + User.USERNAME_MIN_LENGTH + " - " + User.USERNAME_MAX_LENGTH + " characters";
        }
        return error;
    }

    public String fullNameValidation() {
        String error = null;
        if (fullName.trim().isEmpty()) {
            error = "Full name required";
        } else if (fullName.length() > User.FULL_NAME_MAX_LENGTH) {
            error = "Full name must not exceed " + User.FULL_NAME_MAX_LENGTH + " characters";
        }
        return error;
    }

    public String passwordValidation() {
        String error = null;
        if (password.trim().isEmpty()) {
            error = "Password required";
        } else if (password.length() < User.PASSWORD_MIN_LENGTH || password.length() > User.PASSWORD_MAX_LENGTH) {
            error = "Password must be between " + User.PASSWORD_MIN_LENGTH + " - " + User.PASSWORD_MAX_LENGTH;
        }
        return error;
    }

    public String confPasswordValidation() {
        if (!password.equals(confPassword)) return "Confirmation password does not match";
        return null;
    }
}
