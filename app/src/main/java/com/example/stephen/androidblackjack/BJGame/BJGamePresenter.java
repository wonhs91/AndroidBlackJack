package com.example.stephen.androidblackjack.BJGame;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.util.Log;

import com.example.stephen.androidblackjack.BlackJackUtils;
import com.example.stephen.androidblackjack.data.Card;
import com.example.stephen.androidblackjack.data.Hand;
import com.example.stephen.androidblackjack.data.Shoe;
import com.example.stephen.androidblackjack.data.StatsData;

/**
 * Created by stephen on 6/27/2018.
 */

public class BJGamePresenter implements BJGameContract.Presenter {

    private static final String TAG = BJGameFragment.class.getName();

    @NonNull
    private final Hand playerHand;

    @NonNull
    private final Hand dealerHand;

    @NonNull
    private final Shoe shoe;

    @NonNull
    private final BJGameContract.View view;

    private final StatsData stats;

    private static final String WIN = "win";
    private static final String LOSS = "loss";
    private static final String PUSH = "push";
    @StringDef({
            WIN, LOSS, PUSH
    })
    private @interface Result {}

    public BJGamePresenter(@NonNull BJGameContract.View view) {
        Log.i(TAG, "constructor");
        this.view = view;

        shoe = new Shoe();
        playerHand = new Hand();
        dealerHand = new Hand();

        stats = StatsData.getInstance();
        view.setPresenter(this);
    }

    public void start() {
        Log.i(TAG, "new game started");
        shoe.generateShoe();
        startRound();
    }

    public void hit() {
        Log.i(TAG, "hit");
        if (!BlackJackUtils.checkHand(playerHand).equals(BlackJackUtils.ALIVE)) {
            //TODO show toast to notify this action cannot be done..
        }
        addToPlayerHand(shoe.drawACard());

        if (BlackJackUtils.checkHand(playerHand).equals(BlackJackUtils.BUST)) {
            roundOver(LOSS);
        }
        else if(BlackJackUtils.checkHand(playerHand).equals(BlackJackUtils.BLACKJACK)){
            stand();
        }

    }

    public void stand() {
        Log.i(TAG, "stand");
        view.disableUserDecisions();
        Log.v(TAG, String.format("player's hand value: %d", playerHand.getHandValue()));
        dealerPlay();
    }

    private void dealerPlay() {
        while (dealerHand.getHandValue() < 17) {
            addToDealerHand(shoe.drawACard());
        }

        if (BlackJackUtils.checkHand(dealerHand).equals(BlackJackUtils.BUST)) {
            //dealer busted
            // at the moment when user hits and is busted, dealer doesn't play so the dealer loses
            roundOver(WIN);
        }
        else {
            if (dealerHand.getHandValue() > playerHand.getHandValue()) {
                roundOver(LOSS);
            }
            else if (dealerHand.getHandValue() < playerHand.getHandValue()) {
                roundOver(WIN);
            }
            else {
                roundOver(PUSH);
            }
        }
    }

    public void doubleDown() {
        Log.i(TAG, "doubleDown");
        addToPlayerHand(shoe.drawACard());
        if (BlackJackUtils.checkHand(playerHand).equals(BlackJackUtils.BUST)) {
            roundOver(LOSS);
        }
        else {
            stand();
        }
    }

    private void roundOver(@Result String result) {
        if (result.equals(WIN)) {
            stats.setNumWins(stats.getNumWins() + 1);
            Log.v(TAG, "Player Won");
        }
        else if (result.equals(LOSS)) {
            stats.setNumLoss(stats.getNumLoss() + 1);
            Log.v(TAG, "Player Lost");
        }
        else {
            stats.setNumPush(stats.getNumPush() + 1);
            Log.v(TAG, "Player Pushed");
        }
        shoe.checkForRenerate();
        startRound();
    }



    private void startRound() {
        Log.i(TAG, "new round start");
        clearHands();
        view.disableUserDecisions();
        dealCards();
        view.enableUserDecisions();
    }

    private void dealCards() {
        addToPlayerHand(shoe.drawACard());
        addToDealerHand(shoe.drawACard());

        addToPlayerHand(shoe.drawACard());
        addToDealerHand(shoe.drawACard());
    }

    private void clearHands() {
        playerHand.clearHand();
        dealerHand.clearHand();
        view.clearPlayerHandTv();
        view.clearDealerHandTv();
    }

    private void addToPlayerHand(Card card) {
        Log.v(TAG, String.format("player got (%s, %s)", card.getRank().toString(), card.getSuit().toString()));
        playerHand.addCard(card);
        view.updatePlayerHandTv(card);
    }

    private void addToDealerHand(Card card) {
        Log.v(TAG, String.format("dealer got (%s, %s)", card.getRank().toString(), card.getSuit().toString()));
        dealerHand.addCard(card);
        view.updateDealerHandTv(card);
    }

}
