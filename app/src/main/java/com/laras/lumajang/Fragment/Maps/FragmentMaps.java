package com.laras.lumajang.Fragment.Maps;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.laras.lumajang.Adapter.AppController;
import com.laras.lumajang.Fragment.Home.DataWisata;
import com.laras.lumajang.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaps extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private View view;
    MapFragment mapFragment;
    MarkerOptions markerOptions = new MarkerOptions();
    CameraPosition cameraPosition;
    LatLng center, latLng;
    String title;

    public static final String ID = "id";
    public static final String TITLE = "nama";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    String TAG="volleyyy";
    private static String URL="https://lumajang-tourism.000webhostapp.com/";
    private static String url_select    = URL + "Read.php";

    public FragmentMaps() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        // Add a marker in b29 and move the camera
//        LatLng b29 = new LatLng(-7.959168, 112.994831);
//        mMap.addMarker(new MarkerOptions().position(b29).title("Marker in b29"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(b29));

        callVolley(googleMap);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+String.valueOf(marker.getPosition().latitude)+","+String.valueOf(marker.getPosition().longitude)));
                startActivity(intent);

                return false;
            }
        });
        getMarkers();
    }

    private void getMarkers() {

    }
    private void callVolley(final GoogleMap googleMap) {


        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                mMap = googleMap;

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Log.d(TAG, "onResponse: "+response);
                        //wisataArrayList.add(new DataWisata(obj.getString("nama_lokasi"), obj.getString("gambar"), obj.getString("alamat_lokasi"),obj.getString("deskripsi")));
                        center=new LatLng(obj.getDouble("long"),obj.getDouble("lat"));
                        cameraPosition = new CameraPosition.Builder().target(center).zoom(10).build();
                        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        mMap.addMarker(new MarkerOptions().position(center).title(obj.getString("nama_lokasi")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "onResponse: "+e.toString());
                    }
                }

                // notifikasi adanya perubahan data pada adapter


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

}
