package com.example.stephen.androidblackjack.Statistics;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stephen.androidblackjack.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stephen on 7/6/2018.
 */

public class StatisticsFragment extends Fragment implements StatisticsContract.View{

    private static final String TAG = StatisticsFragment.class.getName();

    private StatisticsContract.Presenter presenter;

    @BindView(R.id.win_tv)
    TextView winTv;

    @BindView(R.id.push_tv)
    TextView pushTv;

    @BindView(R.id.loss_tv)
    TextView lossTv;


    public static StatisticsFragment newInstance() {
        Log.i(TAG, "new instance created");
        StatisticsFragment fragment = new StatisticsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView method");
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume method");
        super.onResume();
        presenter.start();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            presenter.updateStat();
        }
    }



    @Override
    public void setPresenter(StatisticsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setWinPushLossView(String winNum, String pushNum, String lossNum) {
        winTv.setText(winNum);
        pushTv.setText(pushNum);
        lossTv.setText(lossNum);
    }
}
