package edu.hooapps.android.namegame.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import edu.hooapps.android.namegame.R;
import edu.hooapps.android.namegame.fragment.HomeFragment;

public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Always call super.onCreate() in the first line of onCreate for an activity
        // This call allows the super class to handle all initialization
        super.onCreate(savedInstanceState);

        // Specify the layout for the activity (this just has a FrameLayout in this case)
        setContentView(R.layout.activity_home);

        if (savedInstanceState == null) {
            // Add the fragment to the FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HomeFragment())
                    .commit();
        }
    }
}
