package com.example.stephen.androidblackjack.data;

/**
 * Created by stephen on 7/2/2018.
 */

public final class StatsData {

    private int numWin;
    private int numLoss;
    private int numPush;

    private static StatsData INSTANCE = null;

    private StatsData() {

    }

    public static StatsData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StatsData();
        }
        return INSTANCE;
    }

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
