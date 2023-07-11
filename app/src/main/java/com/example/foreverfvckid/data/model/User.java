package com.example.foreverfvckid.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class User {

    public static final int FULL_NAME_MAX_LENGTH = 20;
    public static final int USERNAME_MIN_LENGTH = 5;
    public static final int USERNAME_MAX_LENGTH = 15;
    public static final int PASSWORD_MIN_LENGTH = 8;
    public static final int PASSWORD_MAX_LENGTH = 100;
    @SerializedName("userId")
    private final Integer userId;
    @SerializedName("fullName")
    private final String fullName;
    @SerializedName("username")
    private final String username;
    private static final User[] dummyUser = {
            new User(1, "John Doe", "johndoe123")
    };

    public User(Integer userId, String fullName, String username) {
        this.userId = userId;
        this.fullName = fullName;
        this.username = username;
    }

    public User(String fullName, String username) {
        this(null, fullName, username);
    }

    public static ArrayList<User> getDummyUser() {
        return new ArrayList<>(Arrays.asList(dummyUser));
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }
}
