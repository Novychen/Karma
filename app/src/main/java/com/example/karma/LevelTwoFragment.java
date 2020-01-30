package com.example.karma;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Objects;


/**
 * Crack something
 */
public class LevelTwoFragment extends Fragment implements View.OnTouchListener {


    final static String TAG = "at.fhooe.mc.karma LevelTwoFragment";
    private int[] mCracks;
    private int mTouch;
    RelativeLayout relativeLayout;


    public LevelTwoFragment() {
        // Required empty public constructor
    }

    private void animateRandom(ImageView _catchItem) {

        Smartphone smartphone = Smartphone.getInstance(getActivity());

        float height = smartphone.getHeightInPixels();
        float width = smartphone.getWidthInPixels();
        mCracks = new int[]{R.drawable.ic_star_black, R.drawable.ic_star_black, R.drawable.ic_star_black, R.drawable.ic_star_black};
        for (int i = 0; i < 20; i++) {
            double y = Math.random() % height;
            double x = Math.random() % width;
            ObjectAnimator oA_01 = ObjectAnimator.ofFloat(_catchItem, "translationY", (float) x);
            ObjectAnimator oA_02 = ObjectAnimator.ofFloat(_catchItem, "translationY", (float) y);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(oA_01, oA_02);
            animatorSet.setDuration(500);
            animatorSet.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container,
                             Bundle _savedInstanceState) {
        View view = _inflater.inflate(R.layout.fragment_level_two, _container, false);
        mCracks = new int[]{R.drawable.ic_crack_1, R.drawable.ic_crack_2, R.drawable.ic_crack_3, R.drawable.ic_crack_4,
                R.drawable.ic_crack_5, R.drawable.ic_crack_6, R.drawable.ic_crack_7, R.drawable.ic_crack_8,
                R.drawable.ic_crack_9, R.drawable.ic_crack_10, R.drawable.ic_crack_11, R.drawable.ic_crack_12,
                R.drawable.ic_crack_13, R.drawable.ic_crack_14, R.drawable.ic_crack_15, R.drawable.ic_crack_16};

        relativeLayout = view.findViewById(R.id.fragment_level_2);
        relativeLayout.setOnTouchListener(this);
        relativeLayout.setBackground(getActivity().getDrawable(R.drawable.ic_crack_0));
        return view;
    }


    @Override
    public boolean onTouch(View _v, MotionEvent _event) {

        float x = _event.getRawX();
        float y = _event.getRawY();

        float height = Smartphone.getInstance(getActivity()).getHeightInPixels();
        float width = Smartphone.getInstance(getActivity()).getWidthInPixels();

        int tap = 50 + (20* mTouch +1);

        if(_event.getAction() == MotionEvent.ACTION_DOWN){
            if(x > (width/2 - tap) && x < (width/2 + tap) && y > (height/2 - tap) && y < (height/2 + tap) ) {
                if (mTouch >= mCracks.length) {
                    Drawable d = Objects.requireNonNull(getActivity()).getDrawable(mCracks[mCracks.length - 1]);
                    if (d != null) {
                        d.setAlpha(0);
                        relativeLayout.setBackground(d);
                    }
                    LevelCompleteDialog dialog = new LevelCompleteDialog(getActivity());
                    dialog.show();
                } else {
                    relativeLayout.setBackground(getActivity().getDrawable(mCracks[mTouch]));
                }
                mTouch++;
            }
        }
      return false;
    }
}