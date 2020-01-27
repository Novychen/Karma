package com.example.karma;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HelloFragment extends Fragment {

    ObjectAnimator mAnimator;
    public HelloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {

        View view = _inflater.inflate(R.layout.fragment_hello, _container, false);
        TextView hello = this.getView().findViewById(R.id.fragment_level_1_hello);
        mAnimator = ObjectAnimator.ofFloat(hello,"translationX",100f);
        mAnimator.setDuration(2000);
        return view;
    }


}
