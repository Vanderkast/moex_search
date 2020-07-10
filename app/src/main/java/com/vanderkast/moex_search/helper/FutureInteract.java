package com.vanderkast.moex_search.helper;

import androidx.annotation.NonNull;

import java.util.concurrent.*;

public class FutureInteract<T> implements Future<T>, Interact {
    private CountDownLatch latch = new CountDownLatch(1);
    private T t;

    void push(T t) {
        this.t = t;
        latch.countDown();
    }

    @Override
    public T get(long timeout, @NonNull TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        if (latch.await(timeout, timeUnit))
            return t;
        else throw new TimeoutException();
    }

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return latch.getCount() == 0;
    }

    @Override
    public T get() throws ExecutionException, InterruptedException {
        latch.await();
        return t;
    }
}
