package com.bhavyam.pulse;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity extends AppCompatActivity {
    private TextView titleText;
    private TextView par1;
    private TextView par2;

    private String titleTextFull = "Welcome to PULSE";
    private String par1Full = "Personalized Updates";
    private String par2Full = "Live Streaming Engine";

    private int index = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleText = findViewById(R.id.titleText);
        par1 = findViewById(R.id.par1);
        par2 = findViewById(R.id.par2);

        Typeface inter = ResourcesCompat.getFont(this, R.font.inter);
        titleText.setTypeface(inter);
        par1.setTypeface(inter);
        par2.setTypeface(inter);

        titleText.setText("");
        par1.setText("");
        par2.setText("");

        animateTitleText();

        ImageButton home = findViewById(R.id.btn1);
        ImageButton search = findViewById(R.id.btn2);
        ImageButton list = findViewById(R.id.btn3);

        home.setOnClickListener(view -> showToast("You are already on the home page."));

        search.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Search.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        list.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
    }

    private void animateTitleText() {
        index = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index < titleTextFull.length()) {
                    titleText.setText(titleText.getText().toString() + titleTextFull.charAt(index));
                    index++;
                    handler.postDelayed(this, 100);
                } else {
                    animatePar1();
                }
            }
        }, 100);
    }

    private void animatePar1() {
        index = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index < par1Full.length()) {
                    par1.setText(par1.getText().toString() + par1Full.charAt(index));
                    index++;
                    handler.postDelayed(this, 100);
                } else {
                    animatePar2(); 
                }
            }
        }, 100);
    }

    private void animatePar2() {
        index = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (index < par2Full.length()) {
                    par2.setText(par2.getText().toString() + par2Full.charAt(index));
                    index++;
                    handler.postDelayed(this, 100);
                }
            }
        }, 100);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
