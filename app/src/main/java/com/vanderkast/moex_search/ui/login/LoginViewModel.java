package com.vanderkast.moex_search.ui.login;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.vanderkast.moex_search.model.LoginCredentials;
import com.vanderkast.moex_search.model.Result;
import com.vanderkast.moex_search.use_cases.LoginUseCase;

/**
 * ViewModel injection problem:
 * Hilt provides ViewModel constructor injection by using @ViewModelInject and @Assisted,
 * but it's not working.
 */
public class LoginViewModel extends ViewModel {
    LoginUseCase useCase;

    private String email;
    private String password;
    private boolean saveCredentials = true;

    private MutableLiveData<Result> result;

    public LoginViewModel() {
        result = new MutableLiveData<>();
    }

    public void login() {
        new LoginTask(result, useCase).execute(new LoginCredentials(email, password, saveCredentials));
    }

    private static class LoginTask extends AsyncTask<LoginCredentials, Void, Void> {
        private final MutableLiveData<Result> liveData;
        private final LoginUseCase loginUseCase;
        private Result result;

        private LoginTask(MutableLiveData<Result> liveData, LoginUseCase loginUseCase) {
            this.liveData = liveData;
            this.loginUseCase = loginUseCase;
        }


        @Override
        protected Void doInBackground(LoginCredentials... loginCredentials) {
            result = loginUseCase.login(loginCredentials[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            liveData.postValue(result);
        }
    }

    public LiveData<Result> getResult() {
        return result;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSaveCredentials(boolean saveCredentials) {
        this.saveCredentials = saveCredentials;
    }
}
