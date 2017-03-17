package com.codeclan.xwingsquadviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowSquad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_squad);

        Squad squad = (Squad) getIntent().getSerializableExtra("squad");

        Log.d("Squad", squad.getName());

        ImageView factionSymbol = (ImageView) findViewById(R.id.faction_symbol);
        factionSymbol.setImageResource(squad.getFactionSymbol());

        TextView squadName = (TextView) findViewById(R.id.squad_name);
        squadName.setText(squad.getName());

        TextView squadWins = (TextView) findViewById(R.id.squad_wins);
        squadWins.setText("Wins: " + squad.getWins().toString());

        TextView squadLosses = (TextView) findViewById(R.id.squad_losses);
        squadLosses.setText("Losses: " + squad.getLosses().toString());

        TextView squadDetails = (TextView) findViewById(R.id.squad_details);
        squadDetails.setText(squad.getDetails());

    }
}
