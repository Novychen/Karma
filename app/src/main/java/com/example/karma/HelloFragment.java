package com.example.karma;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class HelloFragment extends Fragment {


    public HelloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        // Inflate the layout for this fragment
        return _inflater.inflate(R.layout.fragment_hello, _container, false);
    }
}
