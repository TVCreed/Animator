package com.example.animator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int PIXELS = 16*9;
    private static MainActivity instance;
    private GridView colorsView;
    private ColorAdapter colorAdapter;
    private GridView gridView;
    private List<Pixel> colors = new ArrayList<>();
    private Pixel[] pixels = new Pixel[PIXELS];
    private Pixel[] tempPixels = new Pixel[PIXELS];
    private Pixel selectedColor = new Pixel(0, 0, 0);
    private boolean removingColor = false;
    private ImageAdapter imageAdapter = new ImageAdapter(MainActivity.this);

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameArray SavedFrames = new FrameArray();

        instance = this;

        findViewById(R.id.groupColors).setVisibility(View.VISIBLE);
        findViewById(R.id.groupFrame).setVisibility(View.GONE);

        Button addColor = findViewById(R.id.btnAddColor),
            removeColor = findViewById(R.id.btnRemoveColor),
            AddFrame = findViewById(R.id.btnAddFrame),
            RemoveFrame = findViewById(R.id.btnRemoveFrame),
            PaletteBtn = findViewById(R.id.btnPaletteV),
            FramesBtn = findViewById(R.id.btnFramesV);

        PaletteBtn.setOnClickListener(v -> {
            findViewById(R.id.groupFrame).setVisibility(View.GONE);
            findViewById(R.id.groupColors).setVisibility(View.VISIBLE);
        });
        addColor.setOnClickListener(v -> ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(selectedColor -> {
                })
                .setPositiveButton("Select", (dialog, selectedColor, allColors) -> {
                    //add color
                    colors.add(fromHex(selectedColor));
                    colorAdapter.notifyDataSetChanged();

                    ColorLayout layout;

                    if (colorAdapter.getCount() <= 1) layout = ColorLayout.ONE;
                    else if (colorAdapter.getCount() <= 2) layout = ColorLayout.TWO;
                    else if (colorAdapter.getCount() <= 4) layout = ColorLayout.FOUR;
                    else layout = ColorLayout.EIGHT;

                    layout.applyGrid(colorsView);
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                })
                .build()
                .show()
        );
        removeColor.setOnClickListener(v -> {
            removingColor = !removingColor;
            Toast.makeText(this, "Click on a color to remove it or press remove color again to cancel.", Toast.LENGTH_SHORT).show();
        });

        FramesBtn.setOnClickListener(v -> {
            findViewById(R.id.groupColors).setVisibility(View.GONE);
            findViewById(R.id.groupFrame).setVisibility(View.VISIBLE);
        });
        AddFrame.setOnClickListener(v -> {

            Toast.makeText(this, "Frame Added", Toast.LENGTH_SHORT).show();
        });
        RemoveFrame.setOnClickListener(v -> {

            Toast.makeText(this, "Frame Removed", Toast.LENGTH_SHORT).show();
        });


        fillWhite();
        colorAdapter = new ColorAdapter();

        colorsView = findViewById(R.id.colors);
        gridView = findViewById(R.id.gridView);
        colorsView.setAdapter(colorAdapter);
        gridView.setAdapter(imageAdapter);
        gridView.setOnTouchListener((v, event) -> {
            v.performClick();

            float fX = event.getX(),
                    fY = event.getY();
            int size = (gridView.getWidth()-1) / 9,
                    pos = (int) (Math.floor(fX / size) + (9 * Math.floor(fY / size)));

            if (pos < 0 || pos > pixels.length-1) return false;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP: {
                    setPixel(pos, selectedColor);
                    imageAdapter.notifyDataSetChanged();
                } break;
                default: break;
            }

            return false;
        });
    }

    public void setPixel(int pos, Pixel pixel) {
        pixels[pos] = pixel;
    }

    public Pixel getPixel(int pos) {
        return pixels[pos];
    }

    public void fillWhite() {
        for (int i = 0; i < PIXELS; i++) {
            pixels[i] = new Pixel(255, 255, 255);
        }
    }

    public ColorAdapter getColorAdapter() {
        return colorAdapter;
    }

    public GridView getColorsView() {
        return colorsView;
    }

    public GridView getGridView() {
        return gridView;
    }

    public void setSelectedColor(Pixel selectedColor) {
        this.selectedColor = selectedColor;
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public Pixel getPixelColor(int pos) {
        if (pos < 0 || pos >= colors.size()) return new Pixel(0, 0, 0);
        return colors.get(pos);
    }

    public void addPixelColor(Pixel pixel) {
        colors.add(pixel);
    }

    public void removePixelColor(int position) {
        colors.remove(position);
    }

    public int getPixelColors() {
        return colors.size();
    }

    public boolean isRemovingColor() {
        return removingColor;
    }

    public void setRemovingColor(boolean removingColor) {
        this.removingColor = removingColor;
    }

    public static Pixel fromHex(int hex) {
        int r = (hex & 0xFF0000) >> 16,
                g = (hex & 0xFF00) >> 8,
                b = (hex & 0xFF);

        return new Pixel(r, g, b);
    }
}