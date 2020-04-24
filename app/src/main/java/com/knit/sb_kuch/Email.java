package com.knit.sb_kuch;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Email extends AppCompatActivity {

    EditText to,subj,msg;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        to=findViewById(R.id.To);
        subj=findViewById(R.id.Subj);
        msg=findViewById(R.id.Msg);
        send=findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tto=to.getText().toString();
                String ssubj=subj.getText().toString();
                String mmsg=msg.getText().toString();

                Intent email=new Intent(Intent.ACTION_SENDTO);
                String uriText="mailto:"+tto+"?subject="+ Uri.encode(ssubj)+"&body="+Uri.encode(mmsg);


                Uri uri=Uri.parse(uriText);
                email.setData(uri);
                startActivity(Intent.createChooser(email,"Send email"));
            }
        });

    }
}
