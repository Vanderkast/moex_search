package com.vanderkast.moex_search.di;

import com.vanderkast.moex_search.controller.TokenKeeper;
import com.vanderkast.moex_search.network.LoginApi;
import com.vanderkast.moex_search.network.MainApi;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.io.IOException;

@Module(includes = ApplicationModule.class)
@InstallIn(ApplicationComponent.class)
public class NetworkModule {
    public static final String LOGIN_URL = "https://passport.moex.com/";
    public static final String BASE_URL = "https://iss.moex.com/";

    @Provides
    LoginApi provideApi() {
        return new Retrofit.Builder()
                .baseUrl(LOGIN_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(LoginApi.class);
    }

    @Provides
    MainApi providesMainApi(TokenKeeper keeper) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new TokenInterceptor(keeper))
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(client)
                .build()
                .create(MainApi.class);
    }

    static class TokenInterceptor implements Interceptor {
        private final TokenKeeper keeper;

        TokenInterceptor(TokenKeeper keeper) {
            this.keeper = keeper;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .header("Cookie", "MicexPassportCert=" + keeper.get()).build();
            return chain.proceed(request);
        }
    }
}
