package com.designrifts.ultimatethemeui;

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

public class FragmentContact extends Fragment  implements Card.CardMenuListener<Card> {

	private CardListView list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_contact, container, false);

		list = (CardListView) view.findViewById(R.id.ListView);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		CardAdapter<Card> cardsAdapter = new CardAdapter<Card>(getActivity())
				.setAccentColorRes(android.R.color.holo_blue_light)
				.setPopupMenu(R.menu.card_popup, this);

		cardsAdapter.add(new CardHeader(getActivity(), R.string.contactheader));
		cardsAdapter.add(new CardHeader(getActivity(), R.string.socialheader));
		cardsAdapter.add(new Card("Google Plus", "Is Whats Up").setThumbnail(getActivity(),
				R.drawable.apps_googleplus)); // sets a thumbnail image
														// from drawable
														// resources
		cardsAdapter.add(new Card("Twitter", "Is Where I post My Nudes").setThumbnail(getActivity(),
				R.drawable.apps_twitter)); // sets a thumbnail image from drawable
											// resources
		cardsAdapter.add(new Card("Facebook", "Is Where I Pretend We Are Friends").setThumbnail(getActivity(),
				R.drawable.apps_facebook)); // sets a thumbnail image from
												// drawable resources
		cardsAdapter.add(new CardHeader(getActivity(), R.string.emailheader));
		cardsAdapter.add(new Card("Gmail", "Me Feet Pics").setThumbnail(getActivity(),
				R.drawable.apps_googlemail)); // sets a thumbnail image from
												// drawable resources
		cardsAdapter.add(new CardHeader(getActivity(), R.string.webheader));
		cardsAdapter.add(new Card("The Internet", "Is Always Right").setThumbnail(getActivity(),
				R.drawable.system_browser)); // sets a thumbnail image
														// from drawable
														// resources
		cardsAdapter.add(new CardHeader(getActivity(), R.string.playheader));
		cardsAdapter.add(new Card("Play Store", "Buy More Stuff").setThumbnail(getActivity(),
				R.drawable.apps_googleplaystore)); // sets a thumbnail image
														// from drawable
														// resources

		list.setAdapter(cardsAdapter);
	}
	

    @Override
    public void onMenuItemClick(Card card, MenuItem item) {
        Toast.makeText(getActivity(), card.getTitle() + ": " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
