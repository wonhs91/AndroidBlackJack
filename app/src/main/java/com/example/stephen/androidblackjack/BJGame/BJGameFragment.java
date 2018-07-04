package com.example.stephen.androidblackjack.BJGame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.stephen.androidblackjack.R;
import com.example.stephen.androidblackjack.data.Card;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BJGameFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BJGameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BJGameFragment extends Fragment implements BJGameContract.View{

    private BJGameContract.Presenter presenter;

    private static final String TAG = BJGameFragment.class.getName();

    @BindView(R.id.playerHand_tv) TextView playerHandTv;
    @BindView(R.id.dealerHand_tv) TextView dealerHandTv;
    @BindView(R.id.hit_btn) Button hitBtn;
    @BindView(R.id.stand_btn) Button standBtn;
    @BindView(R.id.double_btn) Button doubleBtn;

    @Override
    public void updatePlayerHandTv(Card card) {
        playerHandTv.append(" `" + card.getSuit().toString() + ", " + card.getRank().getValue() + "` ");
    }

    @Override
    public void updateDealerHandTv(Card card) {
        dealerHandTv.append(" `" + card.getSuit().toString() + ", " + card.getRank().getValue() + "` ");
    }

    public void clearPlayerHandTv() {
        playerHandTv.setText("");
    }

    public void clearDealerHandTv() {
        dealerHandTv.setText("");
    }

    public BJGameFragment() {
        // Required empty public constructor
    }

    public static BJGameFragment newInstance() {
        Log.i(TAG, "new instance created");
        BJGameFragment fragment = new BJGameFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate method");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView method");
        View view = inflater.inflate(R.layout.fragment_bjgame, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume method");
        super.onResume();
        presenter.start();
    }

    public void enableUserDecisions() {
        hitBtn.setEnabled(true);
        standBtn.setEnabled(true);
        doubleBtn.setEnabled(true);
    }

    public void disableUserDecisions() {
        hitBtn.setEnabled(false);
        standBtn.setEnabled(false);
        doubleBtn.setEnabled(false );
    }

    @OnClick(R.id.hit_btn)
    public void onHitBtnClick() {
        presenter.hit();
    }

    @OnClick(R.id.stand_btn)
    public void onStandBtnClick() {
        presenter.stand();
    }

    @OnClick(R.id.double_btn)
    public void onDoubleBtnClick() {
        presenter.doubleDown();
    }

    @Override
    public void setPresenter(BJGameContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
