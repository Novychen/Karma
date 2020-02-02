package com.example.karma;

public interface Riddle {
    long start = 0;
    long end = 0;

    void nextActivity();
    void startTimer();
    void endTimer();
}
