package com.example.week4homework1okhttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.week4homework1okhttp.model.datasource.remote.okhttp3.OkHttpHelperForRepositories;
import com.example.week4homework1okhttp.model.gitrepos.GitReposResponse;

import java.util.ArrayList;
import java.util.List;

public class RepositoriesListActivity extends AppCompatActivity {

    RecyclerView rvRepositories;
    private String reposUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories_list);

        rvRepositories = findViewById(R.id.rvRepositories);
        rvRepositories.setLayoutManager(new LinearLayoutManager(this));

        Intent receivedIntent = getIntent();
        reposUrl = receivedIntent.getStringExtra("reposUrl");

        getRepositories();
    }

    private void getRepositories() {

        class OkHttpAsyncTaskForRepositories extends AsyncTask<String, String, List<GitReposResponse>> {


            @Override
            protected List<GitReposResponse> doInBackground(String... strings) {
                List<GitReposResponse> gitReposResponses = new ArrayList<>();

                try {
                    gitReposResponses = OkHttpHelperForRepositories.getSyncroniousOkHttpResponse(reposUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return gitReposResponses;
            }

            @Override
            protected void onPostExecute(List<GitReposResponse> responses) {
                super.onPostExecute(responses);

                RepositoriesListAdapter repositoriesListAdapter = new RepositoriesListAdapter(responses);
                rvRepositories.setAdapter(repositoriesListAdapter);
            }
        }

        OkHttpAsyncTaskForRepositories okHttpAsyncTaskForRepositories = new OkHttpAsyncTaskForRepositories();
        okHttpAsyncTaskForRepositories.execute();
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnHome:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
