package com.example.cocomath;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class NormalGameActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mGravity;
    private boolean isGravitySensorPresent;
    private boolean correct = false;
    private AudioManager aManager;
    private TextView question, choice1, choice2;
    Random ran = new Random();
    boolean stay = false;
    TextView score_tv;
    int score = 0;
    int num1, num2;
    int count = 0;
    int ans = 0;
    int fake = 0;
    int pos = 0;
    int c = 0;
    int myProgress = 0;
    boolean status_game = true;
    ProgressBar progressBar;
    Button btn_start;
    TextView tv_time;
    EditText et_timer;
    int progress;
    int endTime = 250;
    String mode = "";

    TextView textCounter;
    MyCountDownTimer myCountDownTimer;
    int counter = 0;

    ProgressBar progressBarView;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        score_tv = (TextView) findViewById(R.id.scoretv);
        score_tv.setText("" + score);

        aManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        question = findViewById(R.id.question);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);

        mode = getIntent().getStringExtra("mode");
        RandomNum(mode);

        progressBarView = (ProgressBar) findViewById(R.id.view_progress_bar);
        tv_time= (TextView)findViewById(R.id.tv_timer);

        /*Animation*/
        RotateAnimation makeVertical = new RotateAnimation(0, -90, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        makeVertical.setFillAfter(true);
        progressBarView.startAnimation(makeVertical);
        progressBarView.setSecondaryProgress(endTime);
        progressBarView.setProgress(0);

        fn_countdown();

        myCountDownTimer = new MyCountDownTimer(20000, 1000);
        myCountDownTimer.start();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mGravity = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isGravitySensorPresent = true;
        } else {
            isGravitySensorPresent = false;
        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            int progress = (int) (millisUntilFinished / 100);
            int seconds = (int) (millisUntilFinished / 1000) % 60;
        }

        @Override
        public void onFinish() {
            counter++;
            Intent intent2 = new Intent(NormalGameActivity.this, GameOverActivity.class);
            TextView yourscore = (TextView) findViewById(R.id.scoretv);
            String value = yourscore.getText().toString();
            intent2.putExtra("yourscore", value);
            countDownTimer.cancel();
            if(status_game == true){
                startActivity(intent2);
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Intent intent = new Intent(NormalGameActivity.this, GameOverActivity.class);
        TextView yourscore = (TextView) findViewById(R.id.scoretv);
        String value = yourscore.getText().toString();
        intent.putExtra("yourscore", value);

        if (sensorEvent.values[2] > 4) {
            c++;
            if (c == 1) {
                if (pos == 0) {
                    if (sensorEvent.values[0] > 4) {
                        score();
                        RandomNum(mode);
                        aManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    }
                    if (sensorEvent.values[0] < -2) {
                        status_game = false;
                        countDownTimer.cancel();
                        startActivity(intent);
                    }
                } else {
                    if (sensorEvent.values[0] < -2) {
                        score();
                        RandomNum(mode);
                        aManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    }
                    if (sensorEvent.values[0] > 4) {
                        status_game = false;
                        countDownTimer.cancel();
                        startActivity(intent);
                    }
                }
            }
        } else {
            aManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            c = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensorManager.unregisterListener(this, mGravity);
        }
    }

    public void RandomNum(String mode) {
        count++;

        if (mode.equalsIgnoreCase("easy")) {
            num1 = ran.nextInt((10 - 1) + 1) + 1;
            num2 = ran.nextInt((10 - 1) + 1) + 1;
            pos = ran.nextInt(2);
            question.setText(num1 + " + " + num2 + " = ?");
            ans = num1 + num2;
            fake = ans + 2;
        }

        if (mode.equalsIgnoreCase("normal")) {
            num1 = ran.nextInt((15 - 1) + 1) + 1;
            num2 = ran.nextInt((15 - 1) + 1) + 1;
            pos = ran.nextInt(2);
            question.setText(num1 + " - " + num2 + " = ?");
            ans = num1 - num2;
            fake = ans + 2;
            if (count >= 1 && count <= 10) {
                question.setText(num1 + " + " + num2 + " = ?");
                ans = num1 + num2;
                fake = ans + 2;
            }
            if (count >= 11 && count <= 20) {
                question.setText(num1 + " - " + num2 + " = ?");
                ans = num1 - num2;
                fake = ans + 2;
                if (count == 20) {
                    count = 0;
                }
            }
        }

        if (mode.equalsIgnoreCase("hard")) {
            num1 = ran.nextInt((20 - 1) + 1) + 1;
            num2 = ran.nextInt((20 - 1) + 1) + 1;
            pos = ran.nextInt(2);
            if (count >= 1 && count <= 5) {
                question.setText(num1 + " + " + num2 + " = ?");
                ans = num1 + num2;
                fake = ans + 2;
            }
            if (count >= 6 && count <= 10) {
                question.setText(num1 + " - " + num2 + " = ?");
                ans = num1 - num2;
                fake = ans + 2;

            }
            if (count >= 11 && count <= 15) {
                question.setText(num1 + " x " + num2 + " = ?");
                ans = num1 * num2;
                fake = ans + 2;
                if (count == 30) {
                    count = 0;
                }
            }
        }
        if (pos == 0) {
            choice1.setText(String.valueOf(ans));
            choice2.setText(String.valueOf(fake));
        } else {
            choice1.setText(String.valueOf(fake));
            choice2.setText(String.valueOf(ans));
        }
    }

    public void score() {
        score_tv = (TextView) findViewById(R.id.scoretv);
        score += 2;
        score_tv.setText("" + score);

        SharedPreferences preferences2 = getSharedPreferences("PREFS2", 0);
        SharedPreferences.Editor editor = preferences2.edit();
        editor.putInt("lastscorenormal", score);
        editor.apply();
    }

    private void fn_countdown() {


        myProgress = 0;

        try {
            countDownTimer.cancel();

        } catch (Exception e) {

        }

//            String timeInterval = et_timer.getText().toString();
        progress = 1;
//            endTime = Integer.parseInt(timeInterval); // up to finish time
        endTime=20;
        countDownTimer = new CountDownTimer(endTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setProgress(progress, endTime);
                progress = progress + 1;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(), "%2d", seconds);
                tv_time.setText(timeLeftFormatted);

            }

            @Override
            public void onFinish() {
                setProgress(progress, endTime);


            }
        };
        countDownTimer.start();

    }

    public void setProgress(int startTime, int endTime) {
        progressBarView.setMax(endTime);
        progressBarView.setSecondaryProgress(endTime);
        progressBarView.setProgress(startTime);

    }
}