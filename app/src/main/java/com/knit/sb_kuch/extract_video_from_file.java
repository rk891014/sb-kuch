package com.knit.sb_kuch;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class extract_video_from_file extends AppCompatActivity {

    ListView videolist;
    private Cursor videocursor;
    private int video_column_index;
    int count;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extract_video_from_file);

        init_phone_video_grid();

    }

    public void init_phone_video_grid() {
        //System.gc();
        String[] proj = {MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE};

        videocursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, proj, null, null, null);
        count = videocursor.getCount(); //no. of songs in sd card
        videolist = (ListView) findViewById(R.id.lv);
        videolist.setAdapter(new VideoAdapter(getApplicationContext()));
        videolist.setOnItemClickListener(videogridListener);

        Toast.makeText(getApplicationContext(),String.valueOf(count),Toast.LENGTH_LONG).show();


    }

    private AdapterView.OnItemClickListener videogridListener = new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView parent, View v, int position, long id) {
            //System.gc();
            video_column_index = videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            videocursor.moveToPosition(position);
            String filename = videocursor.getString(video_column_index);
            Intent intent = new Intent(extract_video_from_file.this, ViewVideo.class);
            intent.putExtra("videofilename", filename);
            startActivity(intent);
        }
    };

    public class VideoAdapter extends BaseAdapter {

        private Context vContext;

        public VideoAdapter(Context c) {

            vContext=c;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolder holder;
            String id=null;
            convertView=null;
            if(convertView==null){
                convertView= LayoutInflater.from(vContext).inflate(R.layout.listitem,parent,false);

                holder=new ViewHolder();
                holder.txtTitle=convertView.findViewById(R.id.tv);
                holder.txtSize=convertView.findViewById(R.id.tv1);
                holder.thumbImage=convertView.findViewById(R.id.iv);

                video_column_index =videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME);
                videocursor.moveToPosition(position);
                id=videocursor.getString(video_column_index);


                video_column_index=videocursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE);
                videocursor.moveToPosition(position);

                holder.txtTitle.setText(id);
                holder.txtSize.setText("Size in (KB): "+videocursor.getString(video_column_index));

                String[] proj = {MediaStore.Video.Media._ID,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.SIZE};

                Cursor cursor=managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,proj,
                        MediaStore.Video.Media.DISPLAY_NAME+"=?",new String[]{id},null);

                cursor.moveToFirst();

                Long ids=cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media._ID));

                ContentResolver crThumb=getContentResolver();
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inSampleSize=1;

                Bitmap curthumb=MediaStore.Video.Thumbnails.getThumbnail(crThumb,
                        ids,MediaStore.Video.Thumbnails.MINI_KIND,options);

                holder.thumbImage.setImageBitmap(curthumb);
                curthumb=null;

            }

            return convertView;
        }
    }
    static class ViewHolder{
        TextView txtTitle;
        TextView txtSize;
        ImageView thumbImage;
    }
}