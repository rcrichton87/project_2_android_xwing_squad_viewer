package com.codeclan.xwingsquadviewer;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

        listItemView.setTag(currentSquad);
        Log.d("setTag Squad", currentSquad.toString());

        ImageView factionSymbol = (ImageView) listItemView.findViewById(R.id.faction_symbol);
        factionSymbol.setImageResource(currentSquad.getFactionSymbol());
        factionSymbol.setTag(currentSquad);

        TextView squadName = (TextView) listItemView.findViewById(R.id.squad_name);
        squadName.setText(currentSquad.getName());
        squadName.setTag(currentSquad);


        TextView squadWinLossRatio = (TextView) listItemView.findViewById(R.id.squad_wins_losses);
        squadWinLossRatio.setText(currentSquad.getWinLossRatio().toString());
        squadWinLossRatio.setTag(currentSquad);

        if (currentSquad.getWinLossRatio() < 0 ) {
            squadWinLossRatio.setTextColor(Color.parseColor("#df0000"));
        }
        if (currentSquad.getWinLossRatio() > 0 ) {
            squadWinLossRatio.setText("+" + currentSquad.getWinLossRatio().toString());
            squadWinLossRatio.setTextColor(Color.parseColor("#13df00"));
        }

        return listItemView;


    }

}
