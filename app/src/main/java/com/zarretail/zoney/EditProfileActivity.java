package com.zarretail.zoney;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 4/6/2015.
 */
public class EditProfileActivity  extends Activity {
    private ListView mainListView;
    private ArrayList<Item> item_data;
    int nCount;

    String strUserName;
    String strUserEmail;

    //    public Item item_data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editiprofile);
        getActionBar().hide();

        SharedPreferences settings = getSharedPreferences("mySetting",0);
        strUserName = settings.getString("user_name","default");
        strUserEmail = settings.getString("user_email","default@default.com");

        item_data = new ArrayList<Item>();
        Item sItem = new Item("");
        item_data.add(sItem);
        nCount = 0;
        final String questions[] = new String[]{
                "What is your name?",
                "What is your email address?",
                "What is your password?",
        };

        Typeface myTypefaces = Typeface.createFromAsset(getAssets(),"fonts/choplinmedium.otf");
        TextView txtNavTitle = (TextView)findViewById(R.id.txtNavTitle);
        txtNavTitle.setTypeface(myTypefaces);
        txtNavTitle.setTypeface(null, Typeface.BOLD);

        mainListView = (ListView) findViewById(R.id.main_listView);
        final ListAdapter listAdapter = new ListAdapter(this,R.layout.list_adapter,item_data);
        mainListView.setAdapter(listAdapter);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final TextView txtQestion = (TextView)findViewById(R.id.txtQuestion);
        final EditText eTxtAnswer = (EditText)findViewById(R.id.eTxAnswer);
        eTxtAnswer.setText(strUserName);
        eTxtAnswer.setFocusable(true);

        Typeface myTypeface2 = Typeface.createFromAsset(getAssets(),"fonts/NexaRegular.otf");
        txtQestion.setTypeface(myTypeface2);

        final Button btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (nCount>=2){
                    if(nCount==2){
                        String strAns = eTxtAnswer.getText().toString();
                        Item sItem = new Item("********");
                        item_data.add(sItem);
                        listAdapter.notifyDataSetChanged();
                        txtQestion.setVisibility(View.GONE);
                        eTxtAnswer.setVisibility(View.GONE);
                        nCount++;
                    }
                    btnNext.setVisibility(View.GONE);
                    return;
                }
                String strAns = eTxtAnswer.getText().toString();
                Item sItem = new Item(strAns);
                item_data.add(sItem);
                listAdapter.notifyDataSetChanged();
                eTxtAnswer.setText(strUserEmail);
                nCount++;
                txtQestion.setText(questions[nCount]);
                if(nCount==2){
                    eTxtAnswer.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }
}
