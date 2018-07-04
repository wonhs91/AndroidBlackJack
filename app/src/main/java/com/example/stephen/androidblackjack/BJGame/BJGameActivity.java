package com.example.stephen.androidblackjack.BJGame;


        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;

        import com.example.stephen.androidblackjack.R;

public class BJGameActivity extends AppCompatActivity {

    private static final String TAG = BJGameActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bjgame);

        BJGameFragment bjGameFragment = (BJGameFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (bjGameFragment == null) {
            bjGameFragment = BJGameFragment.newInstance();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contentFrame, bjGameFragment);
        transaction.commit();

        new BJGamePresenter(bjGameFragment);
    }
}
