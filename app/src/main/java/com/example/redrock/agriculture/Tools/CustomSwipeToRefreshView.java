package com.example.redrock.agriculture.Tools;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by REDROCK on 12/6/2015.
 */
public class CustomSwipeToRefreshView extends SwipeRefreshLayout{
    private GestureDetector gestureDetector;

    public CustomSwipeToRefreshView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        gestureDetector=new GestureDetector(context, new YScrollDetector());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        return super.onInterceptTouchEvent(ev) && gestureDetector.onTouchEvent(ev);
    }

    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return (Math.abs(distanceY) > Math.abs(distanceX));
        }
    }

}
