package com.example.keywordfinder.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keywordfinder.R;

import java.util.ArrayList;

public class GradingPage extends AppCompatActivity{
    private Button menuButton;
    private String color;
    private int numRight;
    private int numWrong;
    private int tot;


    private LinearLayout layout;

    private ArrayList<String> key;
    private ArrayList<String> answers;
    private ArrayList<String> original;
    private ArrayList<Boolean> match;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grading);
        //load input from previous page
        Intent intent = getIntent();
        key = intent.getStringArrayListExtra("key");
        answers = intent.getStringArrayListExtra("answers");
        original = intent.getStringArrayListExtra("original");

        color = "#94cffe";
        menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnMainMenu(v);
            }
        });


        layout = findViewById(R.id.linear_layout);

        grade();

        TextView summary = new TextView(this);
        summary.setText("Total Score: "+tot+" ("+numRight+" correct, "+numWrong+" incorrect)");
        layout.addView(summary);

        TextView incorrects = new TextView(this);
        incorrects.setText("The following words are different from the original text");
        layout.addView(incorrects);

        int ptr = -1;
        for (int i=0; i<original.size(); i++) {
            String s = original.get(i);
            if (s.equals("!!BLANK!!")) {
                ptr++;
                if (!match.get(i)) {
                    TextView right = new TextView(this);
                    right.setText(s);
                    right.setTextColor(Color.GREEN);
                    layout.addView(right);
                    TextView yourResponse = new TextView(this);
                    yourResponse.setText(answers.get(ptr));
                    yourResponse.setTextColor(Color.RED);
                    layout.addView(yourResponse);
                    continue;
                }
            }
            TextView t = new TextView(this);
            t.setText(s);
            layout.addView(t);
        }
    }

    //precondition: key and answers have the same number of elements
    private void grade() {
        for (int i=0; i<key.size(); i++) {
            if (same(i)) {
                numRight++;
                match.add(true);
            }
            else {
                numWrong++;
                match.add(false);
            }
        }

        tot = numRight+numWrong;
    }

    private boolean same(int index) {
        String s1 = key.get(index).toLowerCase();
        String s2 = answers.get(index).toLowerCase();
        return stripPunctuation(s1).equals(stripPunctuation(s2));
    }

    private String stripPunctuation(String s) {
        StringBuilder ret = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i)>='a' && s.charAt(i)<='z') ret.append(s.charAt(i));
        }
        return ret.toString();
    }

    public void returnMainMenu(View view) {
        menuButton.setTextColor(Color.parseColor(color));
        Intent intent = new Intent(GradingPage.this, HomePage.class);
        startActivity(intent);
    }
}
