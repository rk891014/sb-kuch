package com.knit.sb_kuch;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Shared_Preference_demo extends AppCompatActivity {

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared__preference_demo);

        et=findViewById(R.id.et);

        SharedPreferences sh=getSharedPreferences("mm",0);
        et.setText(sh.getString("myText",""));
        Toast.makeText(Shared_Preference_demo.this,"it will keep the text alwys even when activity is destroyed", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sh=getSharedPreferences("mm",0);
        SharedPreferences.Editor editor=sh.edit();
        editor.putString("myText",et.getText().toString());
        editor.commit();
    }
}
