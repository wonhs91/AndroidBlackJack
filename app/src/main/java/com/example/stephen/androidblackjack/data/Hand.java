package com.example.stephen.androidblackjack.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stephen on 6/27/2018.
 */

public class Hand {

    private List<Card> hand;

    public Hand() {
        hand = new ArrayList();
    }
    public void addCard(Card card) {
        hand.add(card);
    }

    /*
    public void hit() {

    }

    public void stand() {

    }

    public void doubleDown() {

    }*/

    public int getHandValue() {
        int value = 0;
        int numAce = 0;

        // calculate hand value without aces
        for (Card card : hand ) {
            if (card.getRank().equals(Card.Rank.ACE)) {
                numAce++;
                continue;
            }
            value += card.getRank().getValue();
        }

        // calculate aces' values based on other cards
        for (int i = 0; i < numAce; i++) {
            if (value > 11) {
                value += 1;
            }
            else {
                value += Card.Rank.ACE.getValue();
            }
        }

        return value;
    }

    public void clearHand() {
        hand.clear();
    }

}
