package com.zarretail.zoney.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zarretail.zoney.AvatarActivity;
import com.zarretail.zoney.EditProfileActivity;
import com.zarretail.zoney.MyProfileActivity;
import com.zarretail.zoney.MySettingActivity;
import com.zarretail.zoney.QAActivity;
import com.zarretail.zoney.R;

/**
 * Created by David on 4/7/2015.
 */
public class ProfileMainFragment extends Fragment {
    int nSelectedAvatar;
    ImageView imgAvatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences settings = getActivity().getSharedPreferences("mySetting",0);
        String strUserName = settings.getString("user_name","default");
        String strUserEmail = settings.getString("user_email","default@default.com");
        nSelectedAvatar = settings.getInt("avarta_index",1);

        View v = inflater.inflate(R.layout.activity_myprofile_main, container, false);

        Button btnAvatar = (Button)v.findViewById(R.id.btnProfileImage);
        btnAvatar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AvatarActivity.class);
                startActivityForResult(i, 2);
            }
        });
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/NexaHeavy.otf");//(,"fonts/NexaHeavy.otf");
        btnAvatar.setTypeface(myTypeface);

        Button btnQA = (Button)v.findViewById(R.id.btnQA);
        btnQA.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QAActivity.class);
                startActivity(i);
            }
        });
        myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NexaHeavy.otf");
        btnQA.setTypeface(myTypeface);

        Button btnEditMine = (Button)v.findViewById(R.id.btnUserInfo);
        btnEditMine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(i);
            }
        });
        myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NexaHeavy.otf");
        btnEditMine.setTypeface(myTypeface);

        Button btnMySettings = (Button)v.findViewById(R.id.btnSetting);
        btnMySettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MySettingActivity.class);
                startActivity(i);
            }
        });
        myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NexaHeavy.otf");
        btnMySettings.setTypeface(myTypeface);

        TextView txtUserName = (TextView)v.findViewById(R.id.txtUserName);
        myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/choplinmedium.otf");
        txtUserName.setTypeface(myTypeface);
        txtUserName.setText(strUserName);

        TextView txtUserEmail = (TextView)v.findViewById(R.id.txtUserEmail);
        myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NexaRegular.otf");
        txtUserEmail.setTypeface(myTypeface);
        txtUserEmail.setText(strUserEmail);

        TextView txtMeetCount = (TextView)v.findViewById(R.id.txtMeetCount);
        TextView txtMeet1 = (TextView)v.findViewById(R.id.lblMeet1);
        TextView txtMeet2 = (TextView)v.findViewById(R.id.lblMeet2);
        TextView txtThmbCount = (TextView)v.findViewById(R.id.txtThumbCount);
        TextView txtThmb1 = (TextView)v.findViewById(R.id.lblThumb1);
        TextView txtThmb2 = (TextView)v.findViewById(R.id.lblThumb2);

        myTypeface = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NexaHeavy.otf");
        txtMeetCount.setTypeface(myTypeface);
        txtMeet1.setTypeface(myTypeface);
        txtMeet2.setTypeface(myTypeface);
        txtThmbCount.setTypeface(myTypeface);
        txtThmb1.setTypeface(myTypeface);
        txtThmb2.setTypeface(myTypeface);

        imgAvatar = (ImageView)v.findViewById(R.id.imgAvatar);
        if (nSelectedAvatar==1){
            imgAvatar.setImageResource(R.drawable.avatar1_sel);
        }else if (nSelectedAvatar ==2){
            imgAvatar.setImageResource(R.drawable.avatar2_sel);
        }else if (nSelectedAvatar ==3){
            imgAvatar.setImageResource(R.drawable.avatar3_sel);
        }else if (nSelectedAvatar ==4){
            imgAvatar.setImageResource(R.drawable.avatar4_sel);
        }else if (nSelectedAvatar ==5){
            imgAvatar.setImageResource(R.drawable.avatar5_sel);
        }
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==2){
            SharedPreferences settings = getActivity().getSharedPreferences("mySetting",0);
            nSelectedAvatar = settings.getInt("avarta_index",1);

            if (nSelectedAvatar==1){
                imgAvatar.setImageResource(R.drawable.avatar1_sel);
            }else if (nSelectedAvatar ==2){
                imgAvatar.setImageResource(R.drawable.avatar2_sel);
            }else if (nSelectedAvatar ==3){
                imgAvatar.setImageResource(R.drawable.avatar3_sel);
            }else if (nSelectedAvatar ==4){
                imgAvatar.setImageResource(R.drawable.avatar4_sel);
            }else if (nSelectedAvatar ==5){
                imgAvatar.setImageResource(R.drawable.avatar5_sel);
            }
        }
    }
}
