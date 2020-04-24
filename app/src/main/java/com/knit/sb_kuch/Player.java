package com.knit.sb_kuch;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class Player extends AppCompatActivity implements View.OnClickListener {

     MediaPlayer mp;
    ArrayList<File> mySongs;
    SeekBar sb;
    int position;
    Uri u;
    Thread updateseekBar;
    Button btPlay,btFF,btFB,btNxt,btPV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        btPlay=findViewById(R.id.play_pause);
        btFF=findViewById(R.id.forward);
        btFB=findViewById(R.id.backward);
        btNxt=findViewById(R.id.next);
        btPV=findViewById(R.id.previous);
        sb=findViewById(R.id.seekbar);



        btPlay.setOnClickListener(this);
        btFF.setOnClickListener(this);
        btFB.setOnClickListener(this);
        btNxt.setOnClickListener(this);
        btPV.setOnClickListener(this);

        updateseekBar= new Thread(){
            @Override
            public void run() {

                int totalDuration=mp.getDuration();
                int currentPosition=0;
                sb.setMax(totalDuration);
                while (currentPosition < totalDuration){
                    try{
                        sleep(500);
                        currentPosition=mp.getCurrentPosition();
                        sb.setProgress(currentPosition);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };

        if(mp!=null){
            mp.stop();
            mp.release();
        }

        Bundle b=getIntent().getExtras();
        mySongs= (ArrayList) b.getParcelableArrayList("songlist");
        position=b.getInt("pos",0);
        u=Uri.parse(mySongs.get(position).toString());
        mp=MediaPlayer.create(getApplicationContext(),u);
        mp.start();
        updateseekBar.start();


    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.play_pause:
                if(mp.isPlaying()){
                    mp.pause();
                }else mp.start();
                break;
             case R.id.forward:
                 mp.seekTo(mp.getCurrentPosition()+5000);
                 break;
            case R.id.backward:
                mp.seekTo(mp.getCurrentPosition()-5000);
                break;
            case R.id.next:
                mp.stop();
                mp.release();
                position=((position+1)%mySongs.size());
                Toast.makeText(Player.this,String.valueOf(position),Toast.LENGTH_LONG).show();
                u=Uri.parse(mySongs.get(position).toString());
                mp=MediaPlayer.create(getApplicationContext(),u);
                mp.start();
                break;
            case R.id.previous:
                mp.stop();
                mp.release();
                position=(position-1<0)?mySongs.size()-1 : position-1;
                u=Uri.parse(mySongs.get(position).toString());
                mp=MediaPlayer.create(getApplicationContext(),u);
                mp.start();
                break;

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
