package com.example.cocomath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        Button easy = (Button)findViewById(R.id.easyBtn);
        easy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(PlayActivity.this, EasyGameActivity.class);
                intent1.putExtra("mode", "easy");
                startActivity(intent1);
            }
        });
        Button normal = (Button)findViewById(R.id.normalBtn);
        normal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(PlayActivity.this, NormalGameActivity.class);
                intent2.putExtra("mode", "normal");
                startActivity(intent2);
            }
        });
        Button hard = (Button)findViewById(R.id.hardBtn);
        hard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(PlayActivity.this, HardGameActivity.class);
                intent3.putExtra("mode", "hard");
                startActivity(intent3);
            }
        });

    }

}
