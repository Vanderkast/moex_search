package com.vanderkast.moex_search.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.snackbar.Snackbar;
import com.vanderkast.moex_search.R;
import com.vanderkast.moex_search.helper.InteractiveActivity;
import com.vanderkast.moex_search.model.Result;
import com.vanderkast.moex_search.ui.MainActivity;
import com.vanderkast.moex_search.use_case.LoginUseCase;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class LoginActivity extends InteractiveActivity {
    @Inject
    LoginViewModelFactory viewModelFactory;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
        loginViewModel.getResult().observe(this, this::onLoginResult);

        final EditText email = findViewById(R.id.email_form);
        final EditText password = findViewById(R.id.password_form);
        final CheckBox staySignedIn = findViewById(R.id.stay_signed_in_check_box);
        findViewById(R.id.login_button).setOnClickListener(view -> {
            loginViewModel.setEmail(email.getText().toString());
            loginViewModel.setPassword(password.getText().toString());
            loginViewModel.setSaveCredentials(staySignedIn.isChecked());
            loginViewModel.login();
        });
    }

    private void onLoginResult(Result result) {
        View coordinator = findViewById(R.id.login_coordinator_layout);
        if (result == null) {
            Snackbar.make(coordinator, R.string.no_response_result, Snackbar.LENGTH_SHORT).show();
            return;
        }
        if (result instanceof Result.Failure
                && ((Result.Failure) result).getDescription() instanceof LoginUseCase.AuthException) {
            Snackbar.make(coordinator, R.string.wrong_credentials, Snackbar.LENGTH_SHORT).show();
            return;
        }
        Snackbar.make(coordinator, R.string.success, Snackbar.LENGTH_SHORT).show();
        runMain();
    }

    private void runMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
