package com.canberkdurmus.libra;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

public class FragmentAdvert extends BaseFragment {

    private static final String ARG_ID = "ARG_ID";
    private Button mapButton;

    public static FragmentAdvert newInstance(String id) {
        FragmentAdvert fragment = new FragmentAdvert();

        Bundle bundle = new Bundle();
        bundle.putString(ARG_ID, id);

        fragment.setArguments(bundle);
        return fragment;
    }

    //private TextView textView;

    @Override
    public void init(View view) {
        // textView = view.findViewById(R.id.textView);
        mapButton = view.findViewById(R.id.mapButton);
    }

    @Override
    public void assign(View view) {
        if (getArguments() != null) {
            //textView.setText(getArguments().getString(ARG_ID, "Selam"));
        }


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), ActivityMaps.class);
                //myIntent.putExtra("user_id", user_id); //Optional parameters
                getActivity().startActivity(myIntent);

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_advert;
    }

}

