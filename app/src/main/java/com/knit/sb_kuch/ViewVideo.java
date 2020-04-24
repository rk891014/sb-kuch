package com.knit.sb_kuch;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.nio.channels.SeekableByteChannel;
import java.util.Timer;
import java.util.TimerTask;

public class ViewVideo extends AppCompatActivity {

    MediaPlayer mediaplayer;
    String filename;
    SeekBar seekBar;
    VideoView vv;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        seekBar=findViewById(R.id.seekBar2);
        vv=findViewById(R.id.vv);
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        filename=bundle.getString("videofilename");

        vv.setVideoPath(filename);
//        vv.setMediaController(new MediaController(this));
        vv.requestFocus();
        vv.start();


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaplayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
