package com.example.week4homework1okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week4homework1okhttp.model.datasource.remote.httpurlconnection.HttpUrlConnectionHelper;
import com.example.week4homework1okhttp.model.datasource.remote.okhttp3.OkHttpHelper;
import com.example.week4homework1okhttp.model.datasource.remote.okhttp3.OkHttpResponseCallback;
import com.example.week4homework1okhttp.model.gituser.GitUserResponse;

public class MainActivity extends AppCompatActivity implements OkHttpResponseCallback {
    private ImageView ivAvatar;
    private TextView tvLoginId, tvId, tvHtmlUrl, tvType, tvUserScore;
    private String reposUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        OkHttpHelper.getAsyncroniousOkHttpResponce(this);
//        OkHttpAsyncTask okHttpAsyncTask = new OkHttpAsyncTask();
//        okHttpAsyncTask.execute();

        ivAvatar = findViewById(R.id.ivAvatar);
        tvLoginId = findViewById(R.id.tvLoginId);
        tvId = findViewById(R.id.tvId);
        tvHtmlUrl = findViewById(R.id.tvHtmlUrl);
        tvType = findViewById(R.id.tvType);
        tvUserScore = findViewById(R.id.tvUserScore);

        getAsyncTaskResponse();
    }

    private void getAsyncTaskResponse() {

        class OkHttpAsyncTask extends AsyncTask<String, String, GitUserResponse> {


            @Override
            protected GitUserResponse doInBackground(String... strings) {
                try {
                    return OkHttpHelper.getSyncroniousOkHttpResponse();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(GitUserResponse response) {
                super.onPostExecute(response);
                //Log.d("TAG_ASYNC2", String.valueOf(response.getItems().get(0).getId()));
                //Log.d("TAG_ASYNC2", response.getItems().get(0).getLogin());

                final String avatarUrl = response.getItems().get(0).getAvatarUrl();
                final String loginId = response.getItems().get(0).getLogin();
                final String id = String.valueOf(response.getItems().get(0).getId());
                final String htmlUrl = response.getItems().get(0).getHtmlUrl();
                final String type = response.getItems().get(0).getType();
                final String score = String.valueOf(response.getItems().get(0).getScore());

                //Get repository URL
                reposUrl = response.getItems().get(0).getReposUrl();

                Glide.with(getApplicationContext()).load(avatarUrl).into(ivAvatar);
                tvLoginId.setText(loginId);
                tvId.setText(id);
                tvHtmlUrl.setText(htmlUrl);
                tvType.setText(type);
                tvUserScore.setText(score);
            }
        }

        OkHttpAsyncTask okHttpAsyncTask = new OkHttpAsyncTask();
        okHttpAsyncTask.execute();
    }

    @Override
    public void gitUserResponseFromOkHttp(GitUserResponse response) {
        Log.d("TAG_ASYNC2", "gitUserResponseFromOkHttp: " + response.getItems().get(0).getLogin());
        Log.d("TAG_ASYNC2", "gitUserResponseFromOkHttp: " + response.getItems().get(0).getId());
        final String s = response.getItems().get(0).getUrl();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //tvEx.setText(s);
            }
        });

    }

    public void onClick(View view) {
        Intent reposIntent = new Intent(this, RepositoriesListActivity.class);
        reposIntent.putExtra("reposUrl", reposUrl);
        startActivity(reposIntent);
    }
}
