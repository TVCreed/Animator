package com.example.animator;

import java.util.List;

public class FrameArray {
    private int framePos = 0;
    List<Frame> Frames;

    public void AddFrame(Frame f) {
        Frames.add(f);
    }

    public void RemoveFrame(Frame f) {
        Frames.remove(f);
    }

    public Frame getFrame(int pos) {
        return Frames.get(pos);
    }
}
