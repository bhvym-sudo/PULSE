package com.bhavyam.pulse;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<NewsItem> newsItems;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        drawerLayout = findViewById(R.id.drawerLayout);

        ImageButton btnSideMenu = findViewById(R.id.btnSideMenu);
        btnSideMenu.setOnClickListener(v -> drawerLayout.openDrawer(findViewById(R.id.side_menu)));

        TextView Statusbtn = findViewById(R.id.catStatus);
        Statusbtn.setText("World");




        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        newsItems = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsItems);
        recyclerView.setAdapter(newsAdapter);

        setupSideMenu();

        fetchNewsData("all");

        ImageButton home = findViewById(R.id.btn1);
        ImageButton search = findViewById(R.id.btn2);
        ImageButton list = findViewById(R.id.btn3);

        home.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        search.setOnClickListener(view -> {
            Intent intent = new Intent(ListActivity.this, Search.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        list.setOnClickListener(view -> showToast("You are already on the list page"));
    }

    private void setupSideMenu() {
        TextView menuWorld = findViewById(R.id.menuWorld);
        TextView menuEurope = findViewById(R.id.menuEurope);
        TextView menuAfrica = findViewById(R.id.menuAfrica);
        TextView menuAmericas = findViewById(R.id.menuAmerica);
        TextView menuAsiaPacific = findViewById(R.id.menuAsiaPacific);
        TextView menuMiddleEast = findViewById(R.id.menuMiddleEast);

        menuWorld.setOnClickListener(v -> {
            fetchNewsData("world");
            drawerLayout.closeDrawers();
            ImageButton refreshButton = findViewById(R.id.btnRefresh);
            refreshButton.setOnClickListener(view -> fetchNewsData("all"));
            refreshButton.setOnClickListener(view -> {
                fetchNewsData("world");
                showToast("Refreshed top news");
            });
            TextView Statusbtn = findViewById(R.id.catStatus);
            Statusbtn.setText("World");

        });

        menuEurope.setOnClickListener(v -> {
            fetchNewsData("europe");
            drawerLayout.closeDrawers();
            ImageButton refreshButton = findViewById(R.id.btnRefresh);
            refreshButton.setOnClickListener(view -> fetchNewsData("europe"));
            refreshButton.setOnClickListener(view -> {
                fetchNewsData("europe");
                showToast("Refreshed europe news");
            });TextView Statusbtn = findViewById(R.id.catStatus);
            Statusbtn.setText("Europe");

        });

        menuAfrica.setOnClickListener(v -> {
            fetchNewsData("africa");
            drawerLayout.closeDrawers();
            ImageButton refreshButton = findViewById(R.id.btnRefresh);
            refreshButton.setOnClickListener(view -> {
                fetchNewsData("africa");
                showToast("Refreshed africa news");
            });
            TextView Statusbtn = findViewById(R.id.catStatus);
            Statusbtn.setText("Africa");



        });

        menuAmericas.setOnClickListener(v -> {
            fetchNewsData("americas");
            drawerLayout.closeDrawers();
            ImageButton refreshButton = findViewById(R.id.btnRefresh);
            refreshButton.setOnClickListener(view -> {
                fetchNewsData("americas");
                showToast("Refreshed america news");
            });
            TextView Statusbtn = findViewById(R.id.catStatus);
            Statusbtn.setText("America");

        });

        menuAsiaPacific.setOnClickListener(v -> {
            fetchNewsData("asia-pacific");
            drawerLayout.closeDrawers();
            ImageButton refreshButton = findViewById(R.id.btnRefresh);
            refreshButton.setOnClickListener(view -> {
                fetchNewsData("asia-pacific");
                showToast("Refreshed asia-pacific news");
            });
            TextView Statusbtn = findViewById(R.id.catStatus);
            Statusbtn.setText("Asia-Pacific");


        });

        menuMiddleEast.setOnClickListener(v -> {
            fetchNewsData("middle-east");
            drawerLayout.closeDrawers();
            ImageButton refreshButton = findViewById(R.id.btnRefresh);
            refreshButton.setOnClickListener(view -> {
                fetchNewsData("middle-east");
                showToast("Refreshed middle-east news");
            });
            TextView Statusbtn = findViewById(R.id.catStatus);
            Statusbtn.setText("Middle-East");


        });
    }

    private void fetchNewsData(String category) {
        ApiService apiService = RetrofitClient.getApiService();
        Call<List<NewsItem>> call;

        switch (category) {
            case "world":
                call = apiService.getWorldNews();
                break;
            case "europe":
                call = apiService.getEuropeNews();
                break;
            case "africa":
                call = apiService.getAfricaNews();
                break;
            case "americas":
                call = apiService.getAmericasNews();
                break;
            case "asia-pacific":
                call = apiService.getAsiaPacificNews();
                break;
            case "middle-east":
                call = apiService.getMiddleEastNews();
                break;
            default:
                call = apiService.getWorldNews();  // Default to World news
                break;
        }

        call.enqueue(new Callback<List<NewsItem>>() {
            @Override
            public void onResponse(Call<List<NewsItem>> call, Response<List<NewsItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsItems.clear();
                    newsItems.addAll(response.body());
                    newsAdapter.notifyDataSetChanged();
                } else {
                    showToast("Failed to fetch data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<NewsItem>> call, Throwable t) {
                showToast("Error: " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}