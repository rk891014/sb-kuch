package com.knit.sb_kuch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Runtime_permission extends AppCompatActivity {

    EditText et;
    ImageView iv;
    public static final int i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);

        et=findViewById(R.id.et);
        iv=findViewById(R.id.iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCalls();
            }
        });
    }

    private void makePhoneCalls() {
        String num=et.getText().toString();
        if(num.trim().length()>0) {
            if (ContextCompat.checkSelfPermission(Runtime_permission.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Runtime_permission.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            } else {
                String dial = "tel:" + num;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }else {
            Toast.makeText(Runtime_permission.this,"Enter Number",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==i)
        {
//            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                makePhoneCalls();
            }else {
                Toast.makeText(Runtime_permission.this,"permission is denied",Toast.LENGTH_LONG).show();
            }


    }
}
