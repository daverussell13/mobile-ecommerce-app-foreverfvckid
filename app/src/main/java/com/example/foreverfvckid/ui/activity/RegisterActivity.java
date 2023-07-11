package com.example.foreverfvckid.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foreverfvckid.R;
import com.example.foreverfvckid.data.db.SQLiteUserHelper;
import com.example.foreverfvckid.data.model.User;
import com.example.foreverfvckid.data.session.UserSession;
import com.example.foreverfvckid.ui.activity.LoginActivity;
import com.example.foreverfvckid.ui.activity.MainActivity;
import com.example.foreverfvckid.ui.form.RegisterForm;
import com.example.foreverfvckid.utils.StringUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    private TextView hplSignIn;
    private TextInputLayout tlFullName, tlUsername, tlPassword, tlConfPassword;
    private EditText edtFullName, edtUsername, edtPassword, edtConfPassword;
    private Button btnSignUp;
    private RegisterForm registerForm;
    private SQLiteUserHelper dbHelper;
    private UserSession userSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        hplSignIn = findViewById(R.id.hpl_sign_in);
        tlFullName = findViewById(R.id.tl_fullname);
        tlUsername = findViewById(R.id.tl_username);
        tlPassword = findViewById(R.id.tl_password);
        tlConfPassword = findViewById(R.id.tl_conf_password);
        edtFullName = findViewById(R.id.te_fullname);
        edtUsername = findViewById(R.id.te_username);
        edtPassword = findViewById(R.id.te_password);
        edtConfPassword = findViewById(R.id.te_conf_password);
        btnSignUp = findViewById(R.id.btn_sign_up);
    }

    private void initHandlers() {
        hplSignIn.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class)); finish();
        });

        btnSignUp.setOnClickListener(v -> {
            registerForm = new RegisterForm(
                    edtFullName.getText().toString(),
                    edtUsername.getText().toString(),
                    edtPassword.getText().toString(),
                    edtConfPassword.getText().toString()
            );
            if (!formValidation()) return;
            if (!dbHelper.checkUsernameAvailability(registerForm.getUsername())) {
                tlUsername.setError("Username already taken");
                return;
            }
            boolean success = dbHelper.insertNewUser(
                    new User(registerForm.getFullName(), registerForm.getUsername()),
                    StringUtils.hashStringWithBcrypt(registerForm.getPassword())
            );
            if (!success) {
                fireErrorAlertDialog();
                return;
            }
            fireSuccessAlertDialog();
        });
    }

    private boolean formValidation() {
        boolean valid = true;
        resetFormError();
        if (registerForm.fullNameValidation() != null) {
            tlFullName.setError(registerForm.fullNameValidation());
            valid = false;
        }
        if (registerForm.usernameValidation() != null) {
            tlUsername.setError(registerForm.usernameValidation());
            valid = false;
        }
        if (registerForm.passwordValidation() != null) {
            tlPassword.setError(registerForm.passwordValidation());
            valid = false;
        }
        if (registerForm.confPasswordValidation() != null) {
            tlConfPassword.setError(registerForm.confPasswordValidation());
            valid = false;
        }
        return valid;
    }

    private void resetFormError() {
        tlFullName.setErrorEnabled(false);
        tlUsername.setErrorEnabled(false);
        tlPassword.setErrorEnabled(false);
        tlConfPassword.setErrorEnabled(false);
    }

    private void fireSuccessAlertDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Success! 🎉")
                .setMessage(getResources().getString(R.string.register_success_msg))
                .setPositiveButton(getResources().getString(R.string.dismiss), (dialog, which) -> {
                    startActivity(new Intent(this, LoginActivity.class));
                    finish();
                })
                .show();
    }

    private void fireErrorAlertDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Error! 💀")
                .setMessage(getResources().getString(R.string.register_error_msg))
                .setPositiveButton(getResources().getString(R.string.dismiss), (dialog, which) -> {})
                .show();
    }
}