package com.knit.sb_kuch;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Send_sms extends AppCompatActivity {

    AutoCompleteTextView mobile;
    EditText subject;
    Button send;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        mobile=findViewById(R.id.autoCompleteTextView);
        subject=findViewById(R.id.editText);
        send=findViewById(R.id.b1);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);

        Toast.makeText(Send_sms.this,"type some words related contact it will auto complete",Toast.LENGTH_LONG).show();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobile.getText()!=null && subject.getText()!=null){
                    sendSms(mobile.getText().toString(),subject.getText().toString());
                }
            }
        });

        getNumber();
    }

    public void sendSms(String m, String sub) {

        String SEND_SMS="SEND_SMS";
        String D_SMS="d_SMS";
        PendingIntent send_sms=PendingIntent.getBroadcast(Send_sms.this,0,
                new Intent(SEND_SMS),0);
        PendingIntent d_sms=PendingIntent.getBroadcast(Send_sms.this,0,
                new Intent(D_SMS),0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(Send_sms.this,"Message_Send!!!!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(Send_sms.this,"Network error!!!!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(Send_sms.this,"No service!!!!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(Send_sms.this,"Null PDU error!!!!",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(Send_sms.this,"Genric error due to balance!!!!",Toast.LENGTH_LONG).show();
                        break;
                }

            }
        },new IntentFilter(SEND_SMS));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(Send_sms.this,"Message_Delivered!!!!",Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(Send_sms.this,"Not Delivered!!!!",Toast.LENGTH_LONG).show();
                        break;
                }

            }
        },new IntentFilter(D_SMS));

        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(m,null,sub,send_sms,d_sms);


    }
    public void getNumber(){
        ContentResolver contentResolver=getContentResolver();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor=contentResolver.query(uri,null,null,null,null);
        while (cursor.moveToNext()){
//            String data=cursor.getString(cursor.getColumnIndex
//                    (ContactsContract.CommonDataKinds.Phone.NUMBER));
            String data=cursor.getString(cursor.getColumnIndex
                    (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String no=cursor.getString(cursor.getColumnIndex
                    (ContactsContract.CommonDataKinds.Phone.NUMBER));
            adapter.add(data+"  "+no);
        }
        mobile.setAdapter(adapter);
    }
}
