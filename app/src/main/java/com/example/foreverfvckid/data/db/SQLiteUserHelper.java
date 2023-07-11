package com.example.foreverfvckid.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.foreverfvckid.data.model.User;
import com.example.foreverfvckid.utils.StringUtils;

public class SQLiteUserHelper extends SQLiteHelper {

    public SQLiteUserHelper(Context context) {
        super(context);
    }

    public boolean insertNewUser(User user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("fullname", user.getFullName());
        values.put("username", user.getUsername());
        values.put("password", password);

        long newRowId = db.insert("users", null, values);
        db.close();

        return newRowId != -1;
    }

    public boolean checkUsernameAvailability(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT 1 FROM users WHERE username = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.rawQuery(query, selectionArgs);
        boolean exists = cursor.moveToFirst();

        cursor.close();
        db.close();

        return !exists;
    }

    public User authAttempt(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = "username = ?";
        String[] columns = new String[]{ "id", "fullname", "password" };
        String[] selectionArgs = new String[]{username};

        Cursor cursor = db.query("users", columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
            String hashedPass = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            if (StringUtils.verifyBcryptHash(password, hashedPass)) return new User(id, fullName, username);
        }

        cursor.close();
        db.close();

        return null;
    }
}
