package com.codeclan.xwingsquadviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ShowSquad extends AppCompatActivity {

    Squad squad;
    public static final String SQUADS = "squads";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_squad);

        squad = (Squad) getIntent().getSerializableExtra("squad");

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

    public void addWinClicked(View view){
        //load the saved squads from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        ArrayList<Squad> squadList = gson.fromJson(squads, squadArrayList.getType());

        //remove the squad from the list, modify it, then add the modified squad
        Boolean squadFound = squadList.contains(squad);
        Log.d("Squad found", squadFound.toString());
        
        squadList.remove(squad);
        squad.addWin();
        squadList.add(squad);

        //save the updated list to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        //intent.putExtra("squad", squad);
        //finish();
        Toast.makeText(ShowSquad.this, "Win added for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);

    }

    public void addLossClicked(View view){
        Intent intent = getIntent();
        squad.addLoss();
        intent.putExtra("squad", squad);
        finish();
        startActivity(intent);
    }
}
