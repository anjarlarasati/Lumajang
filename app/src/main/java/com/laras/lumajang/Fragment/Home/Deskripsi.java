package com.laras.lumajang.Fragment.Home;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.laras.lumajang.Adapter.AppController;
import com.laras.lumajang.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import com.laras.lumajang.Fragment.Category.KategoriActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Deskripsi extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    MarkerOptions markerOptions = new MarkerOptions();
    CameraPosition cameraPosition;
    LatLng center, latLng;
    private WisataAdapter adapter ;
    TextView txtNama,txtAlamat,txtDeskripsi;
    ImageView img;
    MapView maps;
    Button lihat,navigasi;
    String TAG="volleyyy";
    private static String URL="https://lumajang-tourism.000webhostapp.com/";
    private static String url_select    = URL + "Read.php";
    private ArrayList<DataWisata> wisataArrayList ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);


        adapter = new WisataAdapter(wisataArrayList);

        txtNama = (TextView) findViewById(R.id.txt_nama);
        img = (ImageView) findViewById(R.id.img_wisata);
        txtAlamat = (TextView) findViewById(R.id.txt_lokasi);
        txtDeskripsi = (TextView) findViewById(R.id.textDeskripsi);
//        maps = (MapView) findViewById(R.id.mapView);
        lihat=findViewById(R.id.btn_lihat);
        navigasi=findViewById(R.id.btn_navigasi);
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q=loc:"+getIntent().getStringExtra("long")+","+getIntent().getStringExtra("lat")));
                startActivity(intent);
            }
        });

        navigasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+getIntent().getStringExtra("long")+","+getIntent().getStringExtra("lat")));
                startActivity(intent);

            }
        });

        txtNama.setText(getIntent().getStringExtra("nama_lokasi"));
        txtAlamat.setText(getIntent().getStringExtra("alamat_lokasi"));
        txtDeskripsi.setText(getIntent().getStringExtra("deskripsi"));

        Picasso.get()
                .load(getIntent().getStringExtra("gambar"))
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(img);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
//        maps.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d(TAG, "onMapReady: ");

        Log.d(TAG, "onMapReady: "+Double.parseDouble(getIntent().getStringExtra("lat")));
        Log.d(TAG, "onMapReady: "+Double.parseDouble(getIntent().getStringExtra("long")));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(getIntent().getStringExtra("long")), Double.parseDouble(getIntent().getStringExtra("lat")));

        mMap.addMarker(new MarkerOptions().position(sydney).title(getIntent().getStringExtra("nama_lokasi")));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        cameraPosition = new CameraPosition.Builder().target(sydney).zoom(100).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

}
