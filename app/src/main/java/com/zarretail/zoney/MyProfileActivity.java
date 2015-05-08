package com.zarretail.zoney;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zarretail.zoney.fragment.ProfileChatFragment;
import com.zarretail.zoney.fragment.ProfileMainFragment;
import com.zarretail.zoney.fragment.ProfileMapFragment;

/**
 * Created by David on 4/1/2015.
 */
public class MyProfileActivity extends Activity {
    Fragment fragmentMain, fragmentMap, fragmentChat;

    Button btnProfileTab;
    Button btnMapTab;
    Button btnChatTab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        getActionBar().hide();

        btnProfileTab = (Button)findViewById(R.id.btnProfile);
        btnMapTab = (Button)findViewById(R.id.btnMap);
        btnChatTab = (Button)findViewById(R.id.btnChat);

        fragmentMain = new ProfileMainFragment();
        fragmentMap = new ProfileMapFragment();
        fragmentChat = new ProfileChatFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentMain).commit();

        btnProfileTab.setBackgroundResource(R.drawable.rectangle_icon_white);

        btnProfileTab.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                btnProfileTab.setBackgroundResource(R.drawable.rectangle_icon_white);
                btnChatTab.setBackgroundResource(R.drawable.shape_icon);
                btnMapTab.setBackgroundResource(R.drawable.maps_icon);

                 getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentMain).commit();
             }
         });

        btnMapTab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnProfileTab.setBackgroundResource(R.drawable.rectangle_icon);
                btnChatTab.setBackgroundResource(R.drawable.shape_icon);
                btnMapTab.setBackgroundResource(R.drawable.maps_icon_white);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentMap).commit();
            }
        });

        btnChatTab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnProfileTab.setBackgroundResource(R.drawable.rectangle_icon);
                btnChatTab.setBackgroundResource(R.drawable.shape_icon_white);
                btnMapTab.setBackgroundResource(R.drawable.maps_icon);

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentChat).commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
