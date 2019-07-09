package com.example.week4homework1okhttp.model.datasource.remote.okhttp3;

import com.example.week4homework1okhttp.model.gituser.GitUserResponse;

public interface OkHttpResponseCallback {
    void gitUserResponseFromOkHttp(GitUserResponse response);
}
