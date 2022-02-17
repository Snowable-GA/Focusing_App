package com.example.focusing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Settings extends AppCompatActivity {
    SwitchCompat switchSound;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        player = MediaPlayer.create(Settings.this, android.provider.Settings.System.DEFAULT_RINGTONE_URI);
        player.setLooping(true);
        player.start();

        switchSound = (SwitchCompat) findViewById(R.id.sound);
        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    player = MediaPlayer.create(Settings.this, android.provider.Settings.System.DEFAULT_RINGTONE_URI);
                    player.setLooping(true);
                    player.start();
                }
                else{
                    player.stop();
                }
            }
        });

    }
}