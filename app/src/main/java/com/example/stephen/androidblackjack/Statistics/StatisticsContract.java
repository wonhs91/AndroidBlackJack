package com.example.stephen.androidblackjack.Statistics;

/**
 * Created by stephen on 7/6/2018.
 */

public interface StatisticsContract {
    interface View {
        void setPresenter(StatisticsContract.Presenter presenter);
        void setWinPushLossView(String winNum, String pushNum, String lossNum);
    }

    interface Presenter {
        void start();
        void updateStat();
    }
}
