package com.doron.watchvault;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.doron.watchvault.network.models.RVModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomRVAdapter customRVAdapter;
    private ArrayList<RVModel> rvModels = new ArrayList<>();
    private static final String API_URL = "https://moviesdatabase.p.rapidapi.com/titles/x/upcoming";
    private static final String API_KEY = "45789babddmsh65e979a938a9f4cp1c1f73jsnb2db4bc0697b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ensure rvModels is not null and initialized
        customRVAdapter = new CustomRVAdapter(this, rvModels);
        recyclerView.setAdapter(customRVAdapter);
        recyclerView.setVisibility(View.VISIBLE);

        fetchMovies();
    }

    private void fetchMovies() {
        Log.d("MainActivity", "Fetching movies...");
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL)
                .get()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", "moviesdatabase.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new okhttp3.Callback() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String jsonResponse = response.body().string();
                    Log.d("API Response", jsonResponse);
                    parseJsonResponse(jsonResponse);
                    Log.d("MainActivity", "Parsed data size: " + rvModels.size());
                    runOnUiThread(() -> {
                        customRVAdapter.notifyDataSetChanged();
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to Load", Toast.LENGTH_SHORT).show());
                }
            }
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Log.e("MainActivity", "onFailure: ", e);
                runOnUiThread(() -> Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
            }
        });
    }

    private void parseJsonResponse(String jsonResponse) {
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray resultsArray = jsonObject.optJSONArray("results");

            if (resultsArray != null) {
                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject movieObject = resultsArray.optJSONObject(i);

                    if (movieObject != null) {
                        String id = movieObject.optString("id", null);
                        JSONObject primaryImage = movieObject.optJSONObject("primaryImage");
                        String imageUrl = primaryImage != null ? primaryImage.optString("url", null) : null;
                        String title = Objects.requireNonNull(movieObject.optJSONObject("titleText")).optString("text", null);

                        JSONObject releaseDate = movieObject.optJSONObject("releaseDate");
                        int releaseDay = releaseDate != null ? releaseDate.optInt("day", 0) : 0;
                        int releaseMonth = releaseDate != null ? releaseDate.optInt("month", 0) : 0;
                        int releaseYear = releaseDate != null ? releaseDate.optInt("year", 0) : 0;

                        RVModel rvModel = new RVModel(id, imageUrl, title, releaseDay, releaseMonth, releaseYear);
                        rvModels.add(rvModel);
                    }
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}