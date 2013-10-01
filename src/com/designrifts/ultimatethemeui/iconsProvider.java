package com.designrifts.ultimatethemeui;

import java.io.FileNotFoundException;
import com.designrifts.ultimatethemeui.R;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;

public class iconsProvider extends ContentProvider{
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return "image/*";
    }

    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        if(uri.getPathSegments().size()>0){
            try{
                int resId=Integer.valueOf(uri.getPathSegments().get(0));
                return this.getContext().getResources().openRawResourceFd(resId);
            }catch (Throwable t){
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
