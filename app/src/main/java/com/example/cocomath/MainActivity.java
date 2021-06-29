package com.example.cocomath;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView seasy_tv,snormal_tv,shard_tv;
    int lastscore,lastscorenormal,lastscorehard;
    int high1, high2, high3,high4, high5, high6,high7, high8, high9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowDialog();
        Highscore();

        Button play = (Button) findViewById(R.id.homeBtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, PlayActivity.class);
//                Intent intent2 = new Intent(MainActivity.this, InstructionsActivity.class);
                startActivity(intent1);
//                ShowDialog();
            }
        });
    }

    public void ShowDialog(){
        final AlertDialog.Builder viewDialog = new AlertDialog.Builder(this);
        viewDialog.setTitle("HOW TO PLAY");
        viewDialog.setMessage("FLIP TO THE LEFT OR RIGHT TO ANSWER");
        viewDialog.setPositiveButton("close", null);
        viewDialog.show();
    }

    public void Highscore(){

        seasy_tv = (TextView) findViewById(R.id.seasy);
        snormal_tv = (TextView) findViewById(R.id.snormal);
        shard_tv = (TextView) findViewById(R.id.shard);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastscore = preferences.getInt("lastscore", 0);
        high1 = preferences.getInt("high1", 0);
        high2 = preferences.getInt("high2", 0);
        high3 = preferences.getInt("high3", 0);

        if (lastscore > high3) {
            high3 = lastscore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high3", high3);
            editor.apply();
        }
        if (lastscore > high2) {
            int temp = high2;
            high2 = lastscore;
            high3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high3", high3);
            editor.putInt("high2", high2);
            editor.apply();
        }
        if (lastscore > high1) {
            int temp = high1;
            high1 = lastscore;
            high2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high2", high2);
            editor.putInt("high1", high1);
            editor.apply();
        }

        SharedPreferences preferences2 = getSharedPreferences("PREFS2", 0);
        lastscorenormal = preferences2.getInt("lastscorenormal", 0);
        high4 = preferences.getInt("high4", 0);
        high5 = preferences.getInt("high5", 0);
        high6 = preferences.getInt("high6", 0);

        if (lastscorenormal > high6) {
            high6 = lastscorenormal;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high6", high6);
            editor.apply();
        }
        if (lastscorenormal > high5) {
            int temp = high5;
            high5 = lastscorenormal;
            high6 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high6", high6);
            editor.putInt("high5", high5);
            editor.apply();
        }
        if (lastscorenormal > high4) {
            int temp = high4;
            high4 = lastscorenormal;
            high5 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high5", high5);
            editor.putInt("high4", high4);
            editor.apply();
        }

        SharedPreferences preferences3 = getSharedPreferences("PREFS3", 0);
        lastscorehard = preferences3.getInt("lastscorehard", 0);
        high7 = preferences.getInt("high7", 0);
        high8 = preferences.getInt("high8", 0);
        high9 = preferences.getInt("high9", 0);

        if (lastscorehard > high9) {
            high9 = lastscorehard;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high9", high9);
            editor.apply();
        }
        if (lastscorehard > high8) {
            int temp = high8;
            high8 = lastscorehard;
            high9 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high9", high9);
            editor.putInt("high8", high8);
            editor.apply();
        }
        if (lastscorehard > high7) {
            int temp = high7;
            high7 = lastscorehard;
            high8 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("high8", high8);
            editor.putInt("high7", high7);
            editor.apply();
        }

        seasy_tv.setText(" " + high1);
        snormal_tv.setText(" " + high4);
        shard_tv.setText(" " + high7);

    }
}