package com.example.focusing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class Settings extends AppCompatActivity {
    SwitchCompat switchSound;
    SwitchCompat switchVibration;
    private MediaPlayer player;
    private Vibrator vibrator;

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

        switchVibration = (SwitchCompat) findViewById(R.id.vibration);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] pattern = {2000, 1000};
        switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    vibrator.vibrate(pattern, 0);
                    Toast.makeText(Settings.this, "Vibration on", Toast.LENGTH_SHORT).show();
                }
                else{
                    vibrator.cancel();
                    Toast.makeText(Settings.this, "Vibration off", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}