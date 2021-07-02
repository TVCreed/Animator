package com.example.animator;

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
            // Retrieve constraint layout and copy the constraint set from it

            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            // Set the width of the GridView to height,
            // so as to fit 1 element per row when the height is equal to width
            // with 1 column.

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
            // Retrieve constraint layout and copy the constraint set from it

            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            // Set the width of the GridView to 2*height,
            // so as to fit 2 elements per row when the height is width/2
            // with 2 columns.

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
            // Retrieve constraint layout and copy the constraint set from it

            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            // Set the width of the GridView to height,
            // so as to fit 2 elements per row when the height is width/2
            // with 2 columns.

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
            // Retrieve constraint layout and copy the constraint set from it

            ConstraintSet set = new ConstraintSet();
            ConstraintLayout layout = (ConstraintLayout) MainActivity.getInstance().findViewById(R.id.constraintlayout);
            set.clone(layout);

            // Set the width of the GridView to 2*height,
            // so as to fit 8 elements per row when the height is width/4
            // with 4 columns.

            gridView.setLayoutParams(new ConstraintLayout.LayoutParams(gridView.getHeight() * 2, gridView.getHeight()));
            gridView.setNumColumns(4);
            set.constrainMinWidth(gridView.getId(), gridView.getHeight()*2);
            set.constrainMaxWidth(gridView.getId(), gridView.getHeight()*2);

            // Apply predefined constraints to the color palette's GridView

            ColorLayout.applyLayoutConstraints(set, layout);
        }
    };

    /*************************************
     *                                   *
     * ColorLayout                       *
     * Used to format color palette grid *
     *                                   *
     *************************************/

    ColorLayout() {

    }



    abstract void applyImage(GridView parent, ImageView view);
    abstract void applyGrid(GridView gridView);

    private static void applyLayoutConstraints(ConstraintSet set, ConstraintLayout layout) {
        // Attach left of palette to left of screen
        // Attach top of palette to top of screen
        // Attach bottom of palette to top of frame's GridView
        set.connect(R.id.viewGridPalette, ConstraintSet.LEFT, R.id.constraintlayout, ConstraintSet.LEFT);
        set.connect(R.id.viewGridPalette, ConstraintSet.TOP, R.id.constraintlayout, ConstraintSet.TOP);
        set.connect(R.id.viewGridPalette, ConstraintSet.BOTTOM, R.id.viewGridPixels, ConstraintSet.TOP);

        // Overwrite current layout constraints
        set.applyTo(layout);
    }
}
