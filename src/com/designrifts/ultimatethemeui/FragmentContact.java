package com.designrifts.ultimatethemeui;
 

import android.content.ActivityNotFoundException;
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
import com.designrifts.ultimatethemeui.R;

 
public class FragmentContact extends Fragment implements
  	Card.CardMenuListener<Card> {
 
	private CardListView list;
 
	private void actGPlus() {
		Uri gplusuriString = Uri.parse("http://gplus.to/designrifts");
		Intent gplusIntent = new Intent("android.intent.action.VIEW",
				gplusuriString);
		try {
			startActivity(gplusIntent);
		} catch (ActivityNotFoundException e2) {
			e2.printStackTrace();
		}
	}
 
	private void actTwitter() {
		Uri twitteruriString = Uri.parse("http://twitter.com/designrifts");
		Intent twitterIntent = new Intent("android.intent.action.VIEW",
				twitteruriString);
		try {
			startActivity(twitterIntent);
		} catch (ActivityNotFoundException e2) {
			e2.printStackTrace();
		}
	}
 
	private void actFacebook() {
		Uri facebookuriString = Uri.parse("http://facebook.com");
		Intent facebookIntent = new Intent("android.intent.action.VIEW",
				facebookuriString);
		try {
			startActivity(facebookIntent);
		} catch (ActivityNotFoundException e2) {
			e2.printStackTrace();
		}
	}
	
	private void actPlay() {
		Uri playuriString = Uri.parse("http://play.google.com/store/apps/developer?id=DesignRifts");
		Intent playIntent = new Intent("android.intent.action.VIEW",
				playuriString);
		try {
			startActivity(playIntent);
		} catch (ActivityNotFoundException e2) {
			e2.printStackTrace();
		}
	}
 
	private void actEmail() {
		Uri emailuriString = Uri.parse("mailto:designrifts@gmail.com");
		Intent emailIntent = new Intent("android.intent.action.VIEW",
				emailuriString);
		try {
			startActivity(emailIntent);
		} catch (ActivityNotFoundException e2) {
			e2.printStackTrace();
		}
	}
 
	private void actWeb() {
		Uri weburiString = Uri.parse("http://designrifts.com/");
		Intent webIntent = new Intent("android.intent.action.VIEW",
				weburiString);
		try {
			startActivity(webIntent);
		} catch (ActivityNotFoundException e2) {
			e2.printStackTrace();
		}
	}
 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
 
		View view = inflater.inflate(R.layout.fragment_contact, container,
				false);
 
		list = (CardListView) view.findViewById(R.id.ListView);
		list.setOnCardClickListener(new CardClickListener() {
			public void onCardClick(AdapterView<?> adapter, View view,
					int position, long arg) {
 
			}
 
			@Override
			public void onCardClick(int position, CardBase card, View view) {
				String str = card.getTitle();
				if (str.equals(getString(R.string.facebook))) {
					actFacebook();
				}
				if (str.equals(getString(R.string.gplus))) {
					actGPlus();
				}
				if (str.equals(getString(R.string.email))) {
					actEmail();
				}
				if (str.equals(getString(R.string.twitter))) {
					actTwitter();
				}
				if (str.equals(getString(R.string.web))) {
					actWeb();
				}
				if (str.equals(getString(R.string.play))) {
					actPlay();
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
				.setPopupMenu(R.menu.contact_popup, this);
		cardsAdapter.add(new CardHeader(getActivity(), R.string.contactheader)
				);
		cardsAdapter.add(new CardHeader(getActivity(), R.string.socialheader)
				);
		cardsAdapter.add(new Card(getString(R.string.gplus),
				getString(R.string.gplus_extra))
				.setThumbnail(getActivity(), R.drawable.apps_googleplus) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 
		cardsAdapter.add(new Card(getString(R.string.twitter),
				getString(R.string.twitter_extra))
				.setThumbnail(getActivity(), R.drawable.apps_twitter) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				);
		cardsAdapter.add(new Card(getString(R.string.facebook),
				getString(R.string.facebook_extra))
				.setThumbnail(getActivity(), R.drawable.apps_facebook) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				);
		cardsAdapter.add(new CardHeader(getActivity(), R.string.emailheader));
		cardsAdapter.add(new Card(getString(R.string.email),
				getString(R.string.email_extra))
				.setThumbnail(getActivity(), R.drawable.apps_googlemail) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 
		cardsAdapter.add(new CardHeader(getActivity(), R.string.webheader)
				);
		cardsAdapter.add(new Card(getString(R.string.web),
				getString(R.string.web_extra))
				.setThumbnail(getActivity(), R.drawable.system_browser) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 
		// drawable resources
		cardsAdapter.add(new CardHeader(getActivity(), R.string.playheader));
		cardsAdapter.add(new Card(getString(R.string.play),
				getString(R.string.play_extra))
				.setThumbnail(getActivity(), R.drawable.apps_googleplaystore) // sets a thumbnail image from drawable resources
				.setPopupMenu(-1, null) // -1 disables the popup menu for this individual card
				); 
		list.setAdapter(cardsAdapter);
	}
 
	@Override
	public void onMenuItemClick(Card card, MenuItem item) {
		Toast.makeText(getActivity(), card.getTitle() + ": " + item.getTitle(),
				Toast.LENGTH_SHORT).show();
	}
 
}