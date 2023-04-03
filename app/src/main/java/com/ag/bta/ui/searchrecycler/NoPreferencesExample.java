package com.ag.bta.ui.searchrecycler;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ag.bta.main.R;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceResultListener;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceResult;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceFragment;

import com.ag.bta.ui.searchrecycler.lib.SearchConfiguration;

/**
 * This file demonstrates how to use the library without actually displaying the preference
 */
public class NoPreferencesExample extends AppCompatActivity implements SearchPreferenceResultListener {
    private SearchPreferenceFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SearchConfiguration config = new SearchConfiguration(this);
        config.setFragmentContainerViewId(android.R.id.content);
        config.index(R.xml.preferences);
        config.setSearchBarEnabled(false);
        config.setFuzzySearchEnabled(false);

        fragment = config.showSearchFragment();
        fragment.setHistoryClickListener(new SearchPreferenceFragment.HistoryClickListener() {
            @Override
            public void onHistoryEntryClicked(String entry) {
                Log.d("NoPreferencesExample", "History entry clicked: " + entry);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        fragment.setSearchTerm("Checkbox");
    }

    @Override
    public void onSearchResultClicked(@NonNull final SearchPreferenceResult result) {
        Toast.makeText(this, result.getKey(), Toast.LENGTH_LONG).show();
    }
}
