package com.example.foreverfvckid.data.session;

import android.content.Context;
import android.util.Log;

import com.example.foreverfvckid.data.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class UserSession extends Session {

    public static final String USER_SESSION_KEY = "user_session";

    public UserSession(Context context) {
        super(context);
    }

    private static User authenticatedUser = null;

    public void saveUserSession(User user) {
        Gson gson = new Gson();
        editor.putString(USER_SESSION_KEY, gson.toJson(user)).commit();
    }

    private User getUserSession() {
        String userJson = sharedPreferences.getString(USER_SESSION_KEY, null);
        if (userJson == null) return null;
        try {
            JSONObject jsonObject = new JSONObject(userJson);
            String userId = jsonObject.getString("userId");
            String fullName = jsonObject.getString("fullName");
            String username = jsonObject.getString("username");
            return new User(Integer.parseInt(userId), fullName, username);
        } catch (JSONException exception) {
            Log.d("JsonException", exception.getMessage());
            return null;
        }
    }

    public void removeUserSession() {
        editor.remove(USER_SESSION_KEY).commit();
        authenticatedUser = null;
    }

    public User getAuthenticatedUser() {
        if (authenticatedUser == null) authenticatedUser = getUserSession();
        return authenticatedUser;
    }
}
