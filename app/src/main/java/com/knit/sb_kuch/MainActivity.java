package com.knit.sb_kuch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lv;

    String[] st = {"1 progress_dialog1","2 Intent_demo","3 Email","4 Send_sms","5 Runtime_permission","6 Sensor_demo"
    ,"7 Shared_preferences","8 Audio_player","9 Media player list of only raw folder","10 all video file","11 audio_player_all mp3 files ","12 Progress_bar","13 SQLite",
    "14 content provider demo"};
    Class[] c={Progress_Dialog.class,Intent_demo.class,Email.class,Send_sms.class,Runtime_permission.class
    ,Sensor_Demo.class,Shared_Preference_demo.class, Audio_player.class,Media_player_many_songs.class
    ,extract_video_from_file.class,video_player.class,Progress_bar.class,sqLite.class,
    Content_Provider_demo.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.lv);

        ArrayAdapter obj=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,st);
        lv.setAdapter(obj);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this,c[position]);
                startActivity(i);

            }
        });

    }
}
