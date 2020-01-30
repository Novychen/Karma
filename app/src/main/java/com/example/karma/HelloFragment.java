package com.example.karma;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class HelloFragment extends Fragment {

    private AnimatorSet mAnimatorSet_01;
    private AnimatorSet mAnimatorSet_02;
    private AnimatorSet mAnimatorSet_03;
    private AnimatorSet mAnimatorSet_04;


    final static String TAG = "at.fhooe.mc.karma HelloFragment";

    public HelloFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {

        View view = _inflater.inflate(R.layout.fragment_hello, _container, false);
        TextView hello = view.findViewById(R.id.activity_level_1_hello);
        animate(hello);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    private void animate (TextView _hello){

            Smartphone smartphone = Smartphone.getInstance(getActivity());

            float height = smartphone.getHeightInPixels();
            float width = smartphone.getWidthInPixels();

            _hello.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            float widthText = _hello.getMeasuredWidth();
            float heightText = _hello.getMeasuredWidth();

            Log.i(TAG, "Device Height: " + height + "\n" +
                    "Device Width: " + width + "\n" +
                    "View Height: " + _hello.getHeight() + "\n" +
                    "View Width: " + _hello.getMeasuredWidth() + "\n");

            // Moving Textview "Hello" to the top with a rotation
            ObjectAnimator oA_01 = ObjectAnimator.ofFloat(_hello, "translationX", width / 2 - widthText / 2);
            ObjectAnimator oA_02 = ObjectAnimator.ofFloat(_hello, "translationY", (-height / 2 + heightText / 2));
            ObjectAnimator oA_03 = ObjectAnimator.ofFloat(_hello, "rotation", 180);

            mAnimatorSet_01 = new AnimatorSet();
            mAnimatorSet_01.playTogether(oA_01, oA_02, oA_03);
            mAnimatorSet_01.setDuration(500);

            // Moving TextView "Hello" to the right with a rotation
            ObjectAnimator oA_05 = ObjectAnimator.ofFloat(_hello, "rotation", 270);
            ObjectAnimator oA_06 = ObjectAnimator.ofFloat(_hello, "translationX", (width - widthText));
            ObjectAnimator oA_07 = ObjectAnimator.ofFloat(_hello, "translationY", (0));

            mAnimatorSet_02 = new AnimatorSet();
            mAnimatorSet_02.playTogether(oA_05, oA_06, oA_07);
            mAnimatorSet_02.setDuration(500);

            // Moving TextView "Hello" to the Bottom with a rotation
            ObjectAnimator oA_08 = ObjectAnimator.ofFloat(_hello, "rotation", 360);
            ObjectAnimator oA_09 = ObjectAnimator.ofFloat(_hello, "translationX", width / 2 - widthText / 2);
            ObjectAnimator oA_10 = ObjectAnimator.ofFloat(_hello, "translationY", (height / 2));

            mAnimatorSet_03 = new AnimatorSet();
            mAnimatorSet_03.playTogether(oA_08, oA_09, oA_10);
            mAnimatorSet_03.setDuration(500);

            // Moving TextView "Hello" to the center, rotate it and make it bigger
            ObjectAnimator oA_11 = ObjectAnimator.ofFloat(_hello, "rotation", 0);
            ObjectAnimator oA_12 = ObjectAnimator.ofFloat(_hello, "translationX", width / 2 - widthText / 2);
            ObjectAnimator oA_13 = ObjectAnimator.ofFloat(_hello, "translationY", 0);
            ObjectAnimator oA_14 = ObjectAnimator.ofFloat(_hello, "scaleX", 1.5f);
            ObjectAnimator oA_15 = ObjectAnimator.ofFloat(_hello, "scaleY", 1.5f);

            mAnimatorSet_04 = new AnimatorSet();
            mAnimatorSet_04.playTogether(oA_11, oA_12, oA_13, oA_14, oA_15);
            mAnimatorSet_04.setDuration(500);
        }
}
