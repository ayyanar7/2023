package com.ag.bta.ui.searchrecycler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import com.ag.bta.ui.searchrecycler.lib.SearchConfiguration;
import com.ag.bta.ui.searchrecycler.lib.SearchPreference;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceResult;
import com.ag.bta.ui.searchrecycler.lib.SearchPreferenceResultListener;
import  com.ag.bta.main.R;

/**
 * This file contains a minimal working example for the library
 */
public class SimpleExample extends AppCompatActivity implements SearchPreferenceResultListener {
    private PrefsFragment prefsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefsFragment = new PrefsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, prefsFragment).commit();
    }

    @Override
    public void onSearchResultClicked(@NonNull SearchPreferenceResult result) {
        result.closeSearchPage(this);
        result.highlight(prefsFragment);
    }

    public static class PrefsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            addPreferencesFromResource(R.xml.preferences);

            SearchPreference searchPreference = (SearchPreference) findPreference("searchPreference");
            SearchConfiguration config = searchPreference.getSearchConfiguration();
            config.setActivity((AppCompatActivity) getActivity());
            config.index(R.xml.preferences);
        }
    }
}
