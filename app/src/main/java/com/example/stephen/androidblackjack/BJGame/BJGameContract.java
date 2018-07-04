package com.example.stephen.androidblackjack.BJGame;

        import com.example.stephen.androidblackjack.data.Card;

/**
 * Created by stephen on 6/27/2018.
 */

public interface BJGameContract {

    interface View {
        void setPresenter(Presenter presenter);
        void updatePlayerHandTv(Card card);
        void updateDealerHandTv(Card card);
        void clearPlayerHandTv();
        void clearDealerHandTv();
        void enableUserDecisions();
        void disableUserDecisions();

    }

    interface Presenter {
        void start();
        void hit();
        void stand();
        void doubleDown();
    }

}
