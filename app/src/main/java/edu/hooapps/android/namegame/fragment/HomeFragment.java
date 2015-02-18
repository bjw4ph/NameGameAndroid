package edu.hooapps.android.namegame.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.hooapps.android.namegame.R;
import edu.hooapps.android.namegame.activity.GameActivity;

/* NOTE: Extend android.support.v4.app.Fragment rather than
android.app.Fragment for better compatibility and improved features
 */
public class HomeFragment extends Fragment {

    Button playButton;
    Button cameraButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Fetch views
        playButton = (Button) rootView.findViewById(R.id.button_play_game);
        cameraButton = (Button) rootView.findViewById(R.id.button_take_picture);

        // Bind listeners
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameActivity();
            }
        });

        return rootView;
    }

    // Launch the GameActivity to start playing the game
    private void startGameActivity() {
        // Create a new Intent to open GameActivity
        Intent intent = new Intent(this.getActivity(), GameActivity.class);

        // Call the Intent to start GameActivity
        startActivity(intent);
    }

}
