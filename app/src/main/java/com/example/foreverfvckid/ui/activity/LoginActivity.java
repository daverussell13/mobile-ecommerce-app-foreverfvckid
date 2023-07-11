package com.example.foreverfvckid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.db.SQLiteUserHelper;
import com.example.foreverfvckid.data.model.User;
import com.example.foreverfvckid.data.session.UserSession;
import com.example.foreverfvckid.ui.form.LoginForm;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextView hplSignUp;
    private Button btnSignIn;
    private LoginForm loginForm;
    private EditText edtUsername, edtPassword;
    private TextInputLayout tlUsername, tlPassword;
    private SQLiteUserHelper dbHelper;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new SQLiteUserHelper(this);
        userSession = new UserSession(this);
        initUI();
        initHandlers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (userSession.getAuthenticatedUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void initUI() {
        hplSignUp = findViewById(R.id.hpl_sign_up);
        btnSignIn = findViewById(R.id.btn_sign_in);
        edtUsername = findViewById(R.id.te_username);
        edtPassword = findViewById(R.id.te_password);
        tlUsername = findViewById(R.id.tl_username);
        tlPassword = findViewById(R.id.tl_password);
    }

    private void initHandlers() {
        hplSignUp.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class)); finish();
        });

        btnSignIn.setOnClickListener(v -> {
            loginForm = new LoginForm(edtUsername.getText().toString(), edtPassword.getText().toString());
            if (!formValidation()) return;
            User user = dbHelper.authAttempt(loginForm.getUsername(), loginForm.getPassword());
            if (user == null)  {
                fireErrorAlertDialog();
                return;
            }
            Log.d("test-gw", user.getUsername() + ", " + user.getFullName() + ", " + user.getUserId());
            userSession.saveUserSession(user);
            startActivity(new Intent(this, MainActivity.class)); finish();
        });
    }

    private boolean formValidation() {
        boolean valid = true;
        resetFormError();
        if (loginForm.validateUsername() != null) {
            tlUsername.setError(loginForm.validateUsername());
            valid = false;
        }
        if (loginForm.validatePassword() != null) {
            tlPassword.setError(loginForm.validatePassword());
            valid = false;
        }
        return valid;
    }

    private void resetFormError() {
        tlUsername.setErrorEnabled(false);
        tlPassword.setErrorEnabled(false);
    }

    private void fireErrorAlertDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Sign In Failed! ❌")
                .setMessage(getResources().getString(R.string.login_wrong_credentials_msg))
                .setPositiveButton(getResources().getString(R.string.dismiss), (dialog, which) -> {})
                .show();
    }
}