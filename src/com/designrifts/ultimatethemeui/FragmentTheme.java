package com.designrifts.ultimatethemeui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.designrifts.ultimatethemeui.QuickContactFragment.ContactPagerAdapter;

public class FragmentTheme extends Fragment {

	private ListView list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_theme, container, false);

		list = (ListView) view.findViewById(R.id.ListView);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		CardAdapter<Card> cardsAdapter = new CardAdapter<Card>(getActivity())
				.setAccentColorRes(android.R.color.holo_blue_light)
				.setPopupMenu(R.menu.card_popup, this);

		cardsAdapter.add(new CardHeader(this, R.string.themeheader));
		cardsAdapter.add(new Card("Action", "Launcher").setThumbnail(this,
				R.drawable.apps_actionlauncherpro)); // sets a thumbnail image
														// from drawable
														// resources
		cardsAdapter.add(new Card("ADW", "Launcher").setThumbnail(this,
				R.drawable.apps_adwex)); // sets a thumbnail image from drawable
											// resources
		cardsAdapter.add(new Card("Apex", "Launcher").setThumbnail(this,
				R.drawable.apps_apexlauncher)); // sets a thumbnail image from
												// drawable resources
		cardsAdapter.add(new Card("Nova", "Launcher").setThumbnail(this,
				R.drawable.apps_novalauncher)); // sets a thumbnail image from
												// drawable resources

		list.setAdapter(cardsAdapter);
	}
}
