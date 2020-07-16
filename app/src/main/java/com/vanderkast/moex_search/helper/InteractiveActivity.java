package com.vanderkast.moex_search.helper;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credentials;
import com.google.android.gms.auth.api.credentials.CredentialsClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.vanderkast.moex_search.R;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

public abstract class InteractiveActivity extends AppCompatActivity {
    public static final int SAVE_CREDENTIALS_REQUEST_CODE = 1488;

    @Inject
    InteractOrchestrator.Registrar registrar;

    private final SparseArray<Interact> interacts = new SparseArray<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    void interact(Interact interact) {
        if (interact instanceof SaveCredentialsInteract) {
            this.interacts.put(SAVE_CREDENTIALS_REQUEST_CODE, interact);
            saveCredentials((SaveCredentialsInteract) interact);
        }
    }

    private void saveCredentials(SaveCredentialsInteract interact){
        Credential credentials = new Credential.Builder(interact.getEmail())
                .setPassword(interact.getPassword())
                .build();
        CredentialsClient client = Credentials.getClient(this);
        client.save(credentials).addOnCompleteListener((result) -> {
            if (result.isSuccessful()) {
                interact.push(null);
                return;
            }
            Exception e = result.getException();
            if (e instanceof ResolvableApiException) {
                ResolvableApiException rae = (ResolvableApiException) result.getException();
                try {
                    rae.startResolutionForResult(this, SAVE_CREDENTIALS_REQUEST_CODE);
                } catch (IntentSender.SendIntentException ex) {
                    Toast.makeText(this, getString(R.string.cant_save_login), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            } else
                Toast.makeText(this, getString(R.string.cant_save_login), Toast.LENGTH_SHORT).show();
            interact.push(null);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SAVE_CREDENTIALS_REQUEST_CODE: {
                SaveCredentialsInteract interact = (SaveCredentialsInteract) interacts.get(SAVE_CREDENTIALS_REQUEST_CODE);
                interact.push(null);
                interacts.remove(SAVE_CREDENTIALS_REQUEST_CODE);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registrar.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        registrar.unbind(this);
    }
}
