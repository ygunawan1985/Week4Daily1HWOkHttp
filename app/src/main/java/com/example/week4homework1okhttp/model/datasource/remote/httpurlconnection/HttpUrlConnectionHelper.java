package com.example.week4homework1okhttp.model.datasource.remote.httpurlconnection;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUrlConnectionHelper {
    public static final String GIT_USER_URL = "https://api.github.com/search/users?q=ygunawan1985";
    private static URL urlObject;
    private static HttpURLConnection httpURLConnection;
    public static final int EOF = -1;

    public static String getGitUserResponse() throws MalformedURLException, Exception {
        String resultString = "";

        //Open the connection
        urlObject = new URL(GIT_USER_URL);
        httpURLConnection = (HttpURLConnection)urlObject.openConnection();

        //Buffer the stream
        InputStream inputStream = httpURLConnection.getInputStream();
        int getIntValueOfCurrentRead = inputStream.read();
        while(getIntValueOfCurrentRead != EOF){
            char convertedIntToChar = (char) getIntValueOfCurrentRead;
            resultString = resultString + convertedIntToChar;
            getIntValueOfCurrentRead = inputStream.read();
        }

        return resultString;
    }
}
