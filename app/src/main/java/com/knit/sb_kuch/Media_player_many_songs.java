package com.knit.sb_kuch;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;


//it will show all files of raw folder only and it play song

public class Media_player_many_songs extends AppCompatActivity {

    ListView lv;
    ArrayList<String> al;

    ArrayAdapter aa;
    MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player_many_songs);

        Toast.makeText(getApplicationContext(),"rohit",Toast.LENGTH_LONG).show();

        lv=findViewById(R.id.lv);

        al=new ArrayList<String>();
        Field []fields=R.raw.class.getFields();

        for(int i=0;i<fields.length;i++){
            al.add(fields[i].getName());
        }
        aa=new ArrayAdapter(this,android.R.layout.simple_expandable_list_item_1,al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mp!=null){
                    mp.release();
                }

                int resid=getResources().getIdentifier(al.get(position),"raw",getPackageName());
                mp=MediaPlayer.create(Media_player_many_songs.this,resid);
                mp.start();
            }
        });


    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mp!=null)
            mp.stop();
    }
}
