package com.designrifts.ultimatethemeui;

import java.util.List;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardHeader;
import com.afollestad.cardsui.CardListView;

public class FragmentTheme extends Fragment  implements Card.CardMenuListener<Card> {

	private CardListView list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_theme, container, false);

		list = (CardListView) view.findViewById(R.id.ListView);
		Resources res = getResources();
		String[] theme = res.getStringArray(R.array.theme_array);
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
	

    @Override
    public void onMenuItemClick(Card card, MenuItem item) {
        Toast.makeText(getActivity(), card.getTitle() + ": " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
