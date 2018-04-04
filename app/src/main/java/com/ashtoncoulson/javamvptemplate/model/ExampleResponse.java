package com.ashtoncoulson.javamvptemplate.model;

import com.google.gson.annotations.SerializedName;

public class ExampleResponse {

    @SerializedName("response")
    private String response;

    public ExampleResponse(String response) {
        this.response = response;
    }
}
