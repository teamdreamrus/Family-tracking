package com.example.dmitriy.familyTracking.restService;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestServiceConfiguration {
    @POST("api/location")
    Call<ResponseBody> addLocation( @Body UserLocation location);

    @GET("/api/location/{id}")
    Call<List<UserLocation>> getLocations( @Path("id") String id, @Query("period") String period);

    @GET("/api/friends/{id}")
    Call<List<UserData>> getFriends(@Path("id") String id);

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> login( @Field ("username") String username, @Field ("password") String password);


}
