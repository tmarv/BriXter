package brix.geektimch.brixter;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class TheGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        // getSupportActionBar().hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
    }
}

