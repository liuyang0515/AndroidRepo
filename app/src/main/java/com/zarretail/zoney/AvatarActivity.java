package com.zarretail.zoney;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.view.ViewGroup.LayoutParams;

import java.nio.InvalidMarkException;


/**
 * Created by David on 4/1/2015.
 */
public class AvatarActivity extends Activity implements OnGestureListener, View.OnTouchListener {
    GestureDetector gd;
    ScrollView sv;
    ViewFlipper vf;
    TextView tv;

    int nSelected;

    ImageView imgAvatar1;
    ImageView imgAvatar2;
    ImageView imgAvatar3;
    ImageView imgAvatar4;
    ImageView imgAvatar5;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        getActionBar().hide();
        getSavedData();

        tv = (TextView)findViewById(R.id.txtNavTitle);
        gd = new GestureDetector(this);
        sv = (ScrollView)findViewById(R.id.bgScroll);
        vf = (ViewFlipper)findViewById(R.id.viewFlipper1);

        LayoutParams lParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        View v1 = new View(this);
        v1.setLayoutParams(lParam);
        v1.setBackgroundResource(R.drawable.background1);

        View v2 = new View(this);
        v2.setLayoutParams(lParam);
        v2.setBackgroundResource(R.drawable.background2);

        View v3 = new View(this);
        v3.setLayoutParams(lParam);
        v3.setBackgroundResource(R.drawable.background3);

        View v4 = new View(this);
        v4.setLayoutParams(lParam);
        v4.setBackgroundResource(R.drawable.background4);

        View v5 = new View(this);
        v5.setLayoutParams(lParam);
        v5.setBackgroundResource(R.drawable.background5);

        vf.addView(v1);
        vf.addView(v2);
        vf.addView(v3);
        vf.addView(v4);
        vf.addView(v5);


        vf.setDisplayedChild(0);
        sv.setOnTouchListener(this);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        imgAvatar1 = (ImageView)findViewById(R.id.imgAvatar1);
        if(nSelected == 1){
            imgAvatar1.setImageResource(R.drawable.avatar1_sel);
        }else{
            imgAvatar1.setImageResource(R.drawable.avatar1_def);
        }
        imgAvatar1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setImages(1);
            }
        });

        imgAvatar2 = (ImageView)findViewById(R.id.imgAvatar2);
        if(nSelected == 2){
            imgAvatar2.setImageResource(R.drawable.avatar2_sel);
        }else{
            imgAvatar2.setImageResource(R.drawable.avatar2_def);
        }
        imgAvatar2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setImages(2);
            }
        });

        imgAvatar3 = (ImageView)findViewById(R.id.imgAvatar3);
        if(nSelected == 3){
            imgAvatar3.setImageResource(R.drawable.avatar3_sel);
        }else{
            imgAvatar3.setImageResource(R.drawable.avatar3_def);
        }
        imgAvatar3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setImages(3);
            }
        });

        imgAvatar4 = (ImageView)findViewById(R.id.imgAvatar4);
        if(nSelected == 4){
            imgAvatar4.setImageResource(R.drawable.avatar4_sel);
        }else{
            imgAvatar4.setImageResource(R.drawable.avatar4_def);
        }
        imgAvatar4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setImages(4);
            }
        });

        imgAvatar5 = (ImageView)findViewById(R.id.imgAvatar5);
        if(nSelected == 5){
            imgAvatar5.setImageResource(R.drawable.avatar5_sel);
        }else{
            imgAvatar5.setImageResource(R.drawable.avatar5_def);
        }
        imgAvatar5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setImages(5);
            }
        });

        Button btnSaveImage = (Button)findViewById(R.id.btnSetImage);
        btnSaveImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SaveData();
                Intent intent = new Intent();
                setResult(2,intent);
                finish();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent me)
    {
        return gd.onTouchEvent(me);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
//        tv.setText("down");
        return false;
    }

    private static final int SWIPE_MIN_DISTANCE = 40;
    private static final int SWIPE_THRESHOLD_VELOCITY = 80;

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//            tv.setText("Left Swipe");

            vf.setInAnimation(this, R.anim.push_right_in);
            vf.setOutAnimation(this, R.anim.push_left_out);
            vf.showNext();

        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
//            tv.setText("Right Swipe");

            vf.setInAnimation(this, R.anim.push_left_in);
            vf.setOutAnimation(this, R.anim.push_right_out);
            vf.showPrevious();

        }

        return false;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
//        tv.setText("long press");

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
//        tv.setText("scroll");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
//        tv.setText("show press");
    }


    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
//        tv.setText("single tab up");
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        onTouchEvent(event);

        return false;
    }

    void SaveData() {
        SharedPreferences settings = getSharedPreferences("mySetting",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("avarta_index", nSelected);
        editor.commit();
    }

    void setImages(int nIndex){
        if(nIndex == 1){
            imgAvatar1.setImageResource(R.drawable.avatar1_sel);
            imgAvatar2.setImageResource(R.drawable.avatar2_def);
            imgAvatar3.setImageResource(R.drawable.avatar3_def);
            imgAvatar4.setImageResource(R.drawable.avatar4_def);
            imgAvatar5.setImageResource(R.drawable.avatar5_def);
            nSelected = 1;
        }else if(nIndex ==2){
            imgAvatar1.setImageResource(R.drawable.avatar1_def);
            imgAvatar2.setImageResource(R.drawable.avatar2_sel);
            imgAvatar3.setImageResource(R.drawable.avatar3_def);
            imgAvatar4.setImageResource(R.drawable.avatar4_def);
            imgAvatar5.setImageResource(R.drawable.avatar5_def);
        }
        else if(nIndex ==3){
            imgAvatar1.setImageResource(R.drawable.avatar1_def);
            imgAvatar2.setImageResource(R.drawable.avatar2_def);
            imgAvatar3.setImageResource(R.drawable.avatar3_sel);
            imgAvatar4.setImageResource(R.drawable.avatar4_def);
            imgAvatar5.setImageResource(R.drawable.avatar5_def);
        }
        else if(nIndex ==4){
            imgAvatar1.setImageResource(R.drawable.avatar1_def);
            imgAvatar2.setImageResource(R.drawable.avatar2_def);
            imgAvatar3.setImageResource(R.drawable.avatar3_def);
            imgAvatar4.setImageResource(R.drawable.avatar4_sel);
            imgAvatar5.setImageResource(R.drawable.avatar5_def);
        }
        else if(nIndex ==5){
            imgAvatar1.setImageResource(R.drawable.avatar1_def);
            imgAvatar2.setImageResource(R.drawable.avatar2_def);
            imgAvatar3.setImageResource(R.drawable.avatar3_def);
            imgAvatar4.setImageResource(R.drawable.avatar4_def);
            imgAvatar5.setImageResource(R.drawable.avatar5_sel);
        }
        nSelected = nIndex;
    }

    void getSavedData(){
        SharedPreferences settings = getSharedPreferences("mySetting",0);
        nSelected = settings.getInt("avarta_index", 1);
    }
}