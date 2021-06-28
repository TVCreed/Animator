package com.example.animator;

import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public enum ColorLayout {
    ONE() {
        @Override
        void applyImage(GridView parent, ImageView view) {
            view.setLayoutParams(new ConstraintLayout.LayoutParams(parent.getWidth(), parent.getHeight()));
        }

        @Override
        void applyGrid(GridView gridView) {
            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            gridView.setLayoutParams(new ConstraintLayout.LayoutParams(gridView.getHeight(), gridView.getHeight()));
            gridView.setNumColumns(1);
            set.constrainMaxWidth(gridView.getId(), gridView.getHeight());
            set.constrainMinWidth(gridView.getId(), gridView.getHeight());

            ColorLayout.applyLayoutConstraints(set, layout);
        }
    },
    TWO() {
        @Override
        void applyImage(GridView parent, ImageView view) {
            view.setLayoutParams(new ConstraintLayout.LayoutParams(parent.getWidth()/2, parent.getHeight()));
        }
        @Override
        void applyGrid(GridView gridView) {
            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            gridView.setLayoutParams(new ConstraintLayout.LayoutParams(gridView.getHeight() * 2, gridView.getHeight()));
            gridView.setNumColumns(2);
            set.constrainMaxWidth(gridView.getId(), gridView.getHeight()*2);
            set.constrainMinWidth(gridView.getId(), gridView.getHeight()*2);

            ColorLayout.applyLayoutConstraints(set, layout);
        }
    },
    FOUR() {
        @Override
        void applyImage(GridView parent, ImageView view) {
            view.setLayoutParams(new ConstraintLayout.LayoutParams(parent.getWidth()/2, parent.getHeight()/2));
        }
        @Override
        void applyGrid(GridView gridView) {
            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            gridView.setLayoutParams(new ConstraintLayout.LayoutParams(gridView.getHeight(), gridView.getHeight()));
            gridView.setNumColumns(2);
            set.constrainMaxWidth(gridView.getId(), gridView.getHeight());
            set.constrainMinWidth(gridView.getId(), gridView.getHeight());

            ColorLayout.applyLayoutConstraints(set, layout);
        }
    },
    EIGHT() {
        @Override
        void applyImage(GridView parent, ImageView view) {
            view.setLayoutParams(new ConstraintLayout.LayoutParams(parent.getWidth()/4, parent.getHeight()/2));
        }
        @Override
        void applyGrid(GridView gridView) {
            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            gridView.setLayoutParams(new ConstraintLayout.LayoutParams(gridView.getHeight() * 2, gridView.getHeight()));
            gridView.setNumColumns(4);
            set.constrainMinWidth(gridView.getId(), gridView.getHeight()*2);
            set.constrainMaxWidth(gridView.getId(), gridView.getHeight()*2);

            ColorLayout.applyLayoutConstraints(set, layout);

        }
    };

    ColorLayout() {

    }

    abstract void applyImage(GridView parent, ImageView view);
    abstract void applyGrid(GridView gridView);

    private static void applyLayoutConstraints(ConstraintSet set, ConstraintLayout layout) {
        set.connect(R.id.colors, ConstraintSet.LEFT, R.id.constraintlayout, ConstraintSet.LEFT);
        set.connect(R.id.colors, ConstraintSet.TOP, R.id.constraintlayout, ConstraintSet.TOP);
        set.connect(R.id.colors, ConstraintSet.BOTTOM, R.id.gridView, ConstraintSet.TOP);
        set.applyTo(layout);
    }
}
