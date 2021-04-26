package com.a.testdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a.testdemo.Webservice.Webservice;

public class MainActivity extends AppCompatActivity {
    Button btn_login;
    EditText et_email,et_password;
    Context mContext;
    String device_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mContext=MainActivity.this;

        btn_login=findViewById(R.id.btn_login);
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 101);
        }
//        device_id = Settings.Secure.getString(mContext.getContentResolver(),
//                Settings.Secure.ANDROID_ID);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if (email.length() == 0) {
                    et_email.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please Enter email", Toast. LENGTH_SHORT).show();
               }
                else if (password.length()==0) {
                    et_password.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please enter password", Toast. LENGTH_SHORT).show();
                } else {
                    if(Constants.isNetworkAvailable(mContext)) {
                        Webservice.login(mContext,email,password,device_id);
                    } else {
                        Toast.makeText(getApplicationContext(),"Ckeck your Internet Connection", Toast. LENGTH_SHORT).show();

                    }
                }


            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                device_id= Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
            } else {
                //not granted
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        device_id= Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
