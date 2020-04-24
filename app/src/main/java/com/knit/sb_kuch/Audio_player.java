package com.knit.sb_kuch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Audio_player extends AppCompatActivity implements View.OnClickListener {

    Button b1,b2;
    Intent ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_demo);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);

        ob=new Intent(Audio_player.this,Service_helper.class);
//        startActivity(ob);
    }

    @Override
    public void onClick(View v) {


        if(v==b1){
            startService(ob);
        }else if(v==b2){
            stopService(ob);
        }

    }
}
