package com.example.week4homework1okhttp.model.datasource.remote.okhttp3;

import android.util.Log;

import com.example.week4homework1okhttp.model.gitrepos.GitReposResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpHelperForRepositories {


    private static OkHttpClient getClient() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    public static List<GitReposResponse> getSyncroniousOkHttpResponse(String reposUrl) throws Exception{

        Request request = new Request.Builder().url(reposUrl).build();
        Response response = getClient().newCall(request).execute();
        //return new Gson().fromJson(response.body().string(), GitReposResponse.class);

        //List<GitReposResponse> gitReposResponses = new Gson().fromJson(response.body().string(), GitReposResponse.class);

        Type listType = new TypeToken<ArrayList<GitReposResponse>>(){}.getType();
        List<GitReposResponse> gitReposResponseList = new Gson().fromJson(response.body().string(), listType);

        return gitReposResponseList;
    }

//    public static void getAsyncroniousOkHttpResponce(final OkHttpResponseCallback okHttpResponseCallback) {
//        Request request = new Request.Builder().url(GIT_USER_URL).build();
//        getClient().newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.e("TAG", "OKHTTP3 HAS AN ERROR", e);
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                String jsonString = response.body().string();
//                okHttpResponseCallback.gitUserResponseFromOkHttp(
//                        new Gson().fromJson(jsonString, GitUserResponse.class)
//                );
//            }
//        });
//    }
}
