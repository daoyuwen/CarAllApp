package com.alap.app5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     ContentResolver contentResolver;
    //1. 从数据库中获取数据（以下代码以从sd卡中读取mp3数据为例）
    Cursor mCursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,   //路径
            new String[]{MediaStore.Audio.Media._ID,    //写入我想要获得的信息（列）
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.ALBUM,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.DURATION,
                    MediaStore.Audio.Media.SIZE,
                    MediaStore.Audio.Media.DATA}, null, null, null);

//2. 将cursor中的数据转存到事先定义好的List中
List<MusicInfo> mMusicInfos = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < mCursor.getCount(); ++i) {
            MusicInfo musicInfo = new MusicInfo();  //MusicInfo类是数据储存单元
            mCursor.moveToNext();   //读取下一行，moveToNext()有boolean返回值，执行成功返回ture,反之false，可用于判断是否读取完毕。

            long id = mCursor.getLong(0);
            String title = mCursor.getString(1);
            String album = mCursor.getString(2);
            String displayName = mCursor.getString(3);
            String artist = mCursor.getString(4);
            long duration = mCursor.getLong(5);
            long size = mCursor.getLong(6);
            String url = mCursor.getString(7);   //转存数据

            musicInfo.setTitle(title);
            musicInfo.setId(id);
            musicInfo.setAlbum(album);
            musicInfo.setArtist(artist);
            musicInfo.setSize(size);
            musicInfo.setDisplayName(displayName);
            musicInfo.setDuration(duration);
            musicInfo.setUrl(url);  //存到数据单元

            mMusicInfos.add(musicInfo);  //添加到List
        }

    }
}