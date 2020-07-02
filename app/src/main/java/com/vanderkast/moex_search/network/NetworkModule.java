package com.vanderkast.moex_search.network;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    Api providesApi() {
        return new Retrofit.Builder()
                .baseUrl("https://passport.moex.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }
}
