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
import com.designrifts.ultimatethemeui.iconfragment.IconFragmentGames;
import com.designrifts.ultimatethemeui.iconfragment.IconFragmentLatest;
import com.designrifts.ultimatethemeui.iconfragment.IconFragmentMisc;
import com.designrifts.ultimatethemeui.iconfragment.IconFragmentPlay;
import com.designrifts.ultimatethemeui.iconfragment.IconFragmentSystem;


public class IconActivity extends FragmentActivity{

	private final Handler handler = new Handler();
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private IconPagerAdapter adapter;
	private ShareActionProvider mShareActionProvider;
	private Intent mShareIntent;
	private int indicatorColor = 0xFF3F9FE0;
	private int tabTextColor = 0xFF666666;
	
		//This activity is what displays the icon fragments (the icon categories) see iconfragment for the tabs themselves
	   @Override
	    public void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_icon);
			mShareIntent = new Intent();
			mShareIntent.setAction(Intent.ACTION_SEND);
			mShareIntent.setType("text/plain");
			mShareIntent.putExtra(Intent.EXTRA_TEXT, "From me to you, this text is new.");

	        
	        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
			ViewPager pager = (ViewPager) findViewById(R.id.pager);
		
			adapter = new IconPagerAdapter(getSupportFragmentManager());

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


	public class IconPagerAdapter extends FragmentPagerAdapter {

		private final int[] TITLES = { R.string.icon1, R.string.icon2, R.string.icon3, R.string.icon4, R.string.icon5 };

		public IconPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.icon1).toUpperCase(l);
			case 1:
				return getString(R.string.icon2).toUpperCase(l);
			case 2:
				return getString(R.string.icon3).toUpperCase(l);
			case 3:
				return getString(R.string.icon4).toUpperCase(l);
			case 4:
				return getString(R.string.icon5).toUpperCase(l);	
			}
			return null;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment f = new Fragment();
			switch(position){
			case 0:
				f= new IconFragmentLatest();	
				break;
			case 1:
				f= new IconFragmentSystem();
				break;
			case 2:
				f= new IconFragmentPlay();	
				break;
			case 3:
				f= new IconFragmentGames();	
				break;
			case 4:
				f= new IconFragmentMisc();	
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