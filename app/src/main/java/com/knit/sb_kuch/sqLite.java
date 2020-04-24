package com.knit.sb_kuch;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sqLite extends AppCompatActivity {

    SQLiteDatabase db;
    EditText et1,et2;
    Button insert,update,delete;
    TextView tv;
    String data="Record is:\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sq_lite);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        tv=findViewById(R.id.tv);

        db=openOrCreateDatabase("iitk",MODE_PRIVATE,null);
        db.execSQL("create table IF NOT EXISTS login(name varchar (20),pass varchar(20))");
        Cursor c=db.rawQuery("select *from login",null);
        while (c.moveToNext()){
            data=data+c.getString(0)+"      "+c.getString(1)+"\n";
        }
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=et1.getText().toString();
                String p=et2.getText().toString();
                if(TextUtils.isEmpty(n) && TextUtils.isEmpty(p)){
                    et1.setError("Enter name");
                    et2.setError("Enter password");
                }else{
                    db.execSQL("insert into login values('"+n+"','"+p+"')");
                Toast.makeText(getApplicationContext(),"record insert",Toast.LENGTH_LONG).show();
            }}
        });

        tv.setText(data);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=et1.getText().toString();
                String p=et2.getText().toString();

                db.execSQL("update login set pass='"+p+"' where name='"+n+"' ");
                Toast.makeText(getApplicationContext(),"record udated",Toast.LENGTH_LONG).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=et1.getText().toString();
                String p=et2.getText().toString();
                db.execSQL("delete from login where name='"+n+"' and pass='"+p+"'");
                Toast.makeText(getApplicationContext(),"record delete",Toast.LENGTH_LONG).show();
            }
        });

    }
}
