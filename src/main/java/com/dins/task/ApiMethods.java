package com.dins.task;

import okhttp3.*;

import java.io.IOException;

public class ApiMethods {
    private final String URL_USERS = "https://my-json-server.typicode.com/knastya/simpleBillingService/users";
    private final String URL_PRODUCTS = "https://my-json-server.typicode.com/knastya/simpleBillingService/products";
    private final String URL_PURCHASES = "https://my-json-server.typicode.com/knastya/simpleBillingService/purchases";

    private final OkHttpClient httpClient = new OkHttpClient();


    public Response sendPostOfUsers(String firstName, String lastName, String email) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .build();

        Request request = new Request.Builder()
                .url(URL_USERS)
                .post(formBody)
                .build();

       return httpClient.newCall(request).execute();
    }

    public Response sendGetOfPurchases() throws IOException {
        Request request = new Request.Builder()
                .url(URL_PURCHASES)
                .build();

        return httpClient.newCall(request).execute();
    }

    public Response sendGetOfUsers() throws IOException {
        Request request = new Request.Builder()
                .url(URL_USERS)
                .build();

        return httpClient.newCall(request).execute();
    }
}
