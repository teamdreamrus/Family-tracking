package com.example.dmitriy.familyTracking.restService;

import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.example.dmitriy.familyTracking.AuthActivity;
import com.example.dmitriy.familyTracking.accountManagement.AccountCredentials;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceController {
    public enum Period {
        ONE, HOUR, DAY;
        @Override
        @NonNull
        public String toString(){
            String periodString = super.toString().toLowerCase();
            return periodString;
        }
    }
    public static RestServiceController instance;
    RestServiceConfiguration service;

    public RestServiceController() {
        instance = this;
        service = RestService.createService(RestServiceConfiguration.class, AccountCredentials.getUsername(), AccountCredentials.getPassword());
    }



    public static void addLocation(UserLocation userLocation){
        instance.service =
                RestService.createService(RestServiceConfiguration.class, AccountCredentials.getUsername(), AccountCredentials.getPassword());

        Call<ResponseBody> call = instance.service.addLocation(userLocation);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("POST location", "server access");
                    if (response.body() != null) {
                        Log.d("Response body", response.body().toString());
                    }
                }
                else {
                    Log.i("POST location", "server error");
                    Log.d("Server return code", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("POST location", "client error");
                String message = t.getMessage();
                if (message != null && !message.isEmpty()) {
                    Log.d("Throwable", t.getMessage());
                }
            }
        });
    }

    private List<UserLocation> userLocations = null;
    public boolean userLocationsResponseReceived;
    public static List<UserLocation> getLocations(String userId, Period period){
        Call<List<UserLocation>> call = instance.service.getLocations(userId, period.toString());
        instance.userLocations = null;
        call.enqueue(new Callback<List<UserLocation>>() {
            @Override
            public void onResponse(Call<List<UserLocation>> call, Response<List<UserLocation>> response) {
                if (response.isSuccessful()) {
                    Log.i("GET location", "server access");
                    if (response.body() != null) {
                        Log.d("Response body", response.body().toString());
                        RestServiceController.instance.userLocations = response.body();
                    }
                }
                else {
                    Log.i("GET location", "server error");
                    Log.d("Server return code", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<UserLocation>> call, Throwable t) {
                Log.i("GET location", "client error");
                String message = t.getMessage();
                if (message != null && !message.isEmpty()) {
                    Log.d("Throwable", t.getMessage());
                }
            }
        });
        return instance.userLocations;
    }

    private List<UserData> friends = null;
    public boolean friendsResponseReceived;
    public static List<UserData> getFriends(String userId){
        Call<List<UserData>> call = instance.service.getFriends(userId);
        instance.friends = null;
        call.enqueue(new Callback<List<UserData>>() {
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                if (response.isSuccessful()) {
                    Log.i("POST location", "server access");
                    if (response.body() != null) {
                        Log.d("Response body", response.body().toString());
                        instance.friends = response.body();
                    }
                }
                else {
                    Log.i("POST location", "server error");
                    Log.d("Server return code", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                Log.i("POST location", "client error");
                Log.d("Throwable", t.getMessage());
            }
        });
        return instance.friends;
    }

    public boolean  loginAccess = false;
    public boolean loginResponseReceived;
    public static void login() {
        instance.loginAccess = false;
        instance.loginResponseReceived = false;
        RestServiceConfiguration loginService =
                RestService.createService(RestServiceConfiguration.class,
                        AccountCredentials.getUsername(), AccountCredentials.getPassword());

        Call<ResponseBody> call = loginService.login(AccountCredentials.getUsername(), AccountCredentials.getPassword());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200 || response.code() == 302){
                    instance.loginAccess = true;
                    instance.service =  RestService.createService(RestServiceConfiguration.class,
                            AccountCredentials.getUsername(), AccountCredentials.getPassword());
                }
                Log.d("Response code", String.valueOf(response.code()));
                instance.loginResponseReceived = true;
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("POST login", "client error");
                String message = t.getMessage();
                if (message != null && !message.isEmpty()) {
                    Log.d("Throwable", t.getMessage());
                }
                instance.loginResponseReceived = true;
            }
        });
    }


}