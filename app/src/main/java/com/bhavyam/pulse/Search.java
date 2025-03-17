package com.bhavyam.pulse;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

public class Search extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText searchEditText = findViewById(R.id.searchEditText);
        ImageButton btnSearchIcon = findViewById(R.id.btnSearchIcon);   


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
            Intent intent = new Intent(Search.this, List.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    // Search function
    private void performSearch(String query) {
        if (!query.isEmpty()) {
            Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a search query", Toast.LENGTH_SHORT).show();
        }
    }

    // Toast function
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
