package com.example.focusing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {
    Button btn_Focus;
    Button btn_Shop;
    Button btn_Settings;
    Button btn_Help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btn_Focus = findViewById(R.id.btn_focus);
        btn_Focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Focus.class);
                startActivity(intent);
            }
        });

        btn_Shop = findViewById(R.id.btn_shop);
        btn_Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Shop.class);
                startActivity(intent);
            }
        });

        btn_Settings = findViewById(R.id.btn_settings);
        btn_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Settings.class);
                startActivity(intent);
            }
        });

        btn_Help = findViewById(R.id.btn_help);
        btn_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashBoard.this, Help.class);
                startActivity(intent);
            }
        });
    }
}