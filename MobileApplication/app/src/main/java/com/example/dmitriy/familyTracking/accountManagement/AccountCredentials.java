package com.example.dmitriy.familyTracking.accountManagement;

import com.google.gson.annotations.SerializedName;

public class AccountCredentials{
    public static AccountCredentials instance;
    private String username;
    private String password;
    private String id;
    private boolean valid;

    public AccountCredentials(String username, String password, String id)
    {
        instance = this;
        instance.username = username;
        instance.password = password;
        instance.id = id;
        instance.valid = false;
    }

    public static void setUsername(String username){
        instance.username = username;
    }

    public static String getUsername(){
        return instance.username;
    }

    public static void setPassword(String password){
        instance.password = password;
    }

    public static String getPassword(){
        return instance.password;
    }

    public static void setId(String id){
        instance.id = id;
    }

    public static String getId(){
        return instance.id;
    }

    public static void setValid(boolean valid){
        instance.valid = valid;
    }

    public static boolean isValid(){
        return instance.valid;
    }
}
