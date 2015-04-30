package com.zarretail.zoney;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Layout;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.zarretail.zoney.view.ItemView;

import java.util.ArrayList;

/**
 * Created by David on 4/1/2015.
 */
public class QAActivity extends Activity implements GestureDetector.OnGestureListener {
    private int offsetX, tranX, width;
    private int index = 1;

    GestureDetector mGestureDetector;

    private ArrayList<ItemView> itemViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);

        ViewGroup layout = (ViewGroup)findViewById(R.id.layout_qa);
        getActionBar().hide();

        final String questions[] = new String[]{
                "What is your name?",
                "How old are you?",
                "To the Edge of the Earth",
        };

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = (int)((float)size.x / 3 * 4);
        offsetX = (int)((float)width / 8 * 5);
        tranX = width;

        itemViews = new ArrayList<>();

        for (int i = 0; i < questions.length; i++){
            ItemView item = new ItemView(this, questions[i], width, size.y);


            layout.addView(item);
            item.setX(offsetX + (i - 1) * width);
            itemViews.add(item);
        }
        mGestureDetector = new GestureDetector(this);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    private static final int SWIPE_MIN_DISTANCE = 40;
    private static final int SWIPE_THRESHOLD_VELOCITY = 80;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//            tv.setText("Left Swipe");
            TranslateAnimation animation = new TranslateAnimation(0, -tranX, 0, 0);
            animation.setFillAfter(true);

            animation.setDuration(500);
      //      animation.setAnimationListener(this);

            for (int i = 0; i < itemViews.size() - 1; i++){
                itemViews.get((index+i)%itemViews.size()).setX(offsetX + i * width);
            }
            itemViews.get((index+itemViews.size()-1)%itemViews.size()).setX(offsetX - width);

            for (int i = 0; i < itemViews.size(); i++) {
                itemViews.get(i).startAnimation(animation);
            }
            index = (index + 1) % itemViews.size();

            /*
            int temp = (index + 2) % itemViews.size();


            itemViews.get(index).setX(offsetX);
//            itemViews.get(index).setVisibility(View.VISIBLE);
            itemViews.get(index).startAnimation(animation);

            index = (index + 1) % itemViews.size();
            itemViews.get(index).setX(offsetX + width * 1);
//            itemViews.get(index).setVisibility(View.VISIBLE);
            itemViews.get(index).startAnimation(animation);
            */
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//            tv.setText("Right Swipe");
            TranslateAnimation animation = new TranslateAnimation(0, tranX, 0, 0);
            animation.setDuration(500);
            animation.setFillAfter(true);
       //     animation.setAnimationListener(this);

            for (int i = 0; i < itemViews.size() - 2; i++){
                itemViews.get((index+i)%itemViews.size()).setX(offsetX + i * width);
            }
            itemViews.get((index+itemViews.size()-1)%itemViews.size()).setX(offsetX - width);
            itemViews.get((index+itemViews.size()-2)%itemViews.size()).setX(offsetX - width * 2);

            for (int i = 0; i < itemViews.size(); i++) {
                itemViews.get(i).startAnimation(animation);
            }

            index = (index + itemViews.size() - 1) % itemViews.size();
/*
            itemViews.get(index).setX(offsetX);
//            itemViews.get(index).setVisibility(View.VISIBLE);
            itemViews.get(index).startAnimation(animation);

            index = index - 1;
            if (index < 0) index += itemViews.size();
            itemViews.get(index).setX(offsetX - width);
//            itemViews.get(index).setVisibility(View.VISIBLE);
            itemViews.get(index).startAnimation(animation);
            */
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}
