package com.example.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static final int WIDTH = 16, HEIGHT = 16;
    private Pixel[] pixels = new Pixel[WIDTH*HEIGHT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillWhite();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                GridView gridView = findViewById(R.id.gridView);
                System.out.println("================================================== " + gridView.getLayoutParams().width);

                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this);

                gridView.setAdapter(imageAdapter);
                gridView.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(getBaseContext(), "Selected grid item " + (position + 1), Toast.LENGTH_SHORT).show());
            }
        }, 20);
    }

    public void fillWhite() {
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            pixels[i] = new Pixel(255, 255, 255);
        }
    }
}