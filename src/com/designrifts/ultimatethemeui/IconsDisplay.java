package com.designrifts.ultimatethemeui;

import java.lang.reflect.Field;
import com.designrifts.ultimatethemeui.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class IconsDisplay extends BaseAdapter {
	// a list of resource IDs for the images we want to display
	private Integer[] images;

	// a context so we can later create a view within it
	private Context myContext;
	
	// store a cache of resized bitmaps
	// Note: we're not managing the cache size to ensure it doesn't 
	// exceed any maximum memory usage requirements
	private Bitmap[] cache;
	

	// Constructor
	public IconsDisplay(Context c) {

		myContext = c;

		// Dynamically figure out which images we've imported
		// into the drawable folder, so we don't have to manually
		// type each image in to a fixed array.
		
		// obtain a list of all of the objects in the R.drawable class
		Field[] list = R.drawable.class.getFields();

		
		int count = 0, index = 0, j = list.length;

		// We first need to figure out how many of our images we have before
		// we can request the memory for an array of integers to hold their contents.

		// loop over all of the fields in the R.drawable class
		for(int i=0; i < j; i++)
			// if the name starts with img_ then we have one of our images!
			if(list[i].getName().startsWith("apps_")) count++;

		// We now know how many images we have. Reserve the memory for an 
		// array of integers with length 'count' and initialize our cache.
		images = new Integer[count];
		cache = new Bitmap[count];

		// Next, (unsafely) try to get the values of each of those fields
		// into the images array.
		try {
			for(int i=0; i < j; i++)
				if(list[i].getName().startsWith("img_"))
					images[index++] = list[i].getInt(null);
		} catch(Exception e) {}
		// safer: catch IllegalArgumentException & IllegalAccessException

	}

	@Override
	// the number of items in the adapter
	public int getCount() {
		return images.length;
	}

	@Override
	// not implemented, but normally would return 
	// the object at the specified position
	public Object getItem(int position) {
		return null;
	}

	@Override
	// return the resource ID of the item at the current position
	public long getItemId(int position) {
		return images[position];
	}

	// create a new ImageView when requested
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// we've been asked for an ImageView at a specific position. If
		// one doesn't already exist (ie, convertView is null) then we must create
		// one. Otherwise we can pass it convertView or a recycled view
		// that's been passed to us.
		
		ImageView imgView;

		
		if(convertView == null) {

			// create a new view
			imgView = new ImageView(myContext);
			imgView.setLayoutParams(new GridView.LayoutParams(144,144));

		} else {
	
			// recycle an old view (it might have old thumbs in it!)
			imgView = (ImageView) convertView;
	
		}

		// see if we've stored a resized thumb in cache
		if(cache[position] == null) {
		
			// create a new Bitmap that stores a resized
			// version of the image we want to display. 
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;
			Bitmap thumb = BitmapFactory.decodeResource(myContext.getResources(), images[position], options);

			// store the resized thumb in a cache so we don't have to re-generate it
			cache[position] = thumb;
		}

		// use the resized image we have in the cache
		imgView.setImageBitmap(cache[position]);


		// We might be tempted to do the below, but this is bad. The
		// images we've put in the drawable directory are quite large
		// and need to be scaled down to load all of them in memory to
		// display on screen. If we just use the raw images (as in the
		// below code) we would quickly get an OutOfMemory exception,
		// as the entire image would be loaded in memory and scaled 
		// down live.
		//imgView.setImageResource(images[position]);

		return imgView;
	}

	
}