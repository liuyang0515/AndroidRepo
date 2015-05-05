package com.zarretail.zoney;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zarretail.zoney.libs.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

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
    String strPassword;
    String strUserToken;
    ProgressDialog ringDialog;

    //    public Item item_data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editiprofile);
        getActionBar().hide();

        SharedPreferences settings = getSharedPreferences("mySetting",0);
        strUserName = settings.getString("user_name","default");
        strUserEmail = settings.getString("user_email","default@default.com");
        strUserToken = settings.getString("user_token","");
        strPassword = settings.getString("user_pass","");

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
//                    btnNext.setVisibility(View.GONE);
                    submitUpdate();
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

    public void submitUpdate(){

        final String userName = item_data.get(1).title;
        if (userName.length()==0){
            Toast.makeText(getApplicationContext(), "Please input your name.", Toast.LENGTH_LONG).show();
            return;
        }

        final String userEmail = item_data.get(2).title;
        if (userEmail.length()==0){
            Toast.makeText(getApplicationContext(),"Please input your email.",Toast.LENGTH_LONG).show();
            return;
        }

        final String userPassword = strPassword; //item_data.get(3).title;
        if (userPassword.length()==0){
            Toast.makeText(getApplicationContext(),"Please input your password.",Toast.LENGTH_LONG).show();
            return;
        }


        AsyncTask<Void, Void, JSONObject> mSignUpTask = new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected void onPreExecute(){
                ringDialog = ProgressDialog.show(EditProfileActivity.this, "Please wait...", "Signing up...", true);
            }

            @Override
            protected JSONObject doInBackground(Void... params) {
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.updateUser(strUserToken,userName,userEmail,userPassword);
                // check for login response
                if(json!=null){
                    return json;
                }
                return null;
            }

            @Override
            protected void onPostExecute(JSONObject result) {
                if(result!=null) {
                    getResult(result);
                }else{
                    Toast.makeText(getApplicationContext(), "Can't update your user information..", Toast.LENGTH_LONG).show();
                }
                ringDialog.dismiss();
            }
        };
        // execute AsyncTask
        mSignUpTask.execute(null, null, null);
    }

    void getResult(JSONObject jResult){
        JSONObject result = null;
        String errorCode = "";
        try {
            result = jResult.getJSONObject("user");
//            errorCode = result.getString("error");
//            Toast.makeText(getApplicationContext(), errorCode, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
//            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        if (result!= null){
            Intent intent = new Intent(EditProfileActivity.this, MyProfileActivity.class);
            startActivity(intent);
            // close this activity
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "JSON ERROR.", Toast.LENGTH_LONG).show();
        }
    }
}
