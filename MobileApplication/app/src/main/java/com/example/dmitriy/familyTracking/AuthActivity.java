package com.example.dmitriy.familyTracking;

import android.accounts.Account;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dmitriy.familyTracking.accountManagement.AccountCredentials;
import com.example.dmitriy.familyTracking.restService.RestService;
import com.example.dmitriy.familyTracking.restService.RestServiceController;

import org.w3c.dom.Text;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    Button loginButton;
    EditText usernameEdit;
    EditText passwordEdit;
    EditText serverIpEdit;
    TextView messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        new AccountCredentials("","","");

        loginButton = findViewById(R.id.loginButton);
        usernameEdit = findViewById(R.id.usernameEdit);
        passwordEdit = findViewById(R.id.passwordEdit);
        messageText = findViewById(R.id.messageText);
        serverIpEdit = findViewById(R.id.serverIpText);
        loginButton.setOnClickListener(this);
        loadAccountCredentials();
        initCredentials();
        loadServerUrl();
        serverIpEdit.addTextChangedListener(this);
        new RestServiceController();
    }

    public void initCredentials(){
        usernameEdit.setText(AccountCredentials.instance.getUsername());
        passwordEdit.setText(AccountCredentials.instance.getPassword());
    }

    boolean saveServerUrl(){
        boolean serverUrlSaved;
        String serverUrl = RestService.API_BASE_URL;
        SharedPreferences sharedPreferences = getPreferences(0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString("serverUrl", serverUrl);
        sharedPreferencesEditor.commit();
        serverUrlSaved = true;
        return serverUrlSaved;
    }

    boolean loadServerUrl(){
        boolean serverUrlLoaded;
        SharedPreferences sharedPreferences = getPreferences(0);
        RestService.API_BASE_URL = sharedPreferences.getString("serverUrl", "https://192.168.0.?:8443/");
        serverIpEdit.setText(RestService.API_BASE_URL);
        serverUrlLoaded = true;
        return serverUrlLoaded;
    }

    boolean saveAccountCredentials(){
        boolean credentialsSaved = false;
        String username = AccountCredentials.instance.getUsername();
        String password = AccountCredentials.instance.getPassword();
        String id = AccountCredentials.instance.getId();
        SharedPreferences sharedPreferences = getPreferences(0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        sharedPreferencesEditor.putString("username", username);
        sharedPreferencesEditor.putString("password", password);
        sharedPreferencesEditor.putString("id", id);
        if (!username.isEmpty() && !password.isEmpty() && !id.isEmpty()) {
            sharedPreferencesEditor.commit();
            credentialsSaved = true;
        }
        return credentialsSaved;
    }

    boolean loadAccountCredentials (){
        boolean credentialsLoaded = false;
        String username = "";
        String password = "";
        String id = "";
        SharedPreferences sharedPreferences = getPreferences(0);
        username = sharedPreferences.getString("username", "");
        password = sharedPreferences.getString("password", "");
        id = sharedPreferences.getString("id", "");
        if (!username.isEmpty() && !password.isEmpty() && !id.isEmpty()) {
            AccountCredentials accountCredentials = new AccountCredentials(username, password, id);
            credentialsLoaded = true;
        }
        return credentialsLoaded;
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.loginButton:
                loginRequest();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        RestService.API_BASE_URL = s.toString();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    class WaitForLoginResponse extends AsyncTask<Void, Void, Boolean> {
        Boolean task() {
            while(!RestServiceController.instance.loginResponseReceived) {

            }
            return RestServiceController.instance.loginAccess;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return task();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            loginResponse(result);
        }
    }


    public void loginRequest(){
        messageText.setText("");
        if(usernameEdit.getText().toString().isEmpty() || passwordEdit.getText().toString().isEmpty()){
            messageText.setText("введите все учётные данные");
        }
        else {
            new RestServiceController();
            messageText.setText("ожидается ответ сервера...");
            loginButton.setEnabled(false);
            AccountCredentials.setUsername(usernameEdit.getText().toString());
            AccountCredentials.setPassword(passwordEdit.getText().toString());
            AccountCredentials.setId("id1");
            RestServiceController.login();
            WaitForLoginResponse waitForLoginResponse = new WaitForLoginResponse();
            waitForLoginResponse.execute();
        }
    }

    public void loginResponse(boolean result){
        loginButton.setEnabled(true);
        if (result) {
            saveAccountCredentials();
            saveServerUrl();
            AccountCredentials.setValid(true);
            Intent intent = new Intent(AuthActivity.this, TabsActivity.class);
            startActivity(intent);
        }
        else {
            messageText.setText("не удалось авторизоваться");
            Log.i("Authentication", "Failed");
        }
    }

}
