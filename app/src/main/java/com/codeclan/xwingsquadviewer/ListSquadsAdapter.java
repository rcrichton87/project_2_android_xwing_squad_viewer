package com.codeclan.xwingsquadviewer;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListSquadsAdapter extends ArrayAdapter<Squad> {

    public ListSquadsAdapter(Context context, ArrayList<Squad> squads){
        super(context, 0, squads);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View listItemView = convertView;

        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.squads_item, parent, false);
        }

        Squad currentSquad = getItem(position);


    }

}
