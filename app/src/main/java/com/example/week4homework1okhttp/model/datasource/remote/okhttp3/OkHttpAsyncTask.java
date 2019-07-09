//package com.example.week4homework1okhttp.model.datasource.remote.okhttp3;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.example.week4homework1okhttp.model.gituser.GitUserResponse;
//
//public class OkHttpAsyncTask extends AsyncTask<String, String, GitUserResponse> {
//
//
//    @Override
//    protected GitUserResponse doInBackground(String... strings) {
//        try {
//            return OkHttpHelper.getSyncroniousOkHttpResponse();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(GitUserResponse response) {
//        super.onPostExecute(response);
//        //Log.d("TAG_ASYNC", response.getResults().get(0).getEmail());
//        Log.d("TAG_ASYNC", response.getItems().get(0).getLogin());
//    }
//}
