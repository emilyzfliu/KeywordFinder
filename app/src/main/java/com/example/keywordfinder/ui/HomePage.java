package com.example.keywordfinder.ui;

import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.keywordfinder.R;

public class HomePage extends AppCompatActivity{
    private String color;

    private Button startButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        color = "#94cffe";

        startButton = findViewById(R.id.startButton);
        startButton.setTextColor(Color.parseColor(color));
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proceed(v);
            }
        });
    }

    public void proceed(View v) {
        Intent intent = new Intent(HomePage.this, TextReaderPage.class);
        startActivity(intent);
    }
}
