package com.designrifts.ultimatethemeui;

	import com.designrifts.ultimatethemeui.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;

	public class iconssystem extends Activity implements AdapterView.OnItemClickListener{
	    public Uri CONTENT_URI;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        // This is quick way of theming the action bar without using styles.xml (e.g. using ActionBar Style Generator)
	        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.holo_blue_dark)));
	        getActionBar().setDisplayShowHomeEnabled(false);
	        super.onCreate(savedInstanceState);
	        int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
	        setContentView(R.layout.main);
	        GridView i=(GridView) findViewById(R.id.icon_grid);
	        i.setNumColumns(GridView.AUTO_FIT);
	        i.setColumnWidth(iconSize);
	        i.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
	        i.setVerticalSpacing(iconSize/3);
	        i.setOnItemClickListener(this);
	        IconAdapter adapter=new IconAdapter(this,iconSize);
	        i.setAdapter(adapter);
	        i.setOnItemClickListener(this);
	        CONTENT_URI=Uri.parse("content://"+iconsProvider.class.getCanonicalName());
	    }

	    @Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
	        String icon=adapterView.getItemAtPosition(i).toString();
	        Intent result = new Intent(null, Uri.withAppendedPath(CONTENT_URI,icon));
	        setResult(RESULT_OK, result);
	        finish();
	    }
	    private class IconAdapter extends BaseAdapter{
			private Context mContext;
			private int mIconSize;
			public IconAdapter(Context mContext, int iconsize) {
				super();
				this.mContext = mContext;
				this.mIconSize = iconsize;
				loadIcon();
			}

	        @Override
	        public int getCount() {
	            return mThumbs.size();
	        }

	        @Override
	        public Object getItem(int position) {
	            return mThumbs.get(position);
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
	        private void loadIcon() {
	            mThumbs = new ArrayList<Integer>();

	            final Resources resources = getResources();
	            final String packageName = getApplication().getPackageName();

	            addIcon(resources, packageName, R.array.systemicons);

	        }
	        private void addIcon(Resources resources, String packageName, int list) {
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

