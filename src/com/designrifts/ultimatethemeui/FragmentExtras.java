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

public class FragmentExtras extends Fragment  implements Card.CardMenuListener<Card> {

	private CardListView list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_extras, container, false);

		list = (CardListView) view.findViewById(R.id.ListView);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();

		CardAdapter<Card> cardsAdapter = new CardAdapter<Card>(getActivity())
				.setAccentColorRes(android.R.color.holo_blue_light)
				.setPopupMenu(R.menu.card_popup, this);

		cardsAdapter.add(new CardHeader(getActivity(), R.string.extrasheader));
		cardsAdapter.add(new Card("Wallpapers", "Are Sexy").setThumbnail(getActivity(),
				R.drawable.system_gallery)); // sets a thumbnail image
														// from drawable
														// resources
		cardsAdapter.add(new Card("Want To Make A Request?", "This will scan your phone for the installed apps").setThumbnail(getActivity(),
				R.drawable.apps_androidactivities)); // sets a thumbnail image from drawable
											// resources
		cardsAdapter.add(new Card("UCCW", "Skins").setThumbnail(getActivity(),
				R.drawable.apps_uccw)); // sets a thumbnail image from drawable
											// resources
		cardsAdapter.add(new Card("Zooper Widgets", "Extra").setThumbnail(getActivity(),
				R.drawable.apps_zooperwidget)); // sets a thumbnail image from
												// drawable resources
		cardsAdapter.add(new Card("Your Mom", "Loves Me").setThumbnail(getActivity(),
				R.drawable.jai)); // sets a thumbnail image from
												// drawable resources
		cardsAdapter.add(new Card("This Guy", "Loves Dildroid!").setThumbnail(getActivity(),
				R.drawable.amasan25)); // sets a thumbnail image
														// from drawable
														// resources

		list.setAdapter(cardsAdapter);
	}
	

    @Override
    public void onMenuItemClick(Card card, MenuItem item) {
        Toast.makeText(getActivity(), card.getTitle() + ": " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
