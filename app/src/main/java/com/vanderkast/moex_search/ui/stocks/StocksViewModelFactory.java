package com.vanderkast.moex_search.ui.stocks;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.vanderkast.moex_search.ui.login.LoginViewModel;
import com.vanderkast.moex_search.use_case.StockUseCases;

import javax.inject.Inject;

public class StocksViewModelFactory implements ViewModelProvider.Factory {
    private final StockUseCases stockUseCases;

    @Inject
    public StocksViewModelFactory(StockUseCases stockUseCases) {
        this.stockUseCases = stockUseCases;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.equals(StockListViewModel.class)){
            StockListViewModel viewModel = new StockListViewModel(stockUseCases);
            return (T) viewModel;
        }
        return new ViewModelProvider.NewInstanceFactory().create(modelClass);
    }
}
