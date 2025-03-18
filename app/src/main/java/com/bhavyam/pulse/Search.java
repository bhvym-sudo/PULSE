package com.bhavyam.pulse;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

public class Search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<SearchResult> searchResults;
    private EditText searchEditText;
    private ImageButton btnSearchIcon;
    private TextView resultsTextView;  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        
        searchEditText = findViewById(R.id.searchEditText);
        btnSearchIcon = findViewById(R.id.btnSearchIcon);
        recyclerView = findViewById(R.id.recyclerView);
        resultsTextView = findViewById(R.id.resultsTextView);  

        
        searchResults = new ArrayList<>();
        searchAdapter = new SearchAdapter(searchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(searchAdapter);

        
        searchEditText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    (keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                performSearch(searchEditText.getText().toString().trim());
                return true;
            }
            return false;
        });

        btnSearchIcon.setOnClickListener(view -> performSearch(searchEditText.getText().toString().trim()));

        
        ImageButton home = findViewById(R.id.btn1);
        ImageButton search = findViewById(R.id.btn2);
        ImageButton list = findViewById(R.id.btn3);

        home.setOnClickListener(view -> {
            Intent intent = new Intent(Search.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        search.setOnClickListener(view -> showToast("You are already on the search page"));

        list.setOnClickListener(view -> {
            Intent intent = new Intent(Search.this, ListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }
    private void performSearch(String query) {
        if (!query.isEmpty()) {

            resultsTextView.setText("Results for: " + query);
            fetchSearchResults(query);
        } else {
            showToast("Please enter a search query");
        }
    }

    
    private void fetchSearchResults(String query) {
        ApiService apiService = RetrofitClient.getApiService();
        Call<ApiResponse> call = apiService.searchNews(query);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    
                    searchResults.clear();
                    searchResults.addAll(response.body().getItem());
                    searchAdapter.notifyDataSetChanged();
                } else {
                    
                    showToast("Failed to fetch data: " + response.message());
                    Log.e("API_ERROR", "Response code: " + response.code());
                    Log.e("API_ERROR", "Error body: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                
                showToast("Error: " + t.getMessage());
                Log.e("API_FAILURE", "Error: ", t);
            }
        });
    }

    
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}