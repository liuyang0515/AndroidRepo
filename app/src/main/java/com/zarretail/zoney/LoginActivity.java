package com.zarretail.zoney;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zarretail.zoney.libs.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

/**
 * Created by David on 3/31/2015.
 */
public class LoginActivity extends Activity{

    private ListView mainListView;
    private ArrayList<Item> item_data;
    int nCount;
    String strPassword;
    String strUserEmail;
    String strUserName;

    TextView txtQestion;
    EditText eTxtAnswer;
    Button btnLoginSubmit;

    ProgressDialog ringDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_login);
        getActionBar().hide();

        item_data = new ArrayList<Item>();
        Item sItem = new Item("");
        item_data.add(sItem);
        nCount = 0;

        final String questions[] = new String[]{
                "What is your email address?",
                "What is your password?",
        };

        final Button btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mainListView = (ListView) findViewById(R.id.main_listView);
        final ListAdapter listAdapter = new ListAdapter(this,R.layout.list_adapter,item_data);
        mainListView.setAdapter(listAdapter);

        txtQestion = (TextView)findViewById(R.id.txtQuestion);
        eTxtAnswer = (EditText)findViewById(R.id.eTxAnswer);

        Typeface myTypeface2 = Typeface.createFromAsset(getAssets(),"fonts/NexaRegular.otf");
        txtQestion.setTypeface(myTypeface2);

        final Button btnLogin = (Button)findViewById(R.id.btnLoginBt);
        btnLogin.setVisibility(View.GONE);

        eTxtAnswer.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == keyEvent.KEYCODE_ENTER)){
                    if (nCount>=1){
                        if(nCount==1){
                            String strAns = eTxtAnswer.getText().toString();
                            strPassword = strAns;
                            Item sItem = new Item("********");
                            item_data.add(sItem);
                            listAdapter.notifyDataSetChanged();
                            txtQestion.setVisibility(View.GONE);
                            eTxtAnswer.setVisibility(View.GONE);
                            nCount++;
                            btnLogin.setVisibility(View.VISIBLE);
                        }
                        return true;
                    }
                    String strAns = eTxtAnswer.getText().toString();
                    Item sItem = new Item(strAns);
                    item_data.add(sItem);
                    listAdapter.notifyDataSetChanged();
                    eTxtAnswer.setText("");
                    nCount++;
                    txtQestion.setText(questions[nCount]);
                    if(nCount==1){
                        eTxtAnswer.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    }
                    return true;
                }
                return false;
            }
        });

        btnLoginSubmit = (Button)findViewById(R.id.btnLoginBt);
        btnLoginSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                submitLogin();
            }
        });
    }

    public void submitLogin(){


        strUserEmail = item_data.get(1).title;
        if (strUserEmail.length()==0){
            Toast.makeText(getApplicationContext(),"Please input your email.",Toast.LENGTH_LONG).show();
            return;
        }

        final String userPassword = strPassword;//item_data.get(2).title;
        if (userPassword.length()==0){
            Toast.makeText(getApplicationContext(),"Please input your password.",Toast.LENGTH_LONG).show();
            return;
        }


        AsyncTask<Void, Void, JSONObject> mSignUpTask = new AsyncTask<Void, Void, JSONObject>() {

            @Override
            protected void onPreExecute(){
                ringDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Loging in...", true);
            }

            @Override
            protected JSONObject doInBackground(Void... params) {
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.loginUser(strUserEmail,userPassword,null);
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
            try {
                strUserName = result.getString("name");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            SharedPreferences settings = getSharedPreferences("mySetting",0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("user_email",strUserEmail);
            editor.putString("user_name",strUserName);
            editor.commit();

            Intent intent = new Intent(LoginActivity.this, MyProfileActivity.class);
            startActivity(intent);
            // close this activity
            finish();
        }else{
            //
            item_data.remove(nCount);
            txtQestion.setVisibility(View.VISIBLE);
            eTxtAnswer.setVisibility(View.VISIBLE);
            btnLoginSubmit.setVisibility(View.GONE);
            nCount--;
            Toast.makeText(getApplicationContext(), "Can't login with your user information. please check your user account and password.", Toast.LENGTH_LONG).show();
        }
    }
}
