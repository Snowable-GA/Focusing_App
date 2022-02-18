package com.example.focusing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateApp extends AppCompatActivity {
    private RatingBar ratingbar;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        ratingbar = findViewById(R.id.ratingbar);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = String.valueOf(ratingbar.getRating());
                Toast.makeText(getApplicationContext(), s + " stars", Toast.LENGTH_SHORT).show();
            }
        });
    }
}