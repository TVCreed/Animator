package com.example.animator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

public class ImageAdapter extends BaseAdapter {
    private final MainActivity activity;

    public ImageAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return MainActivity.PIXELS;
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
        ImageView pic = new ImageView(activity);
        Pixel pixel = MainActivity.getInstance().getPixel(position);
        activity.getResources().getDrawable(R.drawable.pixel).setTint(Color.argb(255, pixel.getR(), pixel.getG(), pixel.getB()));

        pic.setImageResource(R.drawable.pixel);
        int size = (parent.getWidth()-1) / 9;
        pic.setLayoutParams(new ViewGroup.LayoutParams(size, size));

        return pic;
    }
}
