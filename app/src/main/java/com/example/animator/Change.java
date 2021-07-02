package com.example.animator;

public class Change {
    private final int pos;
    private final Pixel from, to;

    public Change(int pos, Pixel from, Pixel to) {
        this.pos = pos;
        this.from = from;
        this.to = to;
    }

    public void redo() {
        MainActivity activity = MainActivity.getInstance();

        activity.setPixel(pos, to);
        activity.getImageAdapter().notifyDataSetChanged();
    }

    public void undo() {
        MainActivity activity = MainActivity.getInstance();

        activity.setPixel(pos, from);
        activity.getImageAdapter().notifyDataSetChanged();
    }

    public int getPos() {
        return pos;
    }

    public Pixel getFrom() {
        return from;
    }

    public Pixel getTo() {
        return to;
    }
}
