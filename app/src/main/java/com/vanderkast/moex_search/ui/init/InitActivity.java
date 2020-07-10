package com.vanderkast.moex_search.ui.init;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsClient;
import com.vanderkast.moex_search.R;
import com.vanderkast.moex_search.helper.InteractiveActivity;
import com.vanderkast.moex_search.model.Result;
import com.vanderkast.moex_search.ui.MainActivity;
import com.vanderkast.moex_search.ui.login.LoginActivity;
import com.vanderkast.moex_search.ui.login.LoginViewModel;
import com.vanderkast.moex_search.ui.login.LoginViewModelFactory;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

@AndroidEntryPoint
public class InitActivity extends InteractiveActivity {
    @Inject
    LoginViewModelFactory viewModelFactory;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
        loginViewModel.getResult().observe(this, this::onLoginResult);

        CredentialsClient credentialsClient = Credentials.getClient(this);
        CredentialRequest request = new CredentialRequest.Builder()
                .setPasswordLoginSupported(true)
                .setAccountTypes(getApplication().getApplicationInfo().name)
                .build();
        credentialsClient.request(request).addOnCompleteListener(
                (result) -> {
                    if (result.isSuccessful()) {
                        Credential credential = result.getResult().getCredential();
                        loginViewModel.setEmail(credential.getId());
                        loginViewModel.setPassword(credential.getPassword());
                        loginViewModel.setSaveCredentials(true);
                        loginViewModel.login();
                    } else
                        onLoginResult(null);
                }
        );
    }

    private void onLoginResult(Result result) {
        Intent intent;
        if (result instanceof Result.Success)
            intent = new Intent(this, MainActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
