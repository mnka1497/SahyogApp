package com.example.android.sahyog;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by shinjaneegupta on 14/06/18.
 */

public class myposts_tab extends Fragment {
    TextView descText, plus, minus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.myposts_tab, container, false);

        descText = (TextView) rootView.findViewById(R.id.description_text);
        plus = (TextView) rootView.findViewById(R.id.plus);
        minus = (TextView) rootView.findViewById(R.id.minus);

        plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                plus.setVisibility(View.GONE);
                minus.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                minus.setVisibility(View.GONE);
                plus.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);

            }
        });
        return rootView;
    }
}
