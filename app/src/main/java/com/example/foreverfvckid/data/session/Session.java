package com.example.foreverfvckid.data.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    protected final SharedPreferences sharedPreferences;
    protected final SharedPreferences.Editor editor;
    public static final String SHARED_PREFERENCE_NAME = "session";

    public Session(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}
