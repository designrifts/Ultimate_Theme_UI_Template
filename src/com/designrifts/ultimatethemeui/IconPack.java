/* This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details. */

package com.designrifts.ultimatethemeui;

import java.util.ArrayList;
import com.designrifts.ultimatethemeui.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class IconPack extends Activity implements OnItemClickListener {
	private static final String ACTION_ADW_PICK_ICON="org.adw.launcher.icons.ACTION_PICK_ICON";
	private static final String ACTION_ADW_PICK_RESOURCE="org.adw.launcher.icons.ACTION_PICK_ICON_RESOURCE";
	private boolean mPickerMode=false;
	private boolean mResourceMode=false;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
        setContentView(R.layout.main);
        GridView g=(GridView) findViewById(R.id.icon_grid);
        g.setNumColumns(GridView.AUTO_FIT);
        g.setColumnWidth(iconSize);
        g.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
        g.setVerticalSpacing(iconSize/3);
        g.setOnItemClickListener(this);
        IconsAdapter adapter=new IconsAdapter(this,iconSize);
        g.setAdapter(adapter);
        if(getIntent().getAction().equals(ACTION_ADW_PICK_ICON)){
        	mPickerMode=true;
        }
        if(getIntent().hasExtra(ACTION_ADW_PICK_RESOURCE)){
        	mResourceMode=true;
        }

    }
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		if(mPickerMode){
			Intent intent=new Intent();
			if(!mResourceMode){
				Bitmap bitmap=null;
				try{
					bitmap=(Bitmap) adapterView.getAdapter().getItem(position);
				}catch (Exception e) {
				}
				if(bitmap!=null){
					intent.putExtra("icon",bitmap);
					setResult(RESULT_OK, intent);
				}else{
					setResult(RESULT_CANCELED, intent);
				}
			}else{
				ShortcutIconResource res=((IconsAdapter)adapterView.getAdapter()).getResource(position);
				if(res!=null){
					intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, res);
					setResult(RESULT_OK, intent);
				}else{
					setResult(RESULT_CANCELED, intent);
				}
			}
			finish();
		}
	}
	private class IconsAdapter extends BaseAdapter{
		private Context mContext;
		private int mIconSize;
		public IconsAdapter(Context mContext, int iconsize) {
			super();
			this.mContext = mContext;
			this.mIconSize = iconsize;
			loadIcons();
		}

		@Override
		public int getCount() {
			return mThumbs.size();
		}

		public ShortcutIconResource getResource(int position){
			return ShortcutIconResource.fromContext(IconPack.this,
                    mThumbs.get(position));
		}
		@Override
		public Object getItem(int position) {
		    Options opts=new BitmapFactory.Options();
		    opts.inPreferredConfig=Bitmap.Config.ARGB_8888;
		    return BitmapFactory.decodeResource(mContext.getResources(), mThumbs.get(position), opts);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(mIconSize, mIconSize));
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(mThumbs.get(position));
            return imageView;
		}

		private ArrayList<Integer> mThumbs;
		////////////////////////////////////////////////
	    private void loadIcons() {
	        mThumbs = new ArrayList<Integer>();

	        final Resources resources = getResources();
	        final String packageName = getApplication().getPackageName();

	        addIcons(resources, packageName, R.array.icon_pack);
	    }
	    private void addIcons(Resources resources, String packageName, int list) {
	        final String[] extras = resources.getStringArray(list);
	        for (String extra : extras) {
	            int res = resources.getIdentifier(extra, "drawable", packageName);
	            if (res != 0) {
	                final int thumbRes = resources.getIdentifier(extra,"drawable", packageName);
	                if (thumbRes != 0) {
	                    mThumbs.add(thumbRes);
	                }
	            }
	        }
	    }

	}
}