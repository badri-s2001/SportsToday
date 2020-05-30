/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.sportstoday;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SportsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Sports>> {

    private static final String LOG_TAG = SportsActivity.class.getName();

    private static final String NEWS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=f9ce430870eb4b9297d3e803e6355e10";
    /**
     * Constant value for the sports loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int SPORTS_LOADER_ID = 1;

    private SportsAdapter mAdapter;

    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports_activity);


        // Find a reference to the {@link ListView} in the layout
        ListView sportsListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new SportsAdapter(this, new ArrayList<Sports>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        sportsListView.setAdapter(mAdapter);

        sportsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sports sport = mAdapter.getItem(position);
                String sportUri = sport.getUrl();
                Intent i = new Intent(SportsActivity.this, WebViewActivity.class);
                i.putExtra(WebViewActivity.WEBSITE_ADDRESS, sportUri);
                startActivity(i);
            }
        });
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(SPORTS_LOADER_ID, null, this);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        sportsListView.setEmptyView(mEmptyStateTextView);

    }

    @Override
    public Loader<List<Sports>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new SportsLoader(this, NEWS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Sports>> loader, List<Sports> sports) {
        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (sports != null && !sports.isEmpty()) {
            mAdapter.addAll(sports);
        }
        // Set empty state text to display "No news found."
        mEmptyStateTextView.setText("No news found.");

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No news found."
        mEmptyStateTextView.setText("No news found.");

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Sports}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (sports != null && !sports.isEmpty()) {
            mAdapter.addAll(sports);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Sports>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}



