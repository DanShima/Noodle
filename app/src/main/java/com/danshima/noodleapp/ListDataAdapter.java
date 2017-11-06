package com.danshima.noodleapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Giddy on 05/11/2017.
 */

public class ListDataAdapter extends ArrayAdapter {
    List<Noodle> mlist;

    public ListDataAdapter(Context context, int resource, List<Noodle> list )
    {
        super(context, resource);
        mlist = list;
    }

    static class LayoutHandler{
        TextView name, description, restaurantInfo;
        ImageView image;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mview = convertView;
        LayoutHandler layoutHandler;
        if(mview==null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mview = layoutInflater.inflate(R.layout.activity_detail,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.name = (TextView)mview.findViewById(R.id.name);
            layoutHandler.description = (TextView)mview.findViewById(R.id.description);
            layoutHandler.image = mview.findViewById(R.id.image);
            layoutHandler.restaurantInfo = mview.findViewById(R.id.restaurant_info);

            mview.setTag(layoutHandler);
        }else {
            layoutHandler = (LayoutHandler) mview.getTag();
        }
        Noodle noodle = (Noodle)this.getItem(position);
        layoutHandler.name.setText(noodle.getName());
        layoutHandler.description.setText(noodle.getDescription());
        layoutHandler.restaurantInfo.setText(noodle.getSuggestedRestaurant());
        layoutHandler.image.setImageResource(noodle.getPhotoID());
        return mview;
    }
}

