package com.designrifts.ultimatethemeui;

import com.designrifts.ultimatethemeui.R;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentNew extends Fragment {

	private TextView exampleText;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_new, container, false);

		exampleText = (TextView) view.findViewById(R.id.txtExample);

		return view;
	}
}
