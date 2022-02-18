package com.example.focusing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Focus extends AppCompatActivity {

    private TextView countdownText;
    private Button countdownButton;

    private CountDownTimer countDownTimer;
    private long timeleftinMilliseconds = 1500000;
    private boolean timerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.countdown_button);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Focus Notification", "Focus Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start_and_Stop();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Focus.this, "Focus Notification");
                builder.setContentTitle("FOFO - FOCUS FOREVER!");
                builder.setContentText("You've just started focusing!");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Focus.this);
                managerCompat.notify(1, builder.build());
            }
        });
    }

    public void Start_and_Stop(){
        if (timerRunning){
            stopTimer();
        }
        else{
            startTimer();
        }
    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeleftinMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeleftinMilliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();

        countdownButton.setText("PAUSE");
        timerRunning = true;
    }



    public void stopTimer(){
        countDownTimer.cancel();
        countdownButton.setText("START");
        timerRunning = false;
    }

    public void updateTimer(){
        int minutes = (int) timeleftinMilliseconds / 60000;
        int seconds = (int) timeleftinMilliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";

        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);


    }
}