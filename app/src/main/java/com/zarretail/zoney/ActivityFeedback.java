package com.zarretail.zoney;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zarretail.zoney.libs.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by David on 4/15/2015.
 */
public class ActivityFeedback extends Activity {

    ProgressDialog ringDialog;
    String strFeedback;
    String strUserEmail;
    String strUserToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        getActionBar().hide();

        SharedPreferences settings = getSharedPreferences("mySetting", 0);
        strUserEmail = settings.getString("user_email","default@default.com");
        strUserToken = settings.getString("user_token", "");

        TextView txtToEmail = (TextView)findViewById(R.id.eTxtTo);
        txtToEmail.setText("feedback@zoney.com");
        TextView txtFromEmail = (TextView)findViewById(R.id.eTxtFrom);
        txtFromEmail.setText(strUserEmail);

        Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Button btnSend = (Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submitFeedback();
            }
        });
    }

    public void submitFeedback(){
        EditText eTxtFeedback = (EditText)findViewById(R.id.eTxtFeedback);
        strFeedback = eTxtFeedback.getText().toString();
        if (strFeedback.length()==0){
            Toast.makeText(getApplicationContext(), "Please input feedback", Toast.LENGTH_LONG).show();
            return;
        }

        AsyncTask<Void, Void, JSONObject> mSignUpTask = new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected void onPreExecute(){
                ringDialog = ProgressDialog.show(ActivityFeedback.this, "Please wait...", "Sending feedback...", true);
            }

            @Override
            protected JSONObject doInBackground(Void... params) {
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.sendFeedback(strFeedback, strUserToken);
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
                    Toast.makeText(getApplicationContext(), "JSON error.", Toast.LENGTH_LONG).show();
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
            e.printStackTrace();
        }

        if (result!= null){
            Intent intent = new Intent(ActivityFeedback.this, MySettingActivity.class);
            startActivity(intent);
            // close this activity
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Can't send feedback. Please try again later.", Toast.LENGTH_LONG).show();
        }
    }
}
