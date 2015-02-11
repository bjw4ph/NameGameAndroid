package edu.hooapps.android.namegame.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.hooapps.android.namegame.R;
import edu.hooapps.android.namegame.data.Data;

/**
 * A placeholder fragment containing a simple view.
 */
public class GameFragment extends Fragment {

    private ArrayList<NameImagePair> itemList;

    public GameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        // Load the data into the ArrayList
        loadData();

        return rootView;
    }

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
