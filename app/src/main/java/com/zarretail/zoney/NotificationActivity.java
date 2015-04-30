package com.zarretail.zoney;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by David on 4/14/2015.
 */
public class NotificationActivity extends Activity {

    Boolean bValue01 = false;
    Boolean bValue02 = false;
    Boolean bValue03 = false;
    Boolean bValue04 = false;
    Boolean bValue05 = false;
    Boolean bValue06 = false;
    Boolean bValue07 = false;
    Boolean bValue08 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getActionBar().hide();

        getSavedData();

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final Button btnSet10 = (Button)findViewById(R.id.btnOnOff10);
        if(bValue01 == true){
            btnSet10.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet10.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue01==true){
                    btnSet10.setBackgroundResource(R.drawable.btn_off);
                    bValue01 = false;
                }else{
                    btnSet10.setBackgroundResource(R.drawable.btn_on);
                    bValue01 = true;
                }
                SaveData();
            }
        });

        final Button btnSet11 = (Button)findViewById(R.id.btnOnOff11);
        if(bValue02 == true){
            btnSet11.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet11.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue02==true){
                    btnSet11.setBackgroundResource(R.drawable.btn_off);
                    bValue02 = false;
                }else{
                    btnSet11.setBackgroundResource(R.drawable.btn_on);
                    bValue02 = true;
                }
                SaveData();
            }
        });

        final Button btnSet20 = (Button)findViewById(R.id.btnOnOff20);
        if(bValue03 == true){
            btnSet20.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet20.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet20.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue03==true){
                    btnSet20.setBackgroundResource(R.drawable.btn_off);
                    bValue03 = false;
                }else{
                    btnSet20.setBackgroundResource(R.drawable.btn_on);
                    bValue03 = true;
                }
                SaveData();
            }
        });

        final Button btnSet21 = (Button)findViewById(R.id.btnOnOff21);
        if(bValue04 == true){
            btnSet21.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet21.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet21.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue04==true){
                    btnSet21.setBackgroundResource(R.drawable.btn_off);
                    bValue04 = false;
                }else{
                    btnSet21.setBackgroundResource(R.drawable.btn_on);
                    bValue04 = true;
                }
                SaveData();
            }
        });

        final Button btnSet30 = (Button)findViewById(R.id.btnOnOff30);
        if(bValue05 == true){
            btnSet30.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet30.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet30.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue05==true){
                    btnSet30.setBackgroundResource(R.drawable.btn_off);
                    bValue05 = false;
                }else{
                    btnSet30.setBackgroundResource(R.drawable.btn_on);
                    bValue05 = true;
                }
                SaveData();
            }
        });

        final Button btnSet31 = (Button)findViewById(R.id.btnOnOff31);
        if(bValue06 == true){
            btnSet31.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet31.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet31.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue06==true){
                    btnSet31.setBackgroundResource(R.drawable.btn_off);
                    bValue06 = false;
                }else{
                    btnSet31.setBackgroundResource(R.drawable.btn_on);
                    bValue06 = true;
                }
                SaveData();
            }
        });

        final Button btnSet40 = (Button)findViewById(R.id.btnOnOff40);
        if(bValue07 == true){
            btnSet40.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet40.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet40.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue07==true){
                    btnSet40.setBackgroundResource(R.drawable.btn_off);
                    bValue07 = false;
                }else{
                    btnSet40.setBackgroundResource(R.drawable.btn_on);
                    bValue07 = true;
                }
                SaveData();
            }
        });

        final Button btnSet41 = (Button)findViewById(R.id.btnOnOff41);
        if(bValue08 == true){
            btnSet41.setBackgroundResource(R.drawable.btn_on);
        }else{
            btnSet41.setBackgroundResource(R.drawable.btn_off);
        }
        btnSet41.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(bValue08==true){
                    btnSet41.setBackgroundResource(R.drawable.btn_off);
                    bValue08 = false;
                }else{
                    btnSet41.setBackgroundResource(R.drawable.btn_on);
                    bValue08 = true;
                }
                SaveData();
            }
        });
    }

    void getSavedData(){
        SharedPreferences settings = getSharedPreferences("mySetting",0);

        bValue01 = settings.getBoolean("push1",false);
        bValue03 = settings.getBoolean("push2",false);
        bValue05= settings.getBoolean("push3",false);
        bValue07= settings.getBoolean("push4",false);

        bValue02= settings.getBoolean("email1",false);
        bValue04 = settings.getBoolean("email2",false);
        bValue06= settings.getBoolean("email3",false);
        bValue08= settings.getBoolean("email4",false);
    }

    void SaveData(){
        SharedPreferences settings = getSharedPreferences("mySetting",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("push1",bValue01);
        editor.putBoolean("push2",bValue03);
        editor.putBoolean("push3",bValue05);
        editor.putBoolean("push4",bValue07);
        editor.putBoolean("email1",bValue02);
        editor.putBoolean("email2",bValue04);
        editor.putBoolean("email3",bValue06);
        editor.putBoolean("email4",bValue08);
        editor.commit();
    }
}
