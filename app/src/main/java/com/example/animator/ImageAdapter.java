package com.example.animator;

import android.graphics.Color;
import android.os.Build;
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
        return MainActivity.WIDTH*MainActivity.HEIGHT;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView pic = new ImageView(activity);

        activity.getResources().getDrawable(R.drawable.pixel).setTint(Color.argb(255, (position + 30) % 255, (position + 60)% 255, position % 255));

        pic.setImageResource(R.drawable.pixel);
        pic.setLayoutParams(new ViewGroup.LayoutParams(parent.getWidth() / MainActivity.WIDTH, (parent.getHeight() / MainActivity.HEIGHT) - 30));

        return pic;
    }
}
