package com.knit.sb_kuch;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Sensor_Demo extends AppCompatActivity implements SensorEventListener {

    RelativeLayout rl;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor__demo);

        rl=findViewById(R.id.rl);
        tv=findViewById(R.id.tv);

//        sensor manager can be wifi too
        SensorManager sm= (SensorManager)getSystemService(SENSOR_SERVICE);

        int i=Sensor.TYPE_ORIENTATION;
//        int i=Sensor.TYPE_PROXIMITY;

        Sensor s=sm.getDefaultSensor(i);
        sm.registerListener(this,s,sm.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float []data=event.values;

        float x=data[0];
        float y=data[1];
        float z=data[2];

        String st="ORIENTATION SENSOR";
        st=st+"\n PITCH="+x;
        st=st+"\n ROLL="+y;
        st=st+"\n AZIMUTH="+z;

        tv.setText(st);
        rl.setBackgroundColor(Color.rgb((int) x,(int) y,(int ) z));


//        float object_distance=data[0];
//        if(object_distance==0){
//            rl.setBackgroundColor(Color.RED);
//        }else {
//            rl.setBackgroundColor(Color.BLUE);
//        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
