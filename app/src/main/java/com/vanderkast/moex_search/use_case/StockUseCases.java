package com.vanderkast.moex_search.use_case;

import androidx.annotation.NonNull;
import com.vanderkast.moex_search.entity.Stock;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public abstract class StockUseCases {
    private final CompletableFuture<Set<Stock>> loadingFuture;
    private boolean loadingStarted;

    public StockUseCases() {
        loadingFuture = new CompletableFuture<>();
    }

    Future<Set<Stock>> getAll() {
        if(!loadingStarted) {
            loadAll(loadingFuture::complete);
            loadingStarted = true;
        }
        return loadingFuture;
    }

    protected abstract void loadAll(Consumer<Set<Stock>> onDone);
}
