package com.vanderkast.moex_search.di;

import com.vanderkast.moex_search.controller.TokenKeeper;
import com.vanderkast.moex_search.network.LoginApi;
import com.vanderkast.moex_search.network.MainApi;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.inject.Inject;
import java.io.IOException;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    LoginApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl("https://passport.moex.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(LoginApi.class);
    }

    @Provides
    MainApi providesMainApi() {
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new TokenInterceptor()).build();

        return new Retrofit.Builder().baseUrl("https://passport.moex.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(MainApi.class);
    }

    static class TokenInterceptor implements Interceptor {
        @Inject
        TokenKeeper keeper;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .header("Cookie", "MicexPassportCert=" + keeper.get()).build();
            return chain.proceed(request);
        }
    }
}
