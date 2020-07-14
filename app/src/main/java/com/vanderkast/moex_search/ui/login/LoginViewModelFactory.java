package com.vanderkast.moex_search.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.vanderkast.moex_search.use_case.LoginUseCase;

import javax.inject.Inject;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private final LoginUseCase loginUseCase;

    @Inject
    public LoginViewModelFactory(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.equals(LoginViewModel.class)){
            LoginViewModel viewModel = new LoginViewModel();
            viewModel.useCase = loginUseCase;
            return (T) viewModel;
        }
        return new ViewModelProvider.NewInstanceFactory().create(modelClass);
    }
}
