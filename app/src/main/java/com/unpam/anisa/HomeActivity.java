package com.unpam.anisa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.unpam.anisa.adapter.AdapterVideo;
import com.unpam.anisa.adapter.AdapterVideoAll;
import com.unpam.anisa.model.VideoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    RecyclerView video, list;
    TextView a;
    private List<VideoModel> videonewlist = new ArrayList<>();
    private List<VideoModel> videonewlist2 = new ArrayList<>();
    private AdapterVideo adapterVideonew;
    private AdapterVideoAll adapterVideoAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        list = findViewById(R.id.list_all_movie);
        video = findViewById(R.id.list_new_movie);
        a = findViewById(R.id.a);
        adapterVideonew = new AdapterVideo(videonewlist);
        adapterVideoAll = new AdapterVideoAll(videonewlist2);
        volly();
        volly1();
        adapterVideoAll.OnRecycleViewClickListener(
                new AdapterVideoAll.OnRecycleViewClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Toast.makeText(getApplicationContext(), videonewlist2.get(position).getId(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(HomeActivity.this, DetailVideoActivity.class);
                        intent.putExtra("judul", videonewlist2.get(position).getTitle());
                        intent.putExtra("judul2", videonewlist2.get(position).getTitle());
                        intent.putExtra("img", videonewlist2.get(position).getImage());
                        intent.putExtra("waktu", videonewlist2.get(position).getTime());
                        intent.putExtra("isi", videonewlist2.get(position).getTagline());
                        startActivity(intent);
                    }
                }
        );
        adapterVideonew.OnRecycleViewClickListener(
                new AdapterVideo.OnRecycleViewClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Toast.makeText(getApplicationContext(), videonewlist.get(position).getId(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(HomeActivity.this, DetailVideoActivity.class);
                        intent.putExtra("judul", videonewlist2.get(position).getTitle());
                        intent.putExtra("judul2", videonewlist2.get(position).getTitle());
                        intent.putExtra("img", videonewlist2.get(position).getImage());
                        intent.putExtra("waktu", videonewlist2.get(position).getTime());
                        intent.putExtra("isi", videonewlist2.get(position).getTagline());                        startActivity(intent);
                    }
                }
        );
    }

    public void volly1()
    {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list.setLayoutManager(mLayoutManager);
        list.setItemAnimator(new DefaultItemAnimator());
        list.setAdapter(adapterVideoAll);

        String url = "https://newsapi.org/v2/everything?q=indonesia&sortBy=popularity";
        final String apiKey = "046a5991cbc24d64818da2f9fbb48406";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jo = new JSONObject(response.toString());
                            JSONArray jsonArray = jo.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject jsonObject2 = jsonObject.getJSONObject("source");
                                String id = jsonObject2.optString("id");
                                String judul = jsonObject.optString("title");
                                String description = jsonObject.optString("content");
                                String publishedAt = jsonObject.optString("publishedAt");
                                String gambar = jsonObject.optString("urlToImage");
                                videonewlist2.add(new VideoModel(id, judul, gambar, publishedAt, description));
                            }
                            adapterVideoAll.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Language", "en-US,en;q=0.5");
                headers.put("X-Api-Key", apiKey);
                return headers;
            }
        };
        videonewlist2.clear();
        queue.add(stringRequest);
    }

    public void volly()
    {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this.getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        video.setLayoutManager(mLayoutManager);
        video.setItemAnimator(new DefaultItemAnimator());
        video.setAdapter(adapterVideonew);

        String url = "http://newsapi.org/v2/top-headlines?country=us";
        final String apiKey = "046a5991cbc24d64818da2f9fbb48406";

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jo = new JSONObject(response.toString());
                            JSONArray jsonArray = jo.getJSONArray("articles");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                JSONObject jsonObject2 = jsonObject.getJSONObject("source");
                                String id = jsonObject2.optString("id");
                                String judul = jsonObject.optString("title");
                                String description = jsonObject.optString("content");
                                String publishedAt = jsonObject.optString("publishedAt");
                                String gambar = jsonObject.optString("urlToImage");
                                videonewlist.add(new VideoModel(id, judul, gambar, publishedAt, description));
                            }
                            adapterVideonew.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                headers.put("Accept-Language", "en-US,en;q=0.5");
                headers.put("X-Api-Key", apiKey);
                return headers;
            }
        };
        videonewlist.clear();
        queue.add(stringRequest);
    }
}