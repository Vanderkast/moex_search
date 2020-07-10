package com.vanderkast.moex_search.di;

import com.vanderkast.moex_search.controller.TokenKeeper;
import com.vanderkast.moex_search.controller.TokenKeeperImpl;
import com.vanderkast.moex_search.helper.InteractOrchestrator;
import com.vanderkast.moex_search.helper.InteractOrchestratorImpl;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class ApplicationModule {
    private InteractOrchestratorImpl orchestrator = new InteractOrchestratorImpl();
    private TokenKeeperImpl tokenKeeper = new TokenKeeperImpl();

    @Provides
    InteractOrchestrator providesInteractOrchestrator() {
        return orchestrator;
    }

    @Provides
    InteractOrchestrator.Registrar providesInteractOrchestratorRegistrar() {
        return orchestrator;
    }

    @Provides
    TokenKeeper providesTokenKeeper() {
        return tokenKeeper;
    }

    @Provides
    TokenKeeper.Provider providesTokenKeeperProvider() {
        return tokenKeeper;
    }

}
