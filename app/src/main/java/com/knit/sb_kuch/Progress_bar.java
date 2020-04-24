package com.knit.sb_kuch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Progress_bar extends AppCompatActivity {

    Button button;
    RatingBar rb;
    ProgressBar pb;
    int counter=0;
        SeekBar seekBar;
        ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

            seekBar=findViewById(R.id.seekBar2);
            progressBar=findViewById(R.id.progressBar);
            button=findViewById(R.id.button);
            rb=findViewById(R.id.rb);

        pb=findViewById(R.id.pb);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float f=rb.getRating();
                Toast.makeText(Progress_bar.this,""+f,Toast.LENGTH_LONG).show();
            }
        });

        final Timer t=new Timer();
        TimerTask tt=new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
                if(counter==1000)
                {
                    t.cancel();
                }
            }
        };
        t.schedule(tt,0,1000);

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressBar.setProgress(progress);

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