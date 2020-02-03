package com.example.karma;

public interface Riddle {
    String[] mColor = {"#D51116","#C51262","#6C4595","#4C4394","#2B4792","#3B5FA9","#328ACA","#08B7D3","#30B39F","#48AE54","#76B82A","#ACC90F","#FFD600","#F8A912","#ED6D1D","#DD2E14"};
    int[] mRating = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    void nextActivity();
    long getTime();
    void setRating(int _rate);
    int getRating();
}
