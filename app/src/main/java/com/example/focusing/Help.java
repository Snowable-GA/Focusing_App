package com.example.focusing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Help extends AppCompatActivity {
    private Button btn_instruction;
    private Button btn_rate;
//    private Button btn_version;
//    String sdkVersion = Build.VERSION.SDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        btn_instruction = findViewById(R.id.btn_instruction);
        btn_instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this, Instruction.class);
                startActivity(intent);
            }
        });

        btn_rate = findViewById(R.id.btn_rate);
        btn_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Help.this, RateApp.class);
                startActivity(intent);
            }
        });

//        btn_version = findViewById(R.id.btn_version);
//        btn_version.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "Version: " + sdkVersion, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}