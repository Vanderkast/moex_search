package com.vanderkast.moex_search.di;

import com.vanderkast.moex_search.controller.LoginController;
import com.vanderkast.moex_search.gateway.SaveCredentialsGatewayImpl;
import com.vanderkast.moex_search.helper.InteractOrchestrator;
import com.vanderkast.moex_search.helper.InteractOrchestratorImpl;
import com.vanderkast.moex_search.use_cases.LoginUseCase;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ActivityRetainedComponent;

@Module
@InstallIn({ActivityComponent.class, ActivityRetainedComponent.class})
public abstract class LoginModule {
    @Binds
    public abstract LoginUseCase bind(LoginController implementation);

    @Binds
    public abstract LoginController.SaveCredentialsGateway bindLoginControllerSaveCredentialsGateway
            (SaveCredentialsGatewayImpl implementation);
}
