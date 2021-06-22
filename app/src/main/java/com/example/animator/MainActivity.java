package com.example.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    public static final int PIXELS = 16*9;
    private static MainActivity instance;
    private GridView gridView;
    private Pixel[] pixels = new Pixel[PIXELS];
    private int selectedColor = 000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        Button button = findViewById(R.id.select_color);
        button.setOnClickListener(v -> ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(selectedColor -> { })
                .setPositiveButton("Select", (dialog, selectedColor, allColors) -> MainActivity.this.selectedColor = selectedColor)
                .setNegativeButton("Cancel", (dialog, which) -> { })
                .build()
                .show());

        fillWhite();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void run() {
                ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this);

                gridView = findViewById(R.id.gridView);
                gridView.setAdapter(imageAdapter);
                gridView.setOnTouchListener((v, event) -> {
                    float fX = event.getX(),
                            fY = event.getY();
                    int size = (gridView.getWidth()-1) / 9,
                            pos = (int) (Math.floor(fX / size) + (9 * Math.floor(fY / size)));

                    if (pos < 0 || pos > pixels.length-1) return false;

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                        case MotionEvent.ACTION_MOVE:
                        case MotionEvent.ACTION_UP: {
                            setPixel(pos, fromHex(selectedColor));
                            imageAdapter.notifyDataSetChanged();
                        } break;
                        default: break;
                    }

                    return false;
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

    public GridView getGridView() {
        return gridView;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public static Pixel fromHex(int hex) {
        int r = (hex & 0xFF0000) >> 16,
                g = (hex & 0xFF00) >> 8,
                b = (hex & 0xFF);

        return new Pixel(r, g, b);
    }
}