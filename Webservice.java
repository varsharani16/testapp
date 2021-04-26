package com.a.testdemo.Webservice;


import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;


import com.a.testdemo.Constants;
import com.a.testdemo.MapsActivity;
import com.a.testdemo.SFStore;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.File;
import java.util.ArrayList;

import androidx.viewpager.widget.ViewPager;
import cz.msebera.android.httpclient.Header;

import static android.content.ContentValues.TAG;


/***
 * Created by comp1 on 06-04-2018.
 */

public class Webservice {

    private static final String TAG = "Webservice";

    private static final String LOGIN= " https://expandx.progfeel.co.in/api/user-login/representative-login";

    /*Login web service*/
    public static void login(final Context mContext, String email,String password,String device_id) {
        try {
            Constants.show_progress_dialog(mContext);
            final AsyncHttpClient client = new AsyncHttpClient();
            //client.setTimeout(6000000);upload_car_images
            final RequestParams request_param = new RequestParams();
            request_param.put("email", email);
            request_param.put("password", password);
            request_param.put("device_id", device_id);
            Log.d(TAG, "@@@login_request_param :" + LOGIN + "?" + request_param.toString());
            ((Activity) mContext).runOnUiThread(new Runnable() {
                public void run() {
                    client.post(LOGIN, request_param, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                            Constants.dismiss_progress_dialog();
                            Log.d(TAG, "@@@ mobile_login_response :" + response.toString());
                            // Log.d(TAG, "@@@ LOGIN resultResponse:" + response);
                            try {
                                //JSONObject result = new JSONObject(resultResponse);
                                boolean status = response.getBoolean("isSuccess");

                                if (status) {
                                    JSONArray arr = response.getJSONArray("data");
                                    JSONObject obj = arr.getJSONObject(0);

                                        Intent intent = new Intent(mContext, MapsActivity.class);
                                        mContext.startActivity(intent);

                                } else {
                                    String message = response.getString("message");
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                Constants.dismiss_progress_dialog();
                                e.printStackTrace();
                            }
                            super.onSuccess(statusCode, headers, response);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                            Constants.dismiss_progress_dialog();
                            //  Log.d(TAG, "@@@onFailure 1:" + responseString);
                            Toast. makeText(mContext,"Database error", Toast. LENGTH_SHORT).show();
                            super.onFailure(statusCode, headers, responseString, throwable);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                            Constants.dismiss_progress_dialog();
                            // Log.d(TAG, "@@@onFailure 2:" + errorResponse.toString());
                            Toast. makeText(mContext,"Network issue", Toast. LENGTH_SHORT).show();
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                            Constants.dismiss_progress_dialog();
                            //Log.d(TAG, "@@@onFailure 3:");
                            Toast. makeText(mContext,"Network issue", Toast. LENGTH_SHORT).show();
                            super.onFailure(statusCode, headers, throwable, errorResponse);
                        }
                    });
                }
            });
        } catch (Exception e) {
            Constants.dismiss_progress_dialog();
            //Toast. makeText(mContext,"", Toast. LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, MapsActivity.class);
            mContext.startActivity(intent);
            e.printStackTrace();
        }
    }





}

