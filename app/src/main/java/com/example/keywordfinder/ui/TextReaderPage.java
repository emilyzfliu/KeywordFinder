package com.example.keywordfinder.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keywordfinder.R;

public class TextReaderPage extends AppCompatActivity{
    private Button menuButton;
    private Button nextButton;
    private EditText inputField;
    private String color;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textreader);
        color = "#94cffe";
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnMainMenu(v);
            }
        });


        nextButton = findViewById(R.id.toTestingPage);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proceed(v);
            }
        });

        inputField = findViewById(R.id.inputField);
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor(color));
        Intent intent = new Intent(TextReaderPage.this, HomePage.class);
        startActivity(intent);
    }

    public void proceed(View view) {
        nextButton.setTextColor(Color.parseColor(color));
        Intent intent = new Intent(TextReaderPage.this, TestingPage.class);
        intent.putExtra("input",inputField.getText().toString());
        startActivity(intent);
    }
}
