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
        ImageView pic = new ImageView(activity);

        Pixel pixel = MainActivity.getInstance().getPixelColor(position);

        activity.getResources().getDrawable(R.drawable.pixel).setTint(Color.argb(255, pixel.getR(), pixel.getG(), pixel.getB()));
        pic.setImageResource(R.drawable.pixel);

        pic.setOnClickListener(v -> {
            if (MainActivity.getInstance().isRemovingColor()) {
                MainActivity.getInstance().removePixelColor(position);
                MainActivity.getInstance().setRemovingColor(false);

                notifyDataSetChanged();

                ColorLayout layout;

                if (getCount() <= 1) layout = ColorLayout.ONE;
                else if (getCount() <= 2) layout = ColorLayout.TWO;
                else if (getCount() <= 4) layout = ColorLayout.FOUR;
                else layout = ColorLayout.EIGHT;

                layout.applyGrid(MainActivity.getInstance().getColorsView());
            } else {
                MainActivity.getInstance().setSelectedColor(pixel);
            }
            MainActivity.getInstance().getColorAdapter().notifyDataSetChanged();
        });

        ColorLayout layout;

        System.out.println("-------------");
        System.out.println("-------------");
        System.out.println("-------------");
        System.out.println(getCount());
        System.out.println("-------------");
        System.out.println("-------------");
        System.out.println("-------------");

        if (getCount() <= 1) layout = ColorLayout.ONE;
        else if (getCount() <= 2) layout = ColorLayout.TWO;
        else if (getCount() <= 4) layout = ColorLayout.FOUR;
        else if (getCount() <= 8) layout = ColorLayout.EIGHT;
        else layout = ColorLayout.EIGHT;

        layout.applyImage(MainActivity.getInstance().getColorsView(), pic);

        return pic;
    }
}
