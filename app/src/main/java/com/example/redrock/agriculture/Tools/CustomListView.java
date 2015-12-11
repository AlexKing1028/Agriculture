package com.example.redrock.agriculture.Tools;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

import java.util.jar.Attributes;

/**
 * Created by REDROCK on 12/6/2015.
 */
public class CustomListView extends ListView{
    private GestureDetector gestureDetector;

    public CustomListView(Context context, AttributeSet attributeSet){
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
