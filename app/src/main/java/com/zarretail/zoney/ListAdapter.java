package com.zarretail.zoney;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Admin on 11-11-2014.
 */
public class ListAdapter extends ArrayAdapter<Item> {
    int layoutResourceId;
    ArrayList<Item> data = new ArrayList<Item>();
    private Context context;


    public ListAdapter(Context context, int resources, ArrayList<Item> data) {
        super(context, resources, data);

        this.layoutResourceId = resources;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new WeatherHolder();
//            holder.txtValue = (EditText)row.findViewById(R.id.txtView);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            Typeface myTypeface = Typeface.createFromAsset(context.getAssets(),"fonts/NexaRegular.otf");
            holder.txtTitle.setTypeface(myTypeface);

            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }


        Item weather = data.get(position);
        holder.txtTitle.setText(weather.title);
//        holder.txtValue.setText(weather.textValue);

        return row;
    }

    static class WeatherHolder
    {
//        EditText txtValue;
        TextView txtTitle;
    }

}
