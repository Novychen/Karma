package com.example.karma;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Clean the dirt of the screen
 */
public class LevelFourFragment extends Fragment {


    public LevelFourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Smartphone s = Smartphone.getInstance(getActivity());

        DrawLineWithFinger.width = (int) s.getWidthInPixels();
        DrawLineWithFinger.height = (int) s.getHeightInPixels();
        DrawLineWithFinger.mActivity = getActivity();
        return inflater.inflate(R.layout.fragment_level_four, container, false);
    }

}
