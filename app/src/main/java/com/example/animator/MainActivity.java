package com.example.animator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static final int PIXELS = 16*9;
    private static MainActivity instance;
    private Pixel[] pixels = new Pixel[PIXELS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        fillWhite();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                GridView gridView = findViewById(R.id.gridView);
                System.out.println("================================================== " + gridView.getLayoutParams().width);

                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this);

                gridView.setAdapter(imageAdapter);
                gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                gridView.setOnItemClickListener((parent, view, position, id) -> {
                    setPixel(position, new Pixel(0, 0, 0));
                    gridView.setAdapter(imageAdapter);
                });
            }
        }, 20);
    }

    public void setPixel(int pos, Pixel pixel) {
        pixels[pos] = pixel;
    }

    public Pixel getPixel(int pos) {
        return pixels[pos];
    }

    public void fillWhite() {
        for (int i = 0; i < PIXELS; i++) {
            pixels[i] = new Pixel(0, 255, 255);
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }
}