package com.example.sportstoday;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SportsAdapter extends ArrayAdapter<Sports>
{
    public SportsAdapter(Activity context, ArrayList<Sports> words)
    {
        super(context, 0, words);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        Sports currentSport = getItem(position);

        String imgurl = currentSport.getImageUrl();
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.sports_img);
        Picasso.get().load(imgurl).resize(100,100).centerCrop().into(imageView);

        String details = currentSport.getDetails();
        TextView detailsView =  (TextView) listItemView.findViewById(R.id.sports_details);
        detailsView.setText(details);

        String date = currentSport.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("h:mm a");
        Date parseDate = new Date();
        try {
            parseDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedTime = sdf1.format(parseDate);
        TextView timeView = (TextView) listItemView.findViewById(R.id.sports_time);
        timeView.setText(formattedTime);

        return listItemView;
    }




}