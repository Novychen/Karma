package com.example.karma;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


/**
 * Crack something?
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
        mCracks = new int[]{R.drawable.ic_crack_1, R.drawable.ic_crack_2, R.drawable.ic_crack_3, R.drawable.ic_crack_4, R.drawable.ic_crack_5, R.drawable.ic_crack_6, R.drawable.ic_crack_7, R.drawable.ic_crack_8};
        relativeLayout = view.findViewById(R.id.fragment_level_2);
        relativeLayout.setOnTouchListener(this);

        return view;
    }


    @Override
    public boolean onTouch(View _v, MotionEvent _event) {

        if(_event.getAction() == MotionEvent.ACTION_DOWN){
                Log.i(TAG, "pressure: " + _event.getPressure());
                if (mTouch > mCracks.length - 1) {
                    LevelCompleteDialog dialog = new LevelCompleteDialog(getActivity());
                    dialog.show();
                } else {
                    relativeLayout.setBackground(getActivity().getDrawable(mCracks[mTouch]));
                }
                mTouch++;
        }
      return false;
    }

    private void breakWindow() {

    }

}