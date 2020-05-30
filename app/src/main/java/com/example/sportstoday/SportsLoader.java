package com.example.sportstoday;
import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of sports news by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class SportsLoader extends AsyncTaskLoader<List<Sports>> {

    /** Tag for log messages */
    private static final String LOG_TAG = SportsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link SportsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public SportsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Sports> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of sports.
        List<Sports> sports = QueryUtils.fetchSportsData(mUrl);
        return sports;
    }
}