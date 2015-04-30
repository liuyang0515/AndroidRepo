package com.zarretail.zoney;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by David on 4/7/2015.
 */
public class MySettingActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_mysettings);
        getActionBar().hide();

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button btnTerms = (Button)findViewById(R.id.btnTermService);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(),"fonts/NexaHeavy.otf");
        btnTerms.setTypeface(myTypeface);
        btnTerms.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MySettingActivity.this, TermsActivity.class);
                startActivity(i);
            }
        });

        Button btnPrivacy = (Button)findViewById(R.id.btnPrivacyPilicy);
        myTypeface = Typeface.createFromAsset(getAssets(),"fonts/NexaHeavy.otf");
        btnPrivacy.setTypeface(myTypeface);
        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MySettingActivity.this, PrivacyAcitivy.class);
                startActivity(i);
            }
        });

        Button btnNotification = (Button)findViewById(R.id.btnNotifications);
        myTypeface = Typeface.createFromAsset(getAssets(),"fonts/NexaHeavy.otf");
        btnNotification.setTypeface(myTypeface);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MySettingActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });

        Button btnFeedback = (Button)findViewById(R.id.btnLeaveFeedback);
        myTypeface = Typeface.createFromAsset(getAssets(),"fonts/NexaHeavy.otf");
        btnFeedback.setTypeface(myTypeface);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MySettingActivity.this, ActivityFeedback.class);
                startActivity(i);
            }
        });


        Button btnInvite = (Button)findViewById(R.id.btnInviteTwitter);
        myTypeface = Typeface.createFromAsset(getAssets(),"fonts/NexaHeavy.otf");
        btnInvite.setTypeface(myTypeface);

        Button btnLogOut = (Button)findViewById(R.id.btnLogOut);
        myTypeface = Typeface.createFromAsset(getAssets(),"fonts/NexaHeavy.otf");
        btnLogOut.setTypeface(myTypeface);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MySettingActivity.this, MyActivity.class);
                startActivity(i);
                finish();
            }
        });


        TextView txtNavTitle = (TextView)findViewById(R.id.txtNavTitle);
        myTypeface = Typeface.createFromAsset(getAssets(),"fonts/choplinmedium.otf");
        txtNavTitle.setTypeface(myTypeface);
    }
}
