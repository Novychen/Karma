package com.example.karma;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Schloss knacken
 */
public class LevelSevenFragment extends Fragment{


    public LevelSevenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_level_seven, _container, false);
        ImageView numbers = view.findViewById(R.id.fragment_level_7_numbers);

        numbers.animate().rotation(360).setDuration(1000);
        numbers.animate().start();

        return view;
    }

}
