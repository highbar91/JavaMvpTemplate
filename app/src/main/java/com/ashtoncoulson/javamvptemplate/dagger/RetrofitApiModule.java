package com.ashtoncoulson.javamvptemplate.dagger;

import com.ashtoncoulson.javamvptemplate.api.ApiClient;
import com.ashtoncoulson.javamvptemplate.api.ApiService;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ashtoncoulson.javamvptemplate.MainApplication.VERSION_NAME;
import static com.ashtoncoulson.javamvptemplate.MainApplication.isDebuggable;

@Module
public class RetrofitApiModule {

    @Provides
    @Named("OkHttp")
    OkHttpClient getOkHttpClient() {

        Interceptor headers = chain -> {
            Request originalRequest = chain.request();
            Request newRequest = originalRequest.newBuilder()
                    .addHeader("Version", VERSION_NAME)
                    .build();

            return chain.proceed(newRequest);
        };

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(headers);

        if(isDebuggable()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(logging);
        }

        return client.build();
    }

    @Provides
    @Named("Retrofit")
    Retrofit getRetrofit(@Named("OkHttp") OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://example.com/API")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    ApiClient geDriveRestApiClient(@Named("Retrofit") Retrofit retrofit) {
        return retrofit.create(ApiClient.class);
    }

    @Provides
    ApiService getDriveRestApiService(ApiClient driveRestApiClient) {
        return new ApiService(driveRestApiClient);
    }
}
