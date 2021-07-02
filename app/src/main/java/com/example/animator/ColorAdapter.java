package com.example.animator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

public class ColorAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return MainActivity.getInstance().getPixelColors();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainActivity activity = MainActivity.getInstance();
        ImageView pixelView = new ImageView(activity);
        Pixel pixel = activity.getPixelColor(position);
        int pixelDrawableId = R.drawable.pixel;

        activity.getResources().getDrawable(pixelDrawableId)
                .setTint(Color.argb(255, pixel.getR(), pixel.getG(), pixel.getB()));
        pixelView.setImageResource(pixelDrawableId);

        pixelView.setOnClickListener(v -> {
            if (activity.isRemovingColor()) {
                activity.removePixelColor(position);

                applyGrid(getCount(), activity.getColorsView());
            } else activity.setSelectedColor(pixel);

            notifyDataSetChanged();
        });

        applyPixel(getCount(), activity.getColorsView(), pixelView);

        return pixelView;
    }

    public static void applyGrid(int count, GridView colorsView) {
        ColorLayout layout;

        // Find optimal layout for color palette

        if (count <= 1) layout = ColorLayout.ONE;
        else if (count <= 2) layout = ColorLayout.TWO;
        else if (count <= 4) layout = ColorLayout.FOUR;
        else if (count <= 8) layout = ColorLayout.EIGHT;
        else layout = ColorLayout.EIGHT;

        // Apply layout to color palette
        layout.applyGrid(colorsView);
    }

    public static void applyPixel(int count, GridView colorsView, ImageView pixelView) {
        ColorLayout layout;

        // Find optimal layout for color in the layout
        if (count <= 1) layout = ColorLayout.ONE;
        else if (count <= 2) layout = ColorLayout.TWO;
        else if (count <= 4) layout = ColorLayout.FOUR;
        else if (count <= 8) layout = ColorLayout.EIGHT;
        else layout = ColorLayout.EIGHT;

        // Apply layout to color in the layout
        layout.applyImage(colorsView, pixelView);
    }
}
