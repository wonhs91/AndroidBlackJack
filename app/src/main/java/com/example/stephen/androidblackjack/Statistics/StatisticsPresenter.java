package com.example.stephen.androidblackjack.Statistics;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.stephen.androidblackjack.data.StatsData;

/**
 * Created by stephen on 7/6/2018.
 */

public class StatisticsPresenter implements StatisticsContract.Presenter {

    private static final String TAG = StatisticsPresenter.class.getName();
    private StatisticsContract.View view;
    private StatsData stats;

    public StatisticsPresenter(@NonNull StatisticsContract.View view) {
        Log.i(TAG, "constructor");
        this.view = view;

        stats = StatsData.getInstance();
        view.setPresenter(this);
    }

    @Override
    public void start() {
        updateStat();
    }

    @Override
    public void updateStat() {
        view.setWinPushLossView(String.valueOf(stats.getNumWins()), String.valueOf(stats.getNumPush()), String.valueOf(stats.getNumLoss()));
    }

}
