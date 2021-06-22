package com.example.animator;

import static com.example.animator.MainActivity.PIXELS;

public class Frame {
    Pixel[] pixels = new Pixel[PIXELS];

    public void FillFrame(Pixel[] inPixels) {
        for (int i = 0; i < PIXELS; i++) {
            pixels[i].setR(inPixels[i].getR());
            pixels[i].setG(inPixels[i].getG());
            pixels[i].setB(inPixels[i].getB());
        }
    }
}
