package com.lq.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lq.activity.R;

public class ContentFragment extends Fragment {
	String text = null;

	public ContentFragment() {
	}

	public ContentFragment(String text) {
		this.text = text;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// inflater the layout
		View view = inflater.inflate(R.layout.fragment_text, null);
		TextView textView = (TextView) view.findViewById(R.id.textView);
		if (!TextUtils.isEmpty(text)) {
			textView.setText(text);
		}
		return view;
	}

}
