package com.designrifts.ultimatethemeui;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardHeader;
import com.afollestad.cardsui.CardListView;
import com.afollestad.cardsui.CardListView.CardClickListener;


public class FragmentTheme extends Fragment  implements Card.CardMenuListener<Card> {
	
	private final String [] THEMES = {"Action", "ADW", "Apex", "Nova", "G0"};

	private CardListView list;
	public final static String ACTION_MYTHEME = "com.gau.go.launcherex.MyThemes.mythemeaction";
	public final static String ACTION_START_MY_THEMES = "com.gau.go.launcherex.action.start_my_themes";
	public final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
	public final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
	public final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
	public final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";

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
				switch(position){
				case 1:
					applyActionLauncherTheme();	
					break;
				case 2:
					applyAdwExTheme();
					break;
				case 3:
					applyApexTheme();	
					break;
				case 4:
					applyNovaTheme();	
					break;	
				case 5:
					applyGoTheme();	
					break;		
				}
			}
			public int getCount() {
				return THEMES.length;
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
				);
		cardsAdapter.add(new Card(getString(R.string.adwlauncher), getString(R.string.adwlauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_adwex) // sets a thumbnail image from drawable resources
				);	
		cardsAdapter.add(new Card(getString(R.string.apexlauncher), getString(R.string.apexlauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_apexlauncher) // sets a thumbnail image from drawable resources
				);
		cardsAdapter.add(new Card(getString(R.string.novalauncher), getString(R.string.novalauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_novalauncher) // sets a thumbnail image from drawable resources
				); 
		cardsAdapter.add(new Card(getString(R.string.golauncher), getString(R.string.golauncher_extra))
				.setThumbnail(getActivity(),R.drawable.apps_golauncher) // sets a thumbnail image from drawable resources
				);

		list.setAdapter(cardsAdapter);
	}
	
private void applyActionLauncherTheme(){
	try {
		Intent intent = getPackageManager().getLaunchIntentForPackage("com.chrislacy.actionlauncher.pro");
		if (intent != null) {
            // TODO BY YOU: set this package name as appropriate. Eg "kov.theme.stark"
            intent.putExtra("apply_icon_pack",getPackageName());
            startActivity(intent);    // Action Launcher will take it from here...
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
		Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.setComponent(new ComponentName("org.adw.launcher","org.adw.launcher.Launcher"));
    	startActivity(intent);
    	makeToast("Apply with \"ADW Settings\" in Menu");
	} catch (ActivityNotFoundException e4) {
		e4.printStackTrace();
		 makeToast("ADW Launcher is not installed!");
	}

}

private void applyAdwExTheme(){
	try {
		Intent intent = new Intent("org.adw.launcher.SET_THEME");
        intent.putExtra("org.adw.launcher.theme.NAME",getPackageName());
        startActivity(Intent.createChooser(intent,"ADW Not Installed"));
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

	Intent intent = new Intent(ACTION_SET_THEME);
	intent.putExtra(EXTRA_PACKAGE_NAME, getPackageName());
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	try {
	    startActivity(intent);
	} catch (ActivityNotFoundException e3) {
		e3.printStackTrace();
	    makeToast("Apex Launcher is not installed!");
	}
	//finish();
}

private void applyNovaTheme(){
	try {

		Intent intent = new Intent(ACTION_APPLY_ICON_THEME);
		intent.setPackage(NOVA_PACKAGE);
		intent.putExtra(EXTRA_ICON_THEME_TYPE, "GO");
		intent.putExtra(EXTRA_ICON_THEME_PACKAGE, getPackageName());

		startActivity(intent);

	} catch (ActivityNotFoundException e6) {
		e6.printStackTrace();
		makeToast("Nova Launcher is not installed!");
	}
	//finish();
}

public void onMenuItemClick(Card card, MenuItem item) {
    Toast.makeText(getActivity(), card.getTitle() + ": " + item.getTitle(), Toast.LENGTH_SHORT).show();
}

private void applyGoTheme() {
	Intent intent = new Intent(ACTION_MYTHEME);
	intent.putExtra("type", 1);
	intent.putExtra("pkgname", getPackageName());
	sendBroadcast(intent);
	Toast.makeText(getApplicationContext(), "Theme applied for GO LAUNCHER", Toast.LENGTH_SHORT).show();
return;

}
private Context getApplicationContext() {
	// TODO Auto-generated method stub
	return null;
}

private void sendBroadcast(Intent intent) {
	// TODO Auto-generated method stub
	
}

private String getPackageName() {
	// TODO Auto-generated method stub
	return null;
}

private void makeToast(String string) {
	// TODO Auto-generated method stub
	
}

private PackageManager getPackageManager() {
	// TODO Auto-generated method stub
	return null;
}

}
