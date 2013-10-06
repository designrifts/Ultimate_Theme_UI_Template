/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.designrifts.ultimatethemeui;


import java.util.Locale;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;


public class ThemeActivity extends FragmentActivity{

	private final Handler handler = new Handler();

	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;
	private ShareActionProvider mShareActionProvider;
	private Intent mShareIntent;
	
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        // This is quick way of theming the action bar without using styles.xml (e.g. using ActionBar Style Generator)
	        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.holo_blue_dark)));
	        getActionBar().setDisplayShowHomeEnabled(false);

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
			mShareIntent = new Intent();
			mShareIntent.setAction(Intent.ACTION_SEND);
			mShareIntent.setType("text/plain");
			mShareIntent.putExtra(Intent.EXTRA_TEXT, "From me to you, this text is new.");
	        
	        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
			pager = (ViewPager) findViewById(R.id.pager);
			adapter = new MyPagerAdapter(getSupportFragmentManager());

			pager.setAdapter(adapter);
			

			final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
					.getDisplayMetrics());
			pager.setPageMargin(pageMargin);

			tabs.setViewPager(pager);

	    }
	


	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.share_menu, menu);
	    MenuItem item = menu.findItem(R.id.menu_item_share);
	    mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	    // Create the share Intent
	    String playStoreLink = "https://play.google.com/store/apps/details?id=" +
	        getPackageName();
	    String yourShareText = getString(R.string.share_this) + playStoreLink;
	    Intent shareIntent = ShareCompat.IntentBuilder.from(this)
	        .setType("text/plain").setText(yourShareText).getIntent();
	    // Set the share Intent
	    mShareActionProvider.setShareIntent(shareIntent);
	    return true;
	}


	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final int[] TITLES = { R.string.tab1, R.string.tab2, R.string.tab3, R.string.tab4 };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.tab1).toUpperCase(l);
			case 1:
				return getString(R.string.tab2).toUpperCase(l);
			case 2:
				return getString(R.string.tab3).toUpperCase(l);
			case 3:
				return getString(R.string.tab4).toUpperCase(l);	
			}
			return null;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment f = new Fragment();
			switch(position){
			case 0:
				f= new FragmentTheme();	
				break;
			case 1:
				f= new FragmentExtras();
				break;
			case 2:
				f= new FragmentContact();	
				break;
			case 3:
				f= new FragmentText();	
				break;	
			}
			return f;
		}
		
		@Override
		public int getCount() {
			return TITLES.length;
		}	
		
	}

}