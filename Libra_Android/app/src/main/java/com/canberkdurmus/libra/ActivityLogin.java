package com.canberkdurmus.libra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import static com.canberkdurmus.libra.ApiClient.getSuccess;
import static com.canberkdurmus.libra.ApiClient.getUserId;

public class ActivityLogin extends AppCompatActivity {

    private EditText usernameView;
    private EditText passwordView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        usernameView = findViewById(R.id.login_username);
        passwordView = findViewById(R.id.login_password);
    }

    String url = "http://ahmetcankucukkor.com/libra/user/login";
    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if (getSuccess(response)) {
                onLoginSuccess(getUserId(response));
            } else {
                onLoginFailed();
            }
            //This code is executed if the server responds, whether or not the response contains data.
            //The String 'response' contains the server's response.
        }
    }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println(error.toString());
            //This code is executed if there is an error.
            onLoginFailed();
        }
    }) {
        protected Map<String, String> getParams() {
            Map<String, String> MyData = new HashMap<String, String>();
            MyData.put("username", usernameView.getText().toString()); //Add the data you'd like to send to the server.
            MyData.put("password", passwordView.getText().toString());
            return MyData;
        }
    };

    public void login(View v) {

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        MyRequestQueue.add(MyStringRequest);
    }

    public void goSignup(View v) {

        Intent myIntent = new Intent(ActivityLogin.this, ActivitySignup.class);
        ActivityLogin.this.startActivity(myIntent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    public void onLoginSuccess(int user_id) {
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", user_id);
        editor.putString("username", usernameView.getText().toString());
        editor.putString("password", passwordView.getText().toString());
        System.out.println("Login: " + user_id);
        editor.apply();

        Intent myIntent = new Intent(ActivityLogin.this, MainActivity.class);
        myIntent.putExtra("user_id", user_id); //Optional parameters
        ActivityLogin.this.startActivity(myIntent);
    }

    public void onLoginFailed() {
        SharedPreferences sharedPref = ActivityLogin.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", -1);
        editor.apply();
        Toast.makeText(ActivityLogin.this, "Login failed.", Toast.LENGTH_LONG).show();
    }

}
