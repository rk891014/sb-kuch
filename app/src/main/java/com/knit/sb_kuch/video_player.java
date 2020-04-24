package com.knit.sb_kuch;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class video_player extends AppCompatActivity {

    ListView listView;

    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lis);

        listView=findViewById(R.id.lv);


        final ArrayList<File> mysongs=findSongs(Environment.getExternalStorageDirectory());



        items=new String[mysongs.size()];

        Toast.makeText(getApplicationContext(),String.valueOf(mysongs.size()),Toast.LENGTH_LONG).show();

        for(int i=0;i<mysongs.size();i++){
            items[i]=mysongs.get(i).getName().toString().replace(".mp3","");
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(getApplicationContext(),R.layout.activity_video_player,R.id.tx,items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),Player.class);
                i.putExtra("pos",position);
                i.putExtra("songlist",mysongs);
                startActivity(i);
            }
        });

    }


    public ArrayList<File> findSongs(File root)
    {
        ArrayList<File> a1=new ArrayList<>();
        File[] files=root.listFiles();
        for(File singleFile : files)
        {
            if(singleFile.isDirectory() && !singleFile.isHidden())
            {
                a1.addAll(findSongs(singleFile));
            }else
            {
                if(singleFile.getName().endsWith(".mp3"))
                {
                    a1.add(singleFile);
                }
            }
        }
        return a1;
    }
}
