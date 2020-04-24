package com.knit.sb_kuch;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.content.Context.MODE_PRIVATE;

public class Provider_Demo extends ContentProvider {

    SQLiteDatabase db;
    Context context;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        context=getContext();
        db=context.openOrCreateDatabase("iitk",MODE_PRIVATE,null);
        Cursor c=db.rawQuery("select *from login",null);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        context=getContext();
        db=context.openOrCreateDatabase("iitk",MODE_PRIVATE,null);
//        Cursor c=db.rawQuery("select *from login",null);
        db.insert("login",null,values);
        return uri;    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
