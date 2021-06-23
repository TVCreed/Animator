package com.example.animator;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

public class ColorAdapter extends BaseAdapter {
    private final MainActivity activity;

    public ColorAdapter(MainActivity activity) {
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

        Pixel pixel = MainActivity.getInstance().getPixelColor(position);

        activity.getResources().getDrawable(R.drawable.pixel).setTint(Color.argb(255, pixel.getR(), pixel.getG(), pixel.getB()));
        pic.setImageResource(R.drawable.pixel);
        pic.setLayoutParams(new ViewGroup.LayoutParams(
                parent.getHeight(),
                parent.getHeight()));

        pic.setOnClickListener(v -> {
            if (MainActivity.getInstance().isRemovingColor()) {
                MainActivity.getInstance().removePixelColor(position);
                MainActivity.getInstance().setRemovingColor(false);
            } else {
                MainActivity.getInstance().setSelectedColor(pixel);
            }
            MainActivity.getInstance().getColorAdapter().notifyDataSetChanged();
        });

        return pic;
    }
}
