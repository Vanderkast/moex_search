package com.vanderkast.moex_search.ui.stocks;

import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.vanderkast.moex_search.entity.Stock;
import com.vanderkast.moex_search.use_case.StockUseCases;

import javax.inject.Inject;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

public class StockListViewModel extends ViewModel {
    private final StockUseCases stockUseCases;
    private MutableLiveData<Set<Stock>> stockLiveData;
    private volatile boolean loadStarted;

    @Inject
    public StockListViewModel(StockUseCases stockUseCases) {
        this.stockUseCases = stockUseCases;
        stockLiveData = new MutableLiveData<>();
    }

    public synchronized LiveData<Set<Stock>> getStockLiveData() {
        if(stockLiveData.getValue() == null && !loadStarted) {
            loadStarted = true;
            new Logic(stockUseCases, this.stockLiveData::setValue).execute();
        }
        return stockLiveData;
    }

    private static class Logic extends AsyncTask<Void, Void, Set<Stock>> {
        private final StockUseCases useCases;
        private final Consumer<Set<Stock>> onDone;

        private Logic(StockUseCases useCases, Consumer<Set<Stock>> onDone) {
            this.useCases = useCases;
            this.onDone = onDone;
        }

        @Override
        protected Set<Stock> doInBackground(Void... voids) {
            try {
                return useCases.getAll().get();
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Set<Stock> stockSet) {
            onDone.accept(stockSet);
        }
    }
}
