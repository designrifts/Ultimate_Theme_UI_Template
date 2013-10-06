package com.designrifts.ultimatethemeui;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardHeader;
import com.afollestad.cardsui.CardListView;
import com.afollestad.cardsui.CardListView.CardClickListener;
import com.designrifts.ultimatethemeui.R;



public class FragmentTheme extends Fragment  implements Card.CardMenuListener<Card> {


	public final static String ACTION_MYTHEME = "com.gau.go.launcherex.MyThemes.mythemeaction";
	public final static String ACTION_START_MY_THEMES = "com.gau.go.launcherex.action.start_my_themes";
	public final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
	public final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
	public final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
	public final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";

	private CardListView list;
	//* CLASS JUST FOR THE CUSTOM ALERT DIALOG
	class CustomAlertDialog extends AlertDialog {
		public CustomAlertDialog(Context context) {
			super(context);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			boolean ret = super.onKeyDown(keyCode, event);
			finish();
			return ret;
		}

		private void finish() {
			// TODO Auto-generated method stub
			
		}
	}

	//!! SUBS / METHODS / FUNCTIONS USED FOR info
	//* MAKETOAST - GENERIC SUB TO TOAST ANYWHERE ANYTIME
	public void makeToast(String msg) {
		Context context = getActivity().getApplicationContext();
		CharSequence text = msg;
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	private void applyActionLauncherTheme(){
		try {
			Intent actionlauncherIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.chrislacy.actionlauncher.pro");
			if (actionlauncherIntent != null) {
	            // TODO BY YOU: set this package name as appropriate. Eg "kov.theme.stark"
				actionlauncherIntent.putExtra("apply_icon_pack",getActivity().getPackageName());
	            startActivity(actionlauncherIntent);    // Action Launcher will take it from here...
	        } else {
	            // Direct users to get Action Launcher Pro
	            String playStoreUrl = "https://play.google.com/store/apps/details?id=com.chrislacy.actionlauncher.pro";
	            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(playStoreUrl)));
	        }
		} catch (ActivityNotFoundException e7) {
			e7.printStackTrace();
			makeToast("Action Launcher is not installed!");
		}
		//finish();
	}
	private void applyAdwTheme(){
		try {
			Intent adwlauncherIntent = new Intent(Intent.ACTION_MAIN);
			adwlauncherIntent.setComponent(new ComponentName("org.adw.launcher","org.adw.launcher.Launcher"));
	    	startActivity(adwlauncherIntent);
	    	makeToast("Apply with \"ADW Settings\" in Menu");
		} catch (ActivityNotFoundException e4) {
			e4.printStackTrace();
			 makeToast("ADW Launcher is not installed!");
		}

	}
	private void applyAdwExTheme(){
		try {
			Intent adwexlauncherIntent = new Intent("org.adw.launcher.SET_THEME");
			adwexlauncherIntent.putExtra("org.adw.launcher.theme.NAME",getActivity().getPackageName());
	        startActivity(Intent.createChooser(adwexlauncherIntent,"ADW Not Installed"));
		} catch (ActivityNotFoundException e5) {
			e5.printStackTrace();
			makeToast("ADW EX is not installed!");
			applyAdwTheme();
		}
		//finish();
	}
	private void applyApexTheme() {
		final String ACTION_SET_THEME = "com.anddoes.launcher.SET_THEME";
		final String EXTRA_PACKAGE_NAME = "com.anddoes.launcher.THEME_PACKAGE_NAME";

		Intent apexlauncherIntent = new Intent(ACTION_SET_THEME);
		apexlauncherIntent.putExtra(EXTRA_PACKAGE_NAME, getActivity().getPackageName());
		apexlauncherIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try {
		    startActivity(apexlauncherIntent);
		} catch (ActivityNotFoundException e3) {
			e3.printStackTrace();
		    makeToast("Apex Launcher is not installed!");
		}
		//finish();
	}
	
	private void applyNovaTheme(){
		try {
			Intent novalauncherIntent = new Intent(ACTION_APPLY_ICON_THEME);
			novalauncherIntent.setPackage(NOVA_PACKAGE);
			novalauncherIntent.putExtra(EXTRA_ICON_THEME_TYPE, "GO");
			novalauncherIntent.putExtra(EXTRA_ICON_THEME_PACKAGE, getActivity().getPackageName());
			startActivity(novalauncherIntent);

		} catch (ActivityNotFoundException e6) {
			e6.printStackTrace();
			makeToast("Nova Launcher is not installed!");
		}
		//finish();
	}
	private void applyGoTheme() {
		try {
			Intent go = getActivity().getPackageManager().getLaunchIntentForPackage("com.gau.go.launcherex");
			if (go != null) {
				Intent intent = new Intent(ACTION_MYTHEME);
				intent.putExtra("type", 1);
				intent.putExtra("pkgname", getActivity().getPackageName());
				getActivity().sendBroadcast(intent);
				Toast appliedGo = Toast
		                .makeText(getActivity().getBaseContext(), getResources().getString
		                		(R.string.go_applied), Toast.LENGTH_LONG);
						appliedGo.show();
						startActivity(intent);
	        } else {
	            // Direct users to get Action Launcher Pro
	            String playStoreUrl = "https://play.google.com/store/apps/details?id=com.gau.go.launcherex";
	            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(playStoreUrl)));
	        }
		} catch (ActivityNotFoundException e7) {
			e7.printStackTrace();
			makeToast("Go is not installed!");
		}
		//finish();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_theme, container, false);

		list = (CardListView) view.findViewById(R.id.ListView);
		
		list.setOnCardClickListener(new CardClickListener() {
			public void onCardClick(AdapterView<?> adapter, View view, int position, long arg) {
				Object listItem = list.getItemAtPosition(position);
			}

			@Override
			public void onCardClick(int position, CardBase card, View view) {
				String str = card.getTitle();
				if (str.equals(getString(R.string.actionlauncher))) {
					applyActionLauncherTheme();
				}
				if (str.equals(getString(R.string.adwlauncher))) {
					applyAdwExTheme();
				}
				if (str.equals(getString(R.string.apexlauncher))) {
					applyApexTheme();
				}
				if (str.equals(getString(R.string.novalauncher))) {
					applyNovaTheme();
				}
				if (str.equals(getString(R.string.golauncher))) {
					applyGoTheme();
				}
			}
		});
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		CardAdapter<Card> cardsAdapter = new CardAdapter<Card>(getActivity())
				.setAccentColorRes(android.R.color.holo_blue_light)
				.setPopupMenu(R.menu.theme_popup, this);

		cardsAdapter.add(new CardHeader(getActivity(), R.string.themeheader)
				);
		cardsAdapter.add(new Card(getString(R.string.actionlauncher), getString(R.string.actionlauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_actionlauncherpro) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				);
		cardsAdapter.add(new Card(getString(R.string.adwlauncher), getString(R.string.adwlauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_adwex) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				);	
		cardsAdapter.add(new Card(getString(R.string.apexlauncher), getString(R.string.apexlauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_apexlauncher) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				);
		cardsAdapter.add(new Card(getString(R.string.novalauncher), getString(R.string.novalauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_novalauncher) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 
		cardsAdapter.add(new Card(getString(R.string.golauncher), getString(R.string.golauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_golauncher) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				);

		list.setAdapter(cardsAdapter);
	}
	
	public void onMenuItemClick(Card card, MenuItem item) {
	    Toast.makeText(getActivity(), card.getTitle() + ": " + item.getTitle(), Toast.LENGTH_SHORT).show();
	}
}
