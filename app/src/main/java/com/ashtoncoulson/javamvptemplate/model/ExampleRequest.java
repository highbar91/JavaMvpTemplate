package com.ashtoncoulson.javamvptemplate.model;

import com.google.gson.annotations.SerializedName;

public class ExampleRequest {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public ExampleRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
