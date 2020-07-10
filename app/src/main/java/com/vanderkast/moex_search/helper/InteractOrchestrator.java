package com.vanderkast.moex_search.helper;

public interface InteractOrchestrator {
    <I extends Interact> void request(I interact);

    interface Registrar {
        void bind(InteractiveActivity activity);
        void unbind(InteractiveActivity activity);
    }
}
