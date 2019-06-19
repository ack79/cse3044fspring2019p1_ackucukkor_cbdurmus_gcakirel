package com.canberkdurmus.libra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.canberkdurmus.libra.ApiClient.getSuccess;
import static com.canberkdurmus.libra.ApiClient.getUserId;

public class ActivitySignup extends AppCompatActivity {
    private EditText usernameView;
    private EditText passwordView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        usernameView = findViewById(R.id.input_username);
        passwordView = findViewById(R.id.input_password);
    }

    String url = "http://ahmetcankucukkor.com/libra/user/register";
    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if (getSuccess(response)) {
                onSignupSuccess(getUserId(response));
            } else {
                onSignupFailed();
            }
            //This code is executed if the server responds, whether or not the response contains data.
            //The String 'response' contains the server's response.
        }
    }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
        @Override
        public void onErrorResponse(VolleyError error) {
            System.out.println(error.toString());
            //This code is executed if there is an error.
            onSignupFailed();
        }
    }) {
        protected Map<String, String> getParams() {
            Map<String, String> MyData = new HashMap<String, String>();
            MyData.put("username", usernameView.getText().toString()); //Add the data you'd like to send to the server.
            MyData.put("password", passwordView.getText().toString());
            return MyData;
        }
    };

    public void signup(View v) {
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        MyRequestQueue.add(MyStringRequest);
    }

    public void goLogin(View v) {
        Intent myIntent = new Intent(ActivitySignup.this, ActivityLogin.class);
        ActivitySignup.this.startActivity(myIntent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    public void onSignupSuccess(int user_id) {
        Context context = this;
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", user_id);
        editor.putString("username", usernameView.getText().toString());
        editor.putString("password", passwordView.getText().toString());
        System.out.println("Signup: " + user_id);
        editor.apply();
        Toast.makeText(ActivitySignup.this, "Sign up successful.", Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(ActivitySignup.this, MainActivity.class);
        myIntent.putExtra("user_id", user_id); //Optional parameters
        ActivitySignup.this.startActivity(myIntent);
    }

    public void onSignupFailed() {
        SharedPreferences sharedPref = ActivitySignup.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("user_id", -1);
        editor.apply();
        Toast.makeText(ActivitySignup.this, "Sign up failed.", Toast.LENGTH_SHORT).show();
    }

}

