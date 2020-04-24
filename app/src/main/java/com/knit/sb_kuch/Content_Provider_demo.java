package com.knit.sb_kuch;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;




public class Content_Provider_demo extends AppCompatActivity {

    ContentResolver cr;

    Button show_data;
    Button insert_data;


    String s="content://com.knit.sb_kuch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content__provider_demo);

//        db=openOrCreateDatabase("iitk",MODE_PRIVATE,null);
//        db.execSQL("create table IF NOT EXISTS login1(name varchar(20),pass varchar(20))");


        show_data=findViewById(R.id.show_data);
        insert_data=findViewById(R.id.insert_data);

        insert_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u=Uri.parse(s);
                cr=getContentResolver();

                ContentValues contentValues=new ContentValues();
                contentValues.put("name","lko");
                contentValues.put("pass","9876");
//        # for normal cases


//        db.execSQL("insert into login1 values('ABC',123)");
//        db.execSQL("insert into login1 values('XYZ',456)");
//        Cursor c=db.rawQuery("select *from login1"

                Uri res=cr.insert(u,contentValues);
                Toast.makeText(getApplicationContext(),"record insert"+res,Toast.LENGTH_LONG).show();
            }
        });

       show_data.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Uri u=Uri.parse(s);
               cr=getContentResolver();

               Cursor c=cr.query(u,null,null,null,null);



//        # for normal cases

//        db.execSQL("insert into login1 values('ABC',123)");
//        db.execSQL("insert into login1 values('XYZ',456)");
//        Cursor c=db.rawQuery("select *from login1",null);


               String data="";
               while (c.moveToNext()){
                   data=data+c.getString(0)+"   "+c.getString(1)+"\n";
               }
               Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
           }
       });

    }
}
