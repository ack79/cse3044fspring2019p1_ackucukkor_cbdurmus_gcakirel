package com.canberkdurmus.libra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.canberkdurmus.libra.ApiClient.getSuccess;
import static com.canberkdurmus.libra.ApiClient.getUserId;

public class ActivityAddBook extends AppCompatActivity {

    private EditText bookname;
    private EditText author;
    private EditText description;
    private int user_id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        bookname = findViewById(R.id.bookname);
        author = findViewById(R.id.author);
        description = findViewById(R.id.description);
        SharedPreferences sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", -1);
    }

    public void addBook(View v) {

        String url = "http://ahmetcankucukkor.com/libra/book/addBook";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        Toast.makeText(ActivityAddBook.this, "Successful.", Toast.LENGTH_SHORT).show();
                        bookname.setText("");
                        author.setText("");
                        description.setText("");
                    } else {
                        Toast.makeText(ActivityAddBook.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
                Toast.makeText(ActivityAddBook.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("user_id", "" + user_id); //Add the data you'd like to send to the server.
                MyData.put("book_name", bookname.getText().toString()); //Add the data you'd like to send to the server.
                MyData.put("book_author", author.getText().toString()); //Add the data you'd like to send to the server.
                MyData.put("book_desc", description.getText().toString()); //Add the data you'd like to send to the server.
                MyData.put("img", ""); //Add the data you'd like to send to the server.
                return MyData;
            }
        };

        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        MyRequestQueue.add(MyStringRequest);
    }
}
