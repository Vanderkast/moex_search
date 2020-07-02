package com.vanderkast.moex_search.ui.login;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
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
    private boolean saveCredentials;

    private MutableLiveData<Result> result;

    public LoginViewModel() {
        result = new MutableLiveData<>();
    }

    public void login() {
        result.postValue(useCase.login(new LoginCredentials(email, password, saveCredentials)));
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
