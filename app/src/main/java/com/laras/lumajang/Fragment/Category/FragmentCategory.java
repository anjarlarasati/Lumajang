package com.laras.lumajang.Fragment.Category;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.laras.lumajang.Adapter.AppController;
import com.laras.lumajang.Fragment.Home.DataWisata;
import com.laras.lumajang.Fragment.Home.Deskripsi;
import com.laras.lumajang.Fragment.Home.WisataAdapter;
import com.laras.lumajang.Fragment.Maps.FragmentMaps;
import com.laras.lumajang.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCategory extends Fragment {
    ImageButton btn_pantai,btn_wisataAlam,btn_KolamRenang,btn_Sejarah,btn_kuliner,btn_hotel;
    private RecyclerView recyclerView;
    private WisataAdapter adapter;
    private ArrayList<DataWisata> wisataArrayList;
    String TAG = "volleyyy";
    private static String URL = "https://lumajang-tourism.000webhostapp.com/";
    private static String url_select = URL + "ReadKat.php";

    public FragmentCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle svedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_category, container, false);

        btn_pantai = (ImageButton) view.findViewById(R.id.btn_pantai);
        btn_wisataAlam =(ImageButton)  view.findViewById(R.id.btn_wisataAlam);
        btn_KolamRenang = (ImageButton) view.findViewById(R.id.btn_kolamRenang);
        btn_Sejarah=(ImageButton)view.findViewById(R.id.btn_sejarah);
        btn_hotel=(ImageButton)view.findViewById(R.id.btn_hotel);
        btn_kuliner=(ImageButton)view.findViewById(R.id.btn_kuliner);
        btn_kuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),KategoriActivity.class);
                intent.putExtra("kategori","kuliner");
                startActivity(intent);
            }
        });
        btn_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),KategoriActivity.class);
                intent.putExtra("kategori","hotel");
                startActivity(intent);
            }
        });
        btn_pantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),KategoriActivity.class);
                intent.putExtra("kategori","pantai");
                startActivity(intent);
            }
        });
        btn_wisataAlam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),KategoriActivity.class);
                intent.putExtra("kategori","wisata alam");
                startActivity(intent);
            }
        });
        btn_KolamRenang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),KategoriActivity.class);
                intent.putExtra("kategori","kolam renang");
                startActivity(intent);
            }
        });
        btn_Sejarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),KategoriActivity.class);
                intent.putExtra("kategori","sejarah");
                startActivity(intent);
            }
        });



        return view;

    }

}

