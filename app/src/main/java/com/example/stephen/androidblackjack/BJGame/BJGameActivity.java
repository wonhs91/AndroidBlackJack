package com.example.stephen.androidblackjack.BJGame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.stephen.androidblackjack.R;

import butterknife.BindView;

public class BJGameActivity extends AppCompatActivity {

    private static final String TAG = BJGameActivity.class.getName();
    private static final int NUM_PAGES = 2;

    private static final int GAME_PAGE = 0;
    private static final int STAT_PAGE = 1;

    @BindView(R.id.content_vp)
    ViewPager contentPager;

    private PagerAdapter contentPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bjgame);

        contentPager = (ViewPager) findViewById(R.id.content_vp);
        contentPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        contentPager.setAdapter(contentPagerAdapter);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position) {
                case BJGameActivity.GAME_PAGE:
                    BJGameFragment bjGameFragment = BJGameFragment.newInstance();
                    new BJGamePresenter(bjGameFragment);
                    return bjGameFragment;
                case BJGameActivity.STAT_PAGE:
                    BJGameFragment bjGameFragment1 = BJGameFragment.newInstance();
                    new BJGamePresenter(bjGameFragment1);
                    return bjGameFragment1;
                default:
            }
                    return null;
        }

        @Override
        public int getCount() {
            return BJGameActivity.NUM_PAGES;
        }
    }
}
