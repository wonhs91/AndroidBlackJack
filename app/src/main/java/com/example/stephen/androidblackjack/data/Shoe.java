package com.example.stephen.androidblackjack.data;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Created by stephen on 6/27/2018.
 */

public class Shoe {


    private static final String TAG = Shoe.class.getName();

    final static int DEAFULT_NUM_DECK = 6;
    final static double DEFAULT_SHUFFLE_POINT = .20;

    private final int numDeck = DEAFULT_NUM_DECK ;
    private final List<Card> shoe;

    public Shoe() {
        this.shoe = new ArrayList();
        populateShoe(numDeck);
        Collections.shuffle(shoe);
    }

    public void generateShoe() {
        populateShoe(numDeck);
        Collections.shuffle(shoe);
    }

    private void populateShoe(int numDeck) {
        shoe.clear();

        for (int i = 0; i < numDeck; i++) {
            for (Card.Rank rank : Card.Rank.values()) {
                for (Card.Suit suit : Card.Suit.values()) {
                    shoe.add(new Card(rank, suit));
                }
            }
        }
    }

    public Card drawACard() {
        if (!shoe.isEmpty()) {
            return shoe.remove(shoe.size() - 1);
        }
        else {
            Log.wtf(TAG, "shoe is empty?");
            throw new EmptyStackException();
        }
    }

    public void checkForRenerate() {
        if (shoe.size() < (11 * 4 * numDeck) * DEFAULT_SHUFFLE_POINT) {
            Log.i(TAG, "regenerating + shuffling shoe");
            generateShoe();
        }
    }


}
