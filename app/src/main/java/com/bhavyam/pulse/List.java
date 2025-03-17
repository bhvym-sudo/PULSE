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
public class List extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ImageButton home = findViewById(R.id.btn1);
        ImageButton search = findViewById(R.id.btn2);
        ImageButton list = findViewById(R.id.btn3);



        home.setOnClickListener(view -> {
            Intent intent = new Intent(List.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        search.setOnClickListener(view -> {
            Intent intent = new Intent(List.this, Search.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        list.setOnClickListener(view -> showToast("You already on the list page"));
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
