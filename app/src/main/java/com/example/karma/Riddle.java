package com.example.karma;

/**interface for the levels*/
public interface Riddle {
    String[] mColor = {"#D51116","#C51262","#6C4595","#4C4394","#2B4792","#3B5FA9","#328ACA","#08B7D3","#30B39F","#48AE54","#76B82A","#ACC90F","#FFD600","#F8A912","#ED6D1D","#DD2E14"};
    /**gets the next activity for the complete Dialog*/
    void nextActivity();
    /**gets the time used for solving a level*/
    long getTime();
    /**sets rating of the level
     * @param _rate rating of the level calculate based of the time returned by getTime()*/
    void setRating(int _rate);
    /**gets rating of the level*/
    int getRating();
}
