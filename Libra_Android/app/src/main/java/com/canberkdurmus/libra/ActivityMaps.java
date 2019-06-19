package com.canberkdurmus.libra;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActivityMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng kadikoy = new LatLng(40.97, 29.07);
        LatLng uskudar = new LatLng(41.02, 29.03);
        LatLng besiktas = new LatLng(41.04, 29.00);
        mMap.addMarker(new MarkerOptions().position(kadikoy).title("Kadıköy'de kitap."));
        mMap.addMarker(new MarkerOptions().position(uskudar).title("Üsküdar'da kitap."));
        mMap.addMarker(new MarkerOptions().position(besiktas).title("Beşiktaş'ta kitap."));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kadikoy));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(kadikoy, 12.0f));
    }
}
