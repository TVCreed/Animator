package com.example.animator;

import static com.example.animator.MainActivity.PIXELS;

public class Frame {
    private Pixel[] pixels = new Pixel[PIXELS];

    public Frame() {

    }

    public Frame(Pixel[] inPixels) {
        FillFrame(inPixels);
    }

    public void FillFrame(Pixel[] inPixels) {
        System.arraycopy(inPixels, 0, pixels, 0, PIXELS);
    }

    public Pixel[] getPixels() {
        return pixels;
    }
}
