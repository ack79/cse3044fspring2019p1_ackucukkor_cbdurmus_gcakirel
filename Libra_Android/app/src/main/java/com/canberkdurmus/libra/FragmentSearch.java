package com.canberkdurmus.libra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.canozgen.genericrv.GenericRecyclerView;
import com.canozgen.genericrv.items.GenericRecyclerItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.canberkdurmus.libra.ApiClient.getData;
import static com.canberkdurmus.libra.ApiClient.getSuccess;

public class FragmentSearch extends BaseFragment {

    private static final String ARG_ID = "ARG_ID";

    public static FragmentSearch newInstance(String id) {
        FragmentSearch fragmentSearch = new FragmentSearch();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_ID, id);

        fragmentSearch.setArguments(bundle);
        return fragmentSearch;
    }

    private Button searchButton;
    private EditText searchBox;
    private int user_id;
    GenericRecyclerView<GenericRecyclerItem> recyclerView;

    @Override
    public void init(View view) {
        searchButton = view.findViewById(R.id.searchButton);
        searchBox = view.findViewById(R.id.searchBox);

        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", -1);
        System.out.println("Search: " + user_id);

        recyclerView = view.findViewById(R.id.SearchRecyclerView);

        recyclerView.init(getActivity())
                .listener((item, position, clickEventCode) -> {
                    ItemSearch searchItem = (ItemSearch) recyclerView.getItems().get(position);
                    if (searchItem.friend) {
                        Toast.makeText(getActivity(), searchItem.username + " is already your friend.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Adding " + searchItem.username + " as friend.", Toast.LENGTH_SHORT).show();
                        int friend_user_id = searchItem.userId;

                        /* REQUEST */
                        String url = "http://ahmetcankucukkor.com/libra/user/addFriend";
                        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //This code is executed if the server responds, whether or not the response contains data.
                                //The String 'response' contains the server's response.
                                if (getSuccess(response)) {
                                    Toast.makeText(getActivity(), searchItem.username + " added as friend.", Toast.LENGTH_SHORT).show();
                                    searchItem.friend = false;
                                    recyclerView.update(item);
                                }
                            }
                        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println(error.toString());
                                Toast.makeText(getActivity(), "An error occurred.", Toast.LENGTH_LONG).show();
                                //This code is executed if there is an error.
                            }
                        }) {
                            protected Map<String, String> getParams() {
                                Map<String, String> MyData = new HashMap<String, String>();
                                MyData.put("user_id", "" + user_id); //Add the data you'd like to send to the server.
                                MyData.put("friend_user_id", "" + friend_user_id); //Add the data you'd like to send to the server.
                                return MyData;
                            }
                        };

                        RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity());
                        MyRequestQueue.add(MyStringRequest);


                    }
                })
                .layout().linear()
                .addView().item(ItemSearch.class).holder(HolderSearch.class).layout(R.layout.holder_search).span(1)
                .build();

    }

    @Override
    public void assign(View view) {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* REQUEST */
                String url = "http://ahmetcankucukkor.com/libra/user/search";
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                        if (getSuccess(response)) {
                            JSONArray jsonSearchArray = getData(response);
                            for (int i = 0; i < (jsonSearchArray != null ? jsonSearchArray.length() : 0); i++) {
                                try {
                                    JSONObject user = jsonSearchArray.getJSONObject(i);
                                    String username = new String(user.getString("username").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                                    int userId = user.getInt("user_id");
                                    boolean friend = user.getBoolean("isFriend");
                                    String bio = new String(user.getString("user_bio").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                                    recyclerView.add(new ItemSearch(username, userId, bio, friend));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        Toast.makeText(getActivity(), "An error occurred.", Toast.LENGTH_LONG).show();
                        //This code is executed if there is an error.
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("user_id", "" + user_id); //Add the data you'd like to send to the server.
                        MyData.put("searchString", "" + searchBox.getText().toString()); //Add the data you'd like to send to the server.
                        return MyData;
                    }
                };

                RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity());
                MyRequestQueue.add(MyStringRequest);


            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }
}

