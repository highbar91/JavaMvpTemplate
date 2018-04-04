package com.ashtoncoulson.javamvptemplate.api;

import com.ashtoncoulson.javamvptemplate.model.ExampleRequest;
import com.ashtoncoulson.javamvptemplate.model.ExampleResponse;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

public class ApiService {

    private ApiClient apiClient;

    public ApiService(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public Observable<Response<ExampleResponse>> getExampleData(ExampleRequest request) {
        return apiClient.getExampleData(request)
                .doOnError(throwable -> Timber.i(throwable.getMessage()))
                .subscribeOn(Schedulers.io());
    }
}
