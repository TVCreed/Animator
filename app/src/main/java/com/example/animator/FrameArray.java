package com.example.animator;

import java.util.ArrayList;
import java.util.List;

public class FrameArray {
    private int framePos = -1;
    private List<Frame> Frames = new ArrayList<>();

    public FrameArray()
    {

    }

    public void AddFrame(Frame f)
    {
        Frames.add(f);
    }

    public void RemoveFrame()
    {
        Frames.remove(framePos);
    }

    public Frame getFrame(int pos)
    {
        return Frames.get(pos);
    }

    public void setFrame(Pixel[] inPixels) {
        Frames.get(framePos).FillFrame(inPixels);
    }

    public int getPos()
    {
        return framePos;
    }

    public void setPos(int fp)
    {
        framePos = fp;
    }

    public boolean addPos()
    {
        if (Frames != null) {
            if (framePos < Frames.size()-1 && framePos >= 0) {
                framePos++;
                return true;
            }
        }
        return false;
    }

    public boolean subPos()
    {
        if (Frames != null) {
            if (framePos <= Frames.size()-1 && framePos > 0) {
                framePos--;
                return true;
            }
        }
        return false;
    }

    public int getSize()
    {
        return Frames.size();
    }
}
