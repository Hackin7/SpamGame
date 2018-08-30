package uselessness.spamgame;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int spammed = 0;
    boolean canspam = true;


    public void spammedUpdate(){
        TextView counterTextView = (TextView) findViewById(R.id.spam_counter);
        counterTextView.setText(spammed+"");
    }
    public void timerUpdate(float time){
        TextView counterTextView = (TextView) findViewById(R.id.time_counter);
        counterTextView.setText("Seconds left: "+time);
    }
    public void alert(String title,String message){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder startStopTime = new AlertDialog.Builder(MainActivity.this);
        startStopTime.setMessage(message);
        startStopTime.setTitle(title);

        // 3. Get the AlertDialog from create()
        final AlertDialog dialog = startStopTime.create();
        dialog.show();
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();//do something
            }
        }, 2000);
        */
    }
    CountDownTimer timer = new CountDownTimer(60000, 1000) {

        public void onTick(long millisUntilFinished) {
            timerUpdate(millisUntilFinished / 1000);
        }

        public void onFinish() {
            canspam = false;
            alert("Time's Up", "Score: "+spammed);
        }
    };

    public void time(View view) {
        canspam = true;spammed = 0;spammedUpdate();
        /*alert("Start","60 seconds starts");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.start(); //Time in ms
            }
        }, 2000);
        */
        timer.start();
    }
    public void spam(View view) {
        if (canspam){spammed += 1;}
        spammedUpdate();
    }

}
