package com.example.keywordfinder.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keywordfinder.R;
import com.example.keywordfinder.functionality.TextTask;

import java.util.ArrayList;

public class TestingPage extends AppCompatActivity{
    private Button menuButton;
    private Button prevButton;
    private Button nextButton;
    private String color;
    private String inputText;
    private TextTask t;
    private ArrayList<String> userInput;
    private ArrayList<EditText> inputs;
    private LinearLayout layout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        Intent intent = getIntent();
        inputText = intent.getStringExtra("input");
        inputs = new ArrayList<EditText>();

        t = new TextTask(inputText);
        ArrayList<String> display = t.display();


        color = "#94cffe";

        layout = findViewById(R.id.linear_layout);

        //create display!

        for (int i=0; i<display.size(); i++) {
            String s = display.get(i);
            if (s.equals("!!BLANK!!")) {
                EditText e = new EditText(this);
                //e.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                inputs.add(e);
                layout.addView(e);
            }
            else {
                TextView t = new TextView(this);
                //t.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                t.setText(s);
                layout.addView(t);
            }
        }

        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnMainMenu(v);
            }
        });

        prevButton = findViewById(R.id.toInputPage);
        prevButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goBack(v);
            }
        });

        nextButton = findViewById(R.id.toGraderPage);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                proceed(v);
            }
        });
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor(color));
        Intent intent = new Intent(TestingPage.this, HomePage.class);
        startActivity(intent);
    }

    public void proceed(View view) {
        nextButton.setTextColor(Color.parseColor(color));

        //populate userInput with input from user

        for (int i=0; i<inputs.size(); i++) {
            userInput.add(inputs.get(i).getText().toString());
        }

        Intent intent = new Intent(TestingPage.this, GradingPage.class);
        intent.putExtra("key", t.getWords());
        intent.putExtra("answers", userInput);
        intent.putExtra("original", t.display());
        startActivity(intent);
    }

    public void goBack(View view) {
        prevButton.setTextColor(Color.parseColor(color));
        Intent intent = new Intent(TestingPage.this, TextReaderPage.class);
        startActivity(intent);
    }
}
