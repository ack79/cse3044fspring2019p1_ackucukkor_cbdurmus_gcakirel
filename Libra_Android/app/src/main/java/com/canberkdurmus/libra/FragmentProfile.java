package com.canberkdurmus.libra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import java.util.Objects;

import static com.canberkdurmus.libra.ApiClient.getSuccess;

public class FragmentProfile extends BaseFragment {

    private static final String ARG_ID = "ARG_ID";
    private TextView vProfileUsername;
    private TextView vProfileUserBio;
    private TextView vProfileRegDate;
    private Button logout;

    public static FragmentProfile newInstance(String id) {
        FragmentProfile fragment = new FragmentProfile();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_ID, id);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void init(View view) {
        vProfileUsername = view.findViewById(R.id.profileUsername);
        vProfileUserBio = view.findViewById(R.id.profileUserBio);
        vProfileRegDate = view.findViewById(R.id.profileRegDate);
        logout = view.findViewById(R.id.logout);

        String url = "http://ahmetcankucukkor.com/libra/user/login";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (getSuccess(response)) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONObject data = jsonResponse.getJSONObject("data");
                        vProfileUsername.setText("Username: " + data.getString("username"));
                        vProfileUserBio.setText("User Bio: " + data.getString("user_bio"));
                        vProfileRegDate.setText("Registration Date: " + data.getString("reg_date"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
                //This code is executed if there is an error.
                Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                String uname = sharedPref.getString("username", null);
                String pass = sharedPref.getString("password", null);
                Map<String, String> MyData = new HashMap<String, String>();
                assert uname != null;
                MyData.put("username", uname); //Add the data you'd like to send to the server.
                assert pass != null;
                MyData.put("password", pass);
                return MyData;
            }
        };
        RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity());
        MyRequestQueue.add(MyStringRequest);
    }

    @Override
    public void assign(View view) {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getActivity();
                SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("user_id", -1);
                editor.putString("username", null);
                editor.putString("password", null);
                editor.apply();

                Intent myIntent = new Intent(context, ActivityLogin.class);
                context.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_profile;
    }


}

