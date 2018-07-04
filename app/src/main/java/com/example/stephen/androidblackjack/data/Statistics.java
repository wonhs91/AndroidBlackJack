package com.example.stephen.androidblackjack.data;

/**
 * Created by stephen on 7/2/2018.
 */

public class Statistics {

    private static Statistics INSTANCE = null;

    private Statistics() {

    }

    public static Statistics getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Statistics();
        }
        return INSTANCE;
    }

    private int numWin;
    private int numLoss;
    private int numPush;

    public int getNumWins() {
        return numWin;
    }

    public void setNumWins(int numWin) {
        this.numWin = numWin;
    }

    public int getNumLoss() {
        return numLoss;
    }

    public void setNumLoss(int numLoss) {
        this.numLoss = numLoss;
    }

    public int getNumPush() {
        return numPush;
    }

    public void setNumPush(int numPush) {
        this.numPush = numPush;
    }
}
