package com.unpam.anisa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailVideoActivity extends AppCompatActivity {

    ImageView img;
    TextView judul, judul2, waktu, isi;
    String jud, jud2, wak, is, im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        Bundle extras = getIntent().getExtras();
        jud = extras.getString("judul");
        jud2 = extras.getString("judul2");
        wak = extras.getString("wak");
        is = extras.getString("isi");
        im = extras.getString("img");
        judul = findViewById(R.id.judul);
        judul2 = findViewById(R.id.judul2);
        waktu = findViewById(R.id.date_time);
        img = findViewById(R.id.image);
        isi = findViewById(R.id.isi);
        judul.setText(jud);
        judul2.setText(jud2);
        waktu.setText(wak);
        isi.setText(is);
        Picasso.with(getApplicationContext()).load(im).into(img);
    }

}