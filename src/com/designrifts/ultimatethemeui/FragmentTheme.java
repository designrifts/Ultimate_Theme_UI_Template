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
	public String pkg;
	
	private final String [] THEMES = {"Action", "ADW", "Apex", "Nova", "G0"};

	private CardListView list;

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

	
private void applyActionLauncherTheme(){
	String pkg = getResources().getString(R.string.pkg);
	Intent action = new Intent ("com.chrislacy.actionlauncher.pro.ActionLauncher");
	action.setPackage("com.chrislacy.actionlauncher.pro");
	action.putExtra("apply_icon_pack", pkg);
	try {
		startActivity(action);
		} 	
	catch (ActivityNotFoundException e) {
		Intent actionMarket = new Intent(Intent.ACTION_VIEW);
		actionMarket.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.chrislacy.actionlauncher.pro"));
		startActivity(actionMarket);
		makeToast("Action Launcher is not installed!");
	}
	//finish();
};



private void applyAdwTheme(){
	    String pkg = getResources().getString(R.string.pkg);
		Intent adw = new Intent("org.adw.launcher.SET_THEME");
		adw.putExtra("org.adw.launcher.theme.NAME",	pkg);
	try {
		startActivity(Intent.createChooser(adw,	"activating theme..."));
		} 
	catch (ActivityNotFoundException e) {
		Intent adwMarket = new Intent(Intent.ACTION_VIEW);
		adwMarket.setData(Uri.parse("market://details?id=org.adw.launcher"));
		startActivity(adwMarket);
		makeToast("Can't Find ADW Launcher");
	}
	//finish();
}; 

private void applyAdwExTheme(){
	    String pkg = getResources().getString(R.string.pkg);
	    Intent adwex = new Intent("org.adw.launcher.SET_THEME");
	    adwex.putExtra("org.adw.launcher.theme.NAME", pkg);
	try {
		startActivity(Intent.createChooser(adwex,"Starting ADWEX..."));
		} 
	catch (ActivityNotFoundException e) {
		Intent apexMarket = new Intent(Intent.ACTION_VIEW);
		apexMarket.setData(Uri.parse("market://details?id=org.adwfreak.launcher"));
		startActivity(apexMarket);
		makeToast("Can't Find AdwEX");
	}
	//finish();
 }; 

private void applyApexTheme() {
	    String pkg = getResources().getString(R.string.pkg);
	    Intent apex = new Intent("com.anddoes.launcher.SET_THEME");
	    apex.putExtra("com.anddoes.launcher.THEME_PACKAGE_NAME", pkg);
	    apex.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	try {
		startActivity(apex);
		} 
	catch (ActivityNotFoundException e) {
		Intent apexMarket = new Intent(Intent.ACTION_VIEW);
		apexMarket.setData(Uri.parse("market://details?id=com.anddoes.launcher"));
		startActivity(apexMarket);
		makeToast("Can't Find Apex Launcher");
	}
	//finish();
 }; 

private void applyNovaTheme(){
		String pkg = getResources().getString(R.string.pkg);
		Intent nova = new Intent("com.teslacoilsw.launcher.APPLY_ICON_THEME");
		nova.setPackage("com.teslacoilsw.launcher");
		nova.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_TYPE", "GO");
		nova.putExtra("com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE",pkg);
	try {
			startActivity(nova);
		} 
	catch (ActivityNotFoundException e) {
		Intent novaMarket = new Intent(Intent.ACTION_VIEW);
		novaMarket.setData(Uri.parse("market://details?id=com.teslacoilsw.launcher"));
		startActivity(novaMarket);
		makeToast("Can't Nova Launcher");
	}
	//finish();
};


private void applyGoTheme() {
        String pkg = getResources().getString(R.string.pkg);	
	    Intent go = new Intent("com.gau.go.launcherex.MyThemes.mythemeaction");
		go.putExtra("type", 1);
		go.putExtra("pkgname",pkg);
		sendBroadcast(go);	
		Toast.makeText(getApplicationContext(), "Theme applied for GO LAUNCHER", Toast.LENGTH_SHORT).show();
return;
};

private Context getApplicationContext() {
	// TODO Auto-generated method stub
	return null;
}

private void sendBroadcast(Intent intent) {
	// TODO Auto-generated method stub
	
}


private void makeToast(String string) {
	// TODO Auto-generated method stub
	
}

}
