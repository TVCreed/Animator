package com.example.animator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static final int PIXELS = 16*9;
    private Pixel[] pixels = new Pixel[PIXELS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnTEST = findViewById(R.id.btnBottom);
        GridView gridView = findViewById(R.id.gridView);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillWhite();

            }
        });

        fillWhite();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("================================================== " + gridView.getLayoutParams().width);

                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this);

                gridView.setAdapter(imageAdapter);
                gridView.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(getBaseContext(), "Selected grid item " + (position + 1), Toast.LENGTH_SHORT).show());
            }
        }, 20);
    }

    public void fillWhite() {
        for (int i = 0; i < PIXELS; i++) {
            pixels[i] = new Pixel(255, 255, 255);
        }
    }
}