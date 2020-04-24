package com.knit.sb_kuch;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class Service_helper extends Service implements MediaPlayer.OnCompletionListener {

    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp=MediaPlayer.create(this,R.raw.aa);
        mp.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(!mp.isPlaying()){
            mp.start();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mp.isPlaying()){
            mp.stop();
        }
    }
}
