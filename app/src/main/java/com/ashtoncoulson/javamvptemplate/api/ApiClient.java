package com.ashtoncoulson.javamvptemplate.api;

import com.ashtoncoulson.javamvptemplate.model.ExampleRequest;
import com.ashtoncoulson.javamvptemplate.model.ExampleResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("/example/path")
    Observable<Response<ExampleResponse>> getExampleData(
            @Body ExampleRequest request
    );
}
