package com.example.animator;

import java.util.List;

public class FrameArray {
    private int framePos = 0;
    private List<Frame> Frames;

    public void AddFrame(Frame f) {
        Frames.add(f);
    }

    public void RemoveFrame(Frame f) {
        Frames.remove(f);
    }

    public Frame getFrame(int pos) {
        return Frames.get(pos);
    }

    public int getPos() {
        return framePos;
    }

    public void addPos() {
        framePos++;
    }

    public void subPos() {
        framePos--;
    }
}
