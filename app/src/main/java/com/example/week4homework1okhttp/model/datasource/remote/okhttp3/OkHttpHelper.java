package com.example.week4homework1okhttp.model.datasource.remote.okhttp3;

import android.util.Log;

import com.example.week4homework1okhttp.model.gituser.GitUserResponse;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpHelper {
    public static final String GIT_USER_URL = "https://api.github.com/search/users?q=ygunawan1985";

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static GitUserResponse getSyncroniousOkHttpResponse() throws Exception{
        Request request = new Request.Builder().url(GIT_USER_URL).build();
        Response response = getClient().newCall(request).execute();
        return new Gson().fromJson(response.body().string(), GitUserResponse.class);
    }

    public static void getAsyncroniousOkHttpResponce(final OkHttpResponseCallback okHttpResponseCallback) {
        Request request = new Request.Builder().url(GIT_USER_URL).build();
        getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("TAG", "OKHTTP3 HAS AN ERROR", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String jsonString = response.body().string();
                okHttpResponseCallback.gitUserResponseFromOkHttp(
                        new Gson().fromJson(jsonString, GitUserResponse.class)
                );
            }
        });
    }
}
