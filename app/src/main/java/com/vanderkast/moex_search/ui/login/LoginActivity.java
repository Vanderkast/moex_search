package com.vanderkast.moex_search.ui.login;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.snackbar.Snackbar;
import com.vanderkast.moex_search.R;
import com.vanderkast.moex_search.model.Result;
import com.vanderkast.moex_search.use_cases.LoginUseCase;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;


@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {
    @Inject
    LoginViewModelFactory viewModelFactory;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
        loginViewModel.getResult().observe(this, this::onLoginResult);

        ((Button) findViewById(R.id.login_button)).setOnClickListener(v -> loginViewModel.login());
    }

    private void onLoginResult(Result result) {
        if(result != null)
            Snackbar.make(findViewById(R.id.login_coordinator_layout), result.getClass().getName(), Snackbar.LENGTH_SHORT).show();
    }
}
