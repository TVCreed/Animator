package com.example.animator;

import java.util.ArrayList;

public class FrameArray {
    ArrayList<Frame> Frames = new ArrayList<>(10);

    public void AddFrame(Frame f) {
        Frames.add(f);
    }

    public void RemoveFrame(Frame f) {
        Frames.remove(f);
    }
}
