package com.designrifts.ultimatethemeui;

import com.designrifts.ultimatethemeui.R;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

	public class IconFragmentSystem extends Fragment implements AdapterView.OnItemClickListener{
	    private static final String RESULT_OK = null;
		public Uri CONTENT_URI;

	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			View view = inflater.inflate(R.layout.main, container, false);
	        int iconSize=getResources().getDimensionPixelSize(android.R.dimen.app_icon_size);
	        GridView gridview = (GridView) view.findViewById(R.id.icon_grid);
	        gridview.setNumColumns(GridView.AUTO_FIT);
	        gridview.setColumnWidth(iconSize);
	        gridview.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
	        gridview.setVerticalSpacing(iconSize/3);
	        gridview.setOnItemClickListener(this);
	        gridview.setAdapter(new IconAdapter(getActivity(), iconSize));
	        gridview.setOnItemClickListener(this);
	        CONTENT_URI=Uri.parse("content://"+iconsProvider.class.getCanonicalName());
			return view;
			
	    }

		@Override
	    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
	        String icon=adapterView.getItemAtPosition(i).toString();
	        Intent result = new Intent(null, Uri.withAppendedPath(CONTENT_URI,icon));
	        setResult(RESULT_OK, result);
	        finish();
	    }
	    private void setResult(String resultOk, Intent result) {
			// TODO Auto-generated method stub

		}

		private void finish() {
			// TODO Auto-generated method stub

		}
		private class IconAdapter extends BaseAdapter{
			private Context mContext;
			private int mIconSize;
			public IconAdapter(Context mContext, int iconsize) {
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
	            final String packageName = getActivity().getApplication().getPackageName();

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
