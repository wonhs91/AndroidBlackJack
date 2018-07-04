package com.example.stephen.androidblackjack;

import android.support.annotation.StringDef;

import com.example.stephen.androidblackjack.data.Hand;

/**
 * Created by stephen on 7/2/2018.
 */

public final class BlackJackUtils {

    public static final String BUST = "bust";
    public static final String BLACKJACK = "blackjack";
    public static final String ALIVE = "alive";

    @StringDef({
            BUST, BLACKJACK, ALIVE
    })
    public @interface HandState {}

    public static @HandState String checkHand(Hand hand) {
        if(hand.getHandValue() < 21) {
            return ALIVE;
        }
        else if(hand.getHandValue() > 21) {
            return BUST;
        }
        return BLACKJACK;
    }

}
