package com.codeclan.xwingsquadviewer;

import android.content.Context;
import android.graphics.Color;
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

        ImageView factionSymbol = (ImageView) listItemView.findViewById(R.id.faction_symbol);
        factionSymbol.setImageResource(currentSquad.getFactionSymbol());

        TextView squadName = (TextView) listItemView.findViewById(R.id.squad_name);
        squadName.setText(currentSquad.getName());

        TextView squadTotalGames = (TextView) listItemView.findViewById(R.id.squad_total_games);
        Integer totalGames = currentSquad.getWins() + currentSquad.getLosses();
        squadTotalGames.setText("Total games: " + totalGames.toString());

        TextView squadWins = (TextView) listItemView.findViewById(R.id.squad_wins);
        squadWins.setText("Wins: " + currentSquad.getWins().toString());

        TextView squadLosses = (TextView) listItemView.findViewById(R.id.squad_losses);
        squadLosses.setText("Losses: " + currentSquad.getLosses().toString());

        TextView squadWinLossRatio = (TextView) listItemView.findViewById(R.id.squad_wins_losses);
        squadWinLossRatio.setText(currentSquad.getWinLossRatio().toString());

        if (currentSquad.getWinLossRatio() < 0 ) {
            squadWinLossRatio.setTextColor(Color.parseColor("#df0000")); //set to red if negative
        }
        if (currentSquad.getWinLossRatio() > 0 ) {
            squadWinLossRatio.setText("+" + currentSquad.getWinLossRatio().toString()); // put a + in front of the number
            squadWinLossRatio.setTextColor(Color.parseColor("#13df00")); //set to green if positive
        }
        if ((currentSquad.getWinLossRatio() == 0 )){
            squadWinLossRatio.setTextColor(Color.parseColor("#000000")); //set to black if 0
        }

        return listItemView;

    }

}
