package com.example.cocomath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    TextView score_tv;
    int yourscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        yourscore();

        Button again = (Button)findViewById(R.id.tryBtn);
        Button home = (Button)findViewById(R.id.homeBtn);

        again.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GameOverActivity.this, PlayActivity.class);
                startActivity(intent1);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    public void yourscore(){
        score_tv = (TextView)findViewById(R.id.scoretv);
        String yourscore = getIntent().getStringExtra("yourscore");
        score_tv.setText(yourscore);
    }

}