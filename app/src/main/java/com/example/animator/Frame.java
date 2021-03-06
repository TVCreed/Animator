package com.example.animator;

import java.util.ArrayList;
import java.util.List;

import static com.example.animator.MainActivity.PIXELS;

public class Frame {
    private Pixel[] pixels = new Pixel[PIXELS];
    public List<Pixel[]> changeHistory = new ArrayList<>();
    private int historyPos = 0;

    public Frame(Pixel[] inPixels)
    {
        FillFrame(inPixels);
    }

    public void FillFrame(Pixel[] inPixels)
    {
        System.arraycopy(inPixels, 0, pixels, 0, PIXELS);
    }

    public Pixel[] getPixels()
    {
        return pixels;
    }
}
