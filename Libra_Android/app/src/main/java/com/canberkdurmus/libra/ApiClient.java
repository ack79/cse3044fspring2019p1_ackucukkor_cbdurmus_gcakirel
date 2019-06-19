package com.canberkdurmus.libra;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiClient {
    public static boolean getSuccess(String response) {
        try {
            JSONObject json_res = new JSONObject(response);
            return json_res.getBoolean("success");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static JSONArray getData(String response) {
        try {
            JSONObject json_res = new JSONObject(response);
            return json_res.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static int getUserId(String response) {
        try {
            JSONObject json_res = new JSONObject(response);
            return json_res.getJSONObject("data").getInt("user_id");
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }

    }
}