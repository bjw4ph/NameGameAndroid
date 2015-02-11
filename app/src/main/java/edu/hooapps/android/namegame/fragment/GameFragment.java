package edu.hooapps.android.namegame.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import edu.hooapps.android.namegame.R;
import edu.hooapps.android.namegame.data.Data;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameFragment extends Fragment {

    private ArrayList<NameImagePair> itemList;

    // Views
    ImageView questionImageView;
    Button option1Button;
    Button option2Button;
    Button option3Button;
    Button option4Button;

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        // Load the data into the ArrayList
        loadData();

        // Retrieve the views from the layout parent (rootView)
        /* NOTE: rootView.findViewById() returns a generic view object
            Each view that is returned has to be cast to its appropriate type
            This is allowed since all Views extend (are a subclass of) View
         */
        questionImageView = (ImageView) rootView.findViewById(R.id.image);
        option1Button = (Button) rootView.findViewById(R.id.button_option_1);
        option2Button = (Button) rootView.findViewById(R.id.button_option_2);
        option3Button = (Button) rootView.findViewById(R.id.button_option_3);
        option4Button = (Button) rootView.findViewById(R.id.button_option_4);

        // Set tags for each view to represent index of in list
        option1Button.setTag(0);
        option2Button.setTag(1);
        option3Button.setTag(2);
        option4Button.setTag(3);

        // Configure data in views
        setUpNextQuestion();

        return rootView;
    }

    /**
     * Display the data on the views and setup the question logic
     */
    private void setUpNextQuestion() {
        // Shuffle the list of items
        Collections.shuffle(itemList);

        // Generate a random number 0-3 that serves as the index of the correct answer
        final int correctIndex = (int)(Math.random()*4);

        // Load the names into the Buttons
        /*
        NOTE: Once views have been retrieved from the layout via 'findViewById()', they are
            modified in the code as normal objects. Changing layout-specific properties tends
            to be difficult, but modifying state is the same as any other object
         */
        option1Button.setText(itemList.get(0).name);
        option2Button.setText(itemList.get(1).name);
        option3Button.setText(itemList.get(2).name);
        option4Button.setText(itemList.get(3).name);

        // Load the image from resources into the ImageView
        questionImageView.setImageResource(itemList.get(correctIndex).imageResId);

        // Make a generic listener for the buttons
        /*
        NOTE: Using a generic listener is possible since the tags for each button have been set
         */
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correctIndex == v.getTag()) {
                    // Correct answer
                    onCorrectAnswer();
                } else {
                    // Incorrect answer
                    onIncorrectAnswer();
                }
            }
        };

        // Set the listeners for each of the buttons
        option1Button.setOnClickListener(buttonListener);
        option2Button.setOnClickListener(buttonListener);
        option3Button.setOnClickListener(buttonListener);
        option4Button.setOnClickListener(buttonListener);
    }

    /**
     * Notify the user that the answer was correct
     * Automatically load the next question (for now)
     */
    private void onCorrectAnswer() {
        Toast.makeText(this.getActivity(), "Correct", Toast.LENGTH_SHORT).show();
        setUpNextQuestion();
    }

    /**
     * Notify the user that the answer was incorrect
     * Automatically load the next question (for now)
     */
    private void onIncorrectAnswer() {
        Toast.makeText(this.getActivity(), "Incorrect", Toast.LENGTH_SHORT).show();
        setUpNextQuestion();
    }

    /**
     * Load the question data from various sources in an ArrayList in the fragment
     */
    private void loadData() {
        // Init the blank ArrayList
        itemList = new ArrayList<>();

        // NOTE: Load this from JSON data in the future

        // Load the image resources from the data class
        int[] imageResIds = Data.imageResIds;

        // Load the names from the String resources
        String[] names = getResources().getStringArray(R.array.name_list);

        // Populate the ArrayList with NameImagePairs
        NameImagePair item;
        for (int i = 0; i < names.length; i++) {
            // Create the item and add the appropriate data
            item = new NameImagePair();
            item.name = names[i];
            item.imageResId = imageResIds[i];

            // Add the item to the ArrayList
            itemList.add(item);
        }
    }

    class NameImagePair {
        String name;
        int imageResId;
    }
}
