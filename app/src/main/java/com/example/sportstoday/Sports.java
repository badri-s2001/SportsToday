package com.example.sportstoday;

public class Sports
{

    private String mImageUrl;
    private String mDetails;
    private String mTime;
    private String mUrl;

    public Sports (String imageurl, String details, String time, String url)
    {
        mImageUrl = imageurl;
        mDetails = details;
        mTime = time;
        mUrl = url;
    }

    public String getImageUrl()
    {
        return mImageUrl;
    }

    public String getDetails()
    {
        return mDetails;
    }

    public String getTime()
    {
        return mTime;
    }

    public String getUrl()
    {
        return mUrl;
    }

}
