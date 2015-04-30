package com.zarretail.zoney;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.identity.intents.AddressConstants;
import com.zarretail.zoney.libs.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by David on 3/26/2015.
 */
public class RegisterActivity extends Activity {
    private ListView mainListView;
    private ArrayList<Item> item_data;
    int nCount;
    String strPassword;

    ProgressDialog ringDialog;
    
//    public Item item_data[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getActionBar().hide();

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

        Typeface myTypeface2 = Typeface.createFromAsset(getAssets(),"fonts/NexaRegular.otf");
        txtQestion.setTypeface(myTypeface2);

        Button btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (nCount>=2){
                    if(nCount==2){
                        String strAns = eTxtAnswer.getText().toString();
                        strPassword = strAns;
                        Item sItem = new Item("********");
                        item_data.add(sItem);
                        listAdapter.notifyDataSetChanged();
                        txtQestion.setVisibility(View.GONE);
                        eTxtAnswer.setVisibility(View.GONE);
                        nCount++;
                    }
                    submitRegister();
                    return;
                }
                String strAns = eTxtAnswer.getText().toString();
                Item sItem = new Item(strAns);
                item_data.add(sItem);
                listAdapter.notifyDataSetChanged();
                eTxtAnswer.setText("");
                nCount++;
                txtQestion.setText(questions[nCount]);
                if(nCount==2){
                    eTxtAnswer.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void submitRegister(){

        final String userName = item_data.get(1).title;
        if (userName.length()==0){
            Toast.makeText(getApplicationContext(),"Please input your name.",Toast.LENGTH_LONG).show();
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
                ringDialog = ProgressDialog.show(RegisterActivity.this,"Please wait...","Signing up...",true);
            }

            @Override
            protected JSONObject doInBackground(Void... params) {
                UserFunctions userFunction = new UserFunctions();
                JSONObject json = userFunction.signUpUser(userName,userEmail,userPassword);
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
//            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        if (result!= null){
            Intent intent = new Intent(RegisterActivity.this, MyProfileActivity.class);
            startActivity(intent);
            // close this activity
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "JSON ERROR.", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
