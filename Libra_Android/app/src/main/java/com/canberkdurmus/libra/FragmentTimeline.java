package com.canberkdurmus.libra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import static com.canberkdurmus.libra.ApiClient.getData;
import static com.canberkdurmus.libra.ApiClient.getSuccess;

public class FragmentTimeline extends BaseFragment {

    private static final String ARG_ID = "ARG_ID";
    private Button postButton;
    private EditText postBox;
    int user_id;
    GenericRecyclerView<GenericRecyclerItem> recyclerView;

    public static FragmentTimeline newInstance(String id) {
        FragmentTimeline fragmentTimeline = new FragmentTimeline();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_ID, id);

        fragmentTimeline.setArguments(bundle);
        return fragmentTimeline;
    }

    @Override
    public void init(View view) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", -1);
        System.out.println("Timeline: " + user_id);
        recyclerView = view.findViewById(R.id.RecyclerView);
        postButton = view.findViewById(R.id.postButton);
        postBox = view.findViewById(R.id.postBox);

        recyclerView.init(getActivity())
                .listener((item, position, clickEventCode) -> {
                    Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
                })
                .layout().linear()
                .addView().item(ItemTimeline.class).holder(HolderTimeline.class).layout(R.layout.holder_timeline).span(1)
                .build();


        /* REQUEST */
        String url = "http://ahmetcankucukkor.com/libra/post/getTimeline";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                if (getSuccess(response)) {
                    JSONArray jsonTimelineArray = getData(response);
                    for (int i = 0; i < (jsonTimelineArray != null ? jsonTimelineArray.length() : 0); i++) {
                        try {
                            JSONObject post = jsonTimelineArray.getJSONObject(i);
                            String username = new String(post.getJSONObject("userInfo").getString("username").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            int userId = post.getJSONObject("userInfo").getInt("user_id");
                            String postText = new String(post.getString("post_text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            int postId = post.getInt("post_id");
                            recyclerView.add(new ItemTimeline(postId, username, userId, postText));

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
                return MyData;
            }
        };

        RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity());
        MyRequestQueue.add(MyStringRequest);

        // Create items and add
        /*for (int i = 0; i < timelineArrayList.size(); i++) {
            recyclerView.add(new ItemTimeline(timelineArrayList.get(i).postId, timelineArrayList.get(i).username, timelineArrayList.get(i).userId, timelineArrayList.get(i).postText));
        }*/
    }

    @Override
    public void assign(View view) {
        postButton.setOnClickListener(view1 -> {
            /* REQUEST */
            String url = "http://ahmetcankucukkor.com/libra/post/addPost";
            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //This code is executed if the server responds, whether or not the response contains data.
                    //The String 'response' contains the server's response.
                    if (getSuccess(response)) {
                        Toast.makeText(getActivity(), "Successful.", Toast.LENGTH_SHORT).show();
                        postBox.setText("");

                        recyclerView.init(getActivity())
                                .listener((item, position, clickEventCode) -> {
                                    Toast.makeText(getActivity(), position + "", Toast.LENGTH_SHORT).show();
                                })
                                .layout().linear()
                                .addView().item(ItemTimeline.class).holder(HolderTimeline.class).layout(R.layout.holder_timeline).span(1)
                                .build();

                        /* REQUEST */
                        String url = "http://ahmetcankucukkor.com/libra/post/getTimeline";
                        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //This code is executed if the server responds, whether or not the response contains data.
                                //The String 'response' contains the server's response.
                                if (getSuccess(response)) {
                                    JSONArray jsonTimelineArray = getData(response);
                                    for (int i = 0; i < (jsonTimelineArray != null ? jsonTimelineArray.length() : 0); i++) {
                                        try {
                                            JSONObject post = jsonTimelineArray.getJSONObject(i);
                                            String username = new String(post.getJSONObject("userInfo").getString("username").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                                            int userId = post.getJSONObject("userInfo").getInt("user_id");
                                            String postText = new String(post.getString("post_text").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                                            int postId = post.getInt("post_id");
                                            recyclerView.add(new ItemTimeline(postId, username, userId, postText));

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
                                return MyData;
                            }
                        };

                        RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity());
                        MyRequestQueue.add(MyStringRequest);
                    } else {
                        Toast.makeText(getActivity(), "Something went wrong.", Toast.LENGTH_SHORT).show();
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
                    MyData.put("post_text", postBox.getText().toString()); //Add the data you'd like to send to the server.
                    return MyData;
                }
            };

            RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity());
            MyRequestQueue.add(MyStringRequest);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_timeline;
    }
}
