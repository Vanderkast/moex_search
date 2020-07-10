package com.vanderkast.moex_search.gateway;

import com.vanderkast.moex_search.controller.LoginController;
import com.vanderkast.moex_search.helper.InteractOrchestrator;
import com.vanderkast.moex_search.helper.SaveCredentialsInteract;

import javax.inject.Inject;
import java.util.concurrent.ExecutionException;

public class SaveCredentialsGatewayImpl implements LoginController.SaveCredentialsGateway {
    private final InteractOrchestrator orchestrator;

    @Inject
    public SaveCredentialsGatewayImpl(InteractOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @Override
    public void save(String email, String password) {
        SaveCredentialsInteract interact = new SaveCredentialsInteract(email, password);
        orchestrator.request(interact);
        try {
            interact.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
