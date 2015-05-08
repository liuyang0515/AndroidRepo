/**
 * Author: wozheguo
 * date:2/3/2014
 * */
package com.zarretail.zoney.libs;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserFunctions {

	private JSONParser jsonParser;

	private static String getImagesURL;
    private static String getAnnounceURL;
    private static String getTransFirstURL;
    private static String getLocalStoreURL;
    private static String getImportantURL;
    private static String getContactURL;
    private static String traxitURL;

    private static String RegUserURL;
    private static String LoginUserURL;
    private static String UpdatePssURL;
    private static String SendFeedbackURL;


    // constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
//		getImagesURL = Config.SouthLantGetImagesURL;
        getAnnounceURL = "http://pax-port.com//southlantau/anouncements/getdata.php";
        getTransFirstURL = "http://pax-port.com//southlantau/transport/getroutelist1.php";
        getLocalStoreURL = "http://pax-port.com/southlantau/localbusiness/getlist1.php";
        getImportantURL = "http://pax-port.com//southlantau/important/getdata.php";
        getContactURL = "http://pax-port.com//southlantau/contact/getdata.php";

//        RegUserURL = "http://freeweightlossministry.com/api?method=addSubscriber";

        RegUserURL = "https://zoney-staging.herokuapp.com/api/sign_up";
        LoginUserURL = "https://zoney-staging.herokuapp.com/api/sign_in";
        UpdatePssURL = "https://zoney-staging.herokuapp.com/api/profile";
        SendFeedbackURL = "https://zoney-staging.herokuapp.com/api/feedback";

	}

    /**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password, String token){
		// Building Parameters
		//NSDictionary * parameters = @{@"tag":@"login", @"email":email, @"password":password, @"token":token};
        Log.e("SignUp", "email:" + email);

//        String email = "asdff@a.com";
//        String name = "name1";
//        String password = "123456";

        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("email", email);
            jsonObj.put("password", password);
        } catch (Exception e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user", jsonObj.toString()));

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("user", jsonObj);
        } catch (JSONException e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }

        JSONObject json = jsonParser.getJSONFromUrlAndJSON(LoginUserURL,jsonRequest);
        return  json;
	}
    /**
     * function make getImages Request
     * */
    public  JSONObject getImages(){
        JSONObject json = jsonParser.getJSONFromUrl(getImagesURL,null);
        return  json;
    }

    public  JSONObject getAnnounce(){
        JSONObject json = jsonParser.getJSONFromUrl(getAnnounceURL,null);
        return  json;
    }

    public  JSONObject getTransFirst(String strType){
        String tmpURL = String.format("%s?type=%s",getTransFirstURL,strType);
        Log.v("URL:",tmpURL);
        JSONObject json = jsonParser.getJSONFromUrl(tmpURL,null);
        return  json;
    }

    public  JSONObject getLocalStore(){
        JSONObject json = jsonParser.getJSONFromUrl(getLocalStoreURL,null);
        return  json;
    }

    public  JSONObject getImportant(){
        JSONObject json = jsonParser.getJSONFromUrl(getImportantURL,null);
        return  json;
    }

    public  JSONObject getContact(){
        JSONObject json = jsonParser.getJSONFromUrl(getContactURL,null);
        return  json;
    }

    public  JSONObject updateUserPwd(String authToken, String strCurPwd, String strNewPwd){
        Log.e("updateUserPwd", "email:" + strNewPwd);

//        String email = "asdff@a.com";
//        String name = "name1";
//        String password = "123456";

        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("current_password", strCurPwd);
            jsonObj.put("password", strNewPwd);
        } catch (Exception e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }



        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user", jsonObj.toString()));

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("user", jsonObj);
            jsonRequest.put("auth_token",authToken);
        } catch (JSONException e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }

        JSONObject json = jsonParser.getJSONFromUrlAndJSON(RegUserURL, jsonRequest);
        return  json;
    }

    public  JSONObject updateUser(String authToken, String fullName, String email, String currentPwd){
        Log.e("updateUser", "email:" + fullName);

//        String email = "asdff@a.com";
//        String name = "name1";
//        String password = "123456";

        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("name", fullName);
            jsonObj.put("email", email);
            jsonObj.put("current_password",currentPwd);
        } catch (Exception e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user", jsonObj.toString()));

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("user", jsonObj);
            jsonRequest.put("auth_token",authToken);
        } catch (JSONException e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }

        JSONObject json = jsonParser.getJSONFromUrlAndJSON(RegUserURL,jsonRequest);
        return  json;
    }
    /**
     * function make User Registration Request
     * */
    public  JSONObject signUpUser(String strName, String strEmail, String strPass){

        Log.e("SignUp", "email:" + strEmail);

//        String email = "asdff@a.com";
//        String name = "name1";
//        String password = "123456";

        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("email", strEmail);
            jsonObj.put("name", strName);
            jsonObj.put("password", strPass);
        } catch (Exception e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }



        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("user", jsonObj.toString()));

        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("user", jsonObj);
        } catch (JSONException e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }

        JSONObject json = jsonParser.getJSONFromUrlAndJSON(RegUserURL,jsonRequest);
        return  json;
    }

    /**
     * function send feedback Request
     * */

    public JSONObject sendFeedback(String strFeedback, String authToken) {
        // Building Parameters
        //NSDictionary * parameters = @{@"tag":@"login", @"email":email, @"password":password, @"token":token};
        Log.e("SendFeedback", "message:" + strFeedback);

        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("auth_token",authToken);
            jsonObj.put("message", strFeedback);
        } catch (Exception e) {
            Log.e("JSON ERROR Occurred!!!!", "Exception: " + e);
        }

        JSONObject json = jsonParser.getJSONFromUrlAndJSON(SendFeedbackURL, jsonObj);
        return  json;
    }

 	/**
	 *
	 * Check connection to server
	 * */
	public boolean isConnectedToServer(String url, int timeout) {
	    try{
	        URL myUrl = new URL(url);
	        URLConnection connection = myUrl.openConnection();
	        connection.setConnectTimeout(timeout);
	        connection.connect();
	        return true;
	    } catch (Exception e) {
	        // Handle your exceptions
	        return false;
	    }
	}

	private String getMD5EncryptedString(String encTarget){
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception while encrypting to md5");
            e.printStackTrace();
        } // Encryption algorithm
        mdEnc.update(encTarget.getBytes(), 0, encTarget.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        while ( md5.length() < 32 ) {
            md5 = "0"+md5;
        }
        return md5;
	}

}
