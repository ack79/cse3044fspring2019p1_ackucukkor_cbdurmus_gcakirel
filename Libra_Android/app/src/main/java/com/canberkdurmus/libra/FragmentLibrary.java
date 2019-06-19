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

public class FragmentLibrary extends BaseFragment {

    private static final String ARG_ID = "ARG_ID";

    private Button addBook;
    private int user_id;
    GenericRecyclerView<GenericRecyclerItem> recyclerView;


    public static FragmentLibrary newInstance(String id) {
        FragmentLibrary fragment = new FragmentLibrary();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_ID, id);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void init(View view) {
        addBook = view.findViewById(R.id.addBook);

        SharedPreferences sharedPref = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        user_id = sharedPref.getInt("user_id", -1);
        System.out.println("Library: " + user_id);

        recyclerView = view.findViewById(R.id.LibraryRecyclerView);

        recyclerView.init(getActivity())
                .listener((item, position, clickEventCode) -> {
                    ItemLibrary libraryItem = (ItemLibrary) recyclerView.getItems().get(position);
                    Toast.makeText(getActivity(), "Removing " + libraryItem.bookName + " from books.", Toast.LENGTH_SHORT).show();
                    int book_id = libraryItem.bookId;

                    /* REQUEST */
                    String url = "http://ahmetcankucukkor.com/libra/book/removeBook";
                    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //This code is executed if the server responds, whether or not the response contains data.
                            //The String 'response' contains the server's response.
                            if (getSuccess(response)) {
                                Toast.makeText(getActivity(), libraryItem.bookName + " removed.", Toast.LENGTH_SHORT).show();
                                recyclerView.remove(position);
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
                            MyData.put("book_id", "" + book_id); //Add the data you'd like to send to the server.
                            return MyData;
                        }
                    };

                    RequestQueue MyRequestQueue = Volley.newRequestQueue(getActivity());
                    MyRequestQueue.add(MyStringRequest);

                })
                .layout().linear()
                .addView().item(ItemLibrary.class).holder(HolderLibrary.class).layout(R.layout.holder_library).span(1)
                .build();

        /* REQUEST */
        String url = "http://ahmetcankucukkor.com/libra/book/getLibrary";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                if (getSuccess(response)) {
                    JSONArray jsonLibraryArray = getData(response);
                    for (int i = 0; i < (jsonLibraryArray != null ? jsonLibraryArray.length() : 0); i++) {
                        try {
                            JSONObject book = jsonLibraryArray.getJSONObject(i);
                            int book_id = book.getInt("book_id");
                            String book_name = new String(book.getString("book_name").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            String book_author = new String(book.getString("book_author").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            String book_desc = new String(book.getString("book_desc").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            String book_img = new String(book.getString("img").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
                            recyclerView.add(new ItemLibrary(book_id, book_name, book_author, book_desc, book_img));

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

    }

    @Override
    public void assign(View view) {
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), ActivityAddBook.class);
                getActivity().startActivity(myIntent);

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_library;
    }
}

