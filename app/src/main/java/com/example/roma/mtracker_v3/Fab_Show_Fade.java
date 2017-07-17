package com.example.roma.mtracker_v3;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Roma on 12.07.2017.
 */

public class Fab_Show_Fade extends FloatingActionButton.Behavior {

    public Fab_Show_Fade(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.v("FABButton","onScroll");

    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.v("FABButton","onScroll");

        if (child.getVisibility() == View.VISIBLE && dyConsumed> 0) {
            child.hide();
        } else if (child.getVisibility() == View.GONE && dyConsumed < 0) {
            child.show();
        }

    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.v("FABButton","onScrollSTART");

        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL ||        super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);

    }


}
