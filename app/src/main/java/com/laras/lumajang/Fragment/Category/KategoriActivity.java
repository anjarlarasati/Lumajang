package com.laras.lumajang.Fragment.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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
import com.google.android.gms.maps.model.MarkerOptions;
import com.laras.lumajang.Adapter.AppController;
import com.laras.lumajang.Fragment.Home.DataWisata;
import com.laras.lumajang.Fragment.Home.Deskripsi;
import com.laras.lumajang.Fragment.Home.RecyclerItemClickListener;
import com.laras.lumajang.Fragment.Home.WisataAdapter;
import com.laras.lumajang.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class KategoriActivity extends AppCompatActivity  {
    private GoogleMap mMap;
    private View view;
    MapFragment mapFragment;
    MarkerOptions markerOptions = new MarkerOptions();
    CameraPosition cameraPosition;
    LatLng center, latLng;
    String title;


    private RecyclerView recyclerView ;
    private WisataAdapter adapter ;
    private ArrayList<DataWisata> wisataArrayList ;
    String TAG="volleyyy";
    private static String URL="https://lumajang-tourism.000webhostapp.com/";
    private static String url_select    = URL + "ReadKat.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment_pantai);

        callVolley();
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        adapter = new WisataAdapter(wisataArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(KategoriActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(KategoriActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(KategoriActivity.this , Deskripsi.class);
                intent.putExtra("nama_lokasi", wisataArrayList.get(position).getNama());
                intent.putExtra("alamat_lokasi", wisataArrayList.get(position).getAlamat());
                intent.putExtra("gambar", wisataArrayList.get(position).getGambar());
                intent.putExtra("deskripsi", wisataArrayList.get(position).getDeskripsi());
                intent.putExtra("lat", String.valueOf(wisataArrayList.get(position).getLat()));
                intent.putExtra("long", String.valueOf(wisataArrayList.get(position).getLong()));
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }
    private void callVolley() {
        wisataArrayList = new ArrayList<>();

        JsonArrayRequest jArr = new JsonArrayRequest(url_select+"?kategori="+getIntent().getStringExtra("kategori"), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);


                        Log.d(TAG, "onResponse: "+response);
                        wisataArrayList.add(new DataWisata(obj.getString("nama_lokasi"), obj.getString("gambar"), obj.getString("alamat_lokasi"),obj.getString("deskripsi"),obj.getDouble("lat"),obj.getDouble("long")));


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d(TAG, "onResponse: "+e.toString());
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                adapter.notifyDataSetChanged();

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
