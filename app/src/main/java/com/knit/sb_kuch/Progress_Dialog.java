package com.knit.sb_kuch;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Progress_Dialog extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress__dialog);


        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading Please wait");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCancelable(false);
        pd.setTitle("My Android App");
        pd.show();
        Thread t = new Thread() {
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        sleep(1000 / 20);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pd.setProgress(i);
                }
                pd.dismiss();
            }
        };
        t.start();
    }

//    this can be used while uploading a photo

//    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//        double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
//                .getTotalByteCount());
//        progressDialog.setMessage("Uploaded "+(int)progress+"%");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
}
