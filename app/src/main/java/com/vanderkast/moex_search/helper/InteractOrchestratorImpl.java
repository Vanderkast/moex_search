package com.vanderkast.moex_search.helper;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Singleton
public class InteractOrchestratorImpl implements InteractOrchestrator, InteractOrchestrator.Registrar {
    private InteractiveActivity activity;
    private Queue<Interact> queue;


    @Inject
    public InteractOrchestratorImpl() {
        queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void request(Interact interact) {
        if (activity != null) {
            activity.interact(interact);
            return;
        }
        queue.add(interact);
    }

    @Override
    public void bind(@NonNull InteractiveActivity activity) {
        this.activity = activity;
        Interact i;
        while((i  = queue.poll()) != null)
            activity.interact(i);
    }

    @Override
    public void unbind(InteractiveActivity activity) {
        if (this.activity == activity)
            this.activity = null;
    }
}
