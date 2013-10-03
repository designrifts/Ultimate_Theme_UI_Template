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

import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardBase;
import com.afollestad.cardsui.CardHeader;
import com.afollestad.cardsui.CardListView;
import com.afollestad.cardsui.CardListView.CardClickListener;

public class FragmentExtras extends Fragment  implements Card.CardMenuListener<Card> {
	
	private final String [] EXTRAS = {"Play", "Wallpapers", "Icons", "Requests", "UCCW", "Zooper", "Your Mom", "Dildroid"};

	private CardListView list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_extras, container, false);

		list = (CardListView) view.findViewById(R.id.ListView);
		
		list.setOnCardClickListener(new CardClickListener() {
			public void onCardClick(AdapterView<?> adapter, View view, int position, long arg) {
				Object listItem = list.getItemAtPosition(position);
			}

			@Override
			public void onCardClick(int position, CardBase card, View view) {
				switch(position){
				case 1:
					actPlay();	
					break;
				case 3:
					actWallpapers();
					break;
				case 5:
					actRequest();
					break;	
		
				}
			}
			public int getCount() {
				return EXTRAS.length;
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

		cardsAdapter.add(new CardHeader(getActivity(), R.string.playheader)
				);
		cardsAdapter.add(new Card(getString(R.string.play), getString(R.string.play_extra))
				.setThumbnail(getActivity(),R.drawable.apps_googleplaystore) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				);	
		cardsAdapter.add(new CardHeader(getActivity(), R.string.basicsheader)
				);
		cardsAdapter.add(new Card(getString(R.string.wallpaper), getString(R.string.wallpaper_extra))
				.setThumbnail(getActivity(),R.drawable.system_gallery)  // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 
		cardsAdapter.add(new Card(getString(R.string.icon), getString(R.string.icon_extra))
				.setThumbnail(getActivity(), R.drawable.icon) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 	
		cardsAdapter.add(new Card(getString(R.string.request), getString(R.string.request_extra))
				.setThumbnail(getActivity(), R.drawable.apps_androidactivities) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 
		cardsAdapter.add(new CardHeader(getActivity(), R.string.extrasheader)
				);
		cardsAdapter.add(new Card(getString(R.string.uccw), getString(R.string.uccw_extra))
				.setThumbnail(getActivity(),R.drawable.apps_uccw) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 						
		cardsAdapter.add(new Card(getString(R.string.zooper), getString(R.string.zooper_extra))
				.setThumbnail(getActivity(),R.drawable.apps_zooperwidget) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 								
		cardsAdapter.add(new Card(getString(R.string.extras1), getString(R.string.extras1_extra))
				.setThumbnail(getActivity(), R.drawable.jai) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 								
		cardsAdapter.add(new Card(getString(R.string.extras2), getString(R.string.extras2_extra))
				.setThumbnail(getActivity(),R.drawable.amasan25) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 										

		list.setAdapter(cardsAdapter);
	}
	

    @Override
    public void onMenuItemClick(Card card, MenuItem item) {
        Toast.makeText(getActivity(), card.getTitle() + ": " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }
    
    private void actPlay() {
    	String marketuriString = "market://search?q=designrifts";
    	Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(marketuriString));
    	try {
    		startActivity(intent);
    	} catch (ActivityNotFoundException e2) {
    		e2.printStackTrace();
    		Toast.makeText(getApplicationContext(), "Play Store not found!", Toast.LENGTH_LONG).show();
    	}
    };
    private void actWallpapers() {
    	String pkg = getResources().getString(R.string.pkg);
    	Intent wallpapers = new Intent(Intent.ACTION_SET_WALLPAPER);
    	wallpapers.putExtra(pkg,".wallpaper");
    	try {        
            startActivity(wallpapers);
    		}
    	catch (RuntimeException wall) {
    		wall.printStackTrace();
    	}
    };
    private void actRequest() {
   	 Uri requesturiString = Uri.parse("https://docs.google.com/document/d/1yhf2KysfD9T6sToJs7iI8QxH8_SA5ZYEevjFLXvCdio/edit?usp=sharing");
        Intent requestIntent = new Intent("android.intent.action.VIEW", requesturiString);
        try {
   		  startActivity(requestIntent);
   		} catch (ActivityNotFoundException e2) {
   		  e2.printStackTrace();
   		}

   }

	private String getPackageName() {
		// TODO Auto-generated method stub
		return null;
	}

	private Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
