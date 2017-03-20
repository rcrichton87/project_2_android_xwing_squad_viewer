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
    ArrayList<Squad> squadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_squad);

        //load the squad from json
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squadString = sharedPref.getString("individualSquad", "Nothing Found");
        TypeToken<Squad> squadTypeToken = new TypeToken<Squad>(){};
        squad = gson.fromJson(squadString, squadTypeToken.getType());

        Log.d("json", squadString);
        Log.d("Squad from json", squad.toString());
        Log.d("Squad id", squad.getId().toString());


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
        squadList = gson.fromJson(squads, squadArrayList.getType());

        //modify the squad in the squadList
        Boolean squadFound = squadList.contains(squad); //this returns false
        Log.d("Squad found .contains", squadFound.toString()); //the squad object from getSerializableExtra a different object to the one in setTag in ListSquadsAdapter and getTag in ListSquadsActivity

        Log.d("Squad list", squadList.toString());

        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.addWin();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquad.this, "Win added for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);

    }

    public void removeWinClicked(View view){
        //load the saved squads from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        squadList = gson.fromJson(squads, squadArrayList.getType());

        //modify the squad in the squadList
        Boolean squadFound = squadList.contains(squad); //this returns false
        Log.d("Squad found .contains", squadFound.toString()); //the squad object from getSerializableExtra a different object to the one in setTag in ListSquadsAdapter and getTag in ListSquadsActivity

        Log.d("Squad list", squadList.toString());

        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.removeWin();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquad.this, "Win removed for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);

    }

    public void addLossClicked(View view){
        //load the saved squads from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        squadList = gson.fromJson(squads, squadArrayList.getType());

        //modify a squad in the squadList
        Boolean squadFound = squadList.contains(squad); //this returns false
        Log.d("Squad found .contains", squadFound.toString()); //it looks like the json gives a different object

        Log.d("Squad list", squadList.toString());

        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.addLoss();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquad.this, "Loss added for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void removeLossClicked(View view){
        //load the saved squads from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        squadList = gson.fromJson(squads, squadArrayList.getType());

        //modify a squad in the squadList
        Boolean squadFound = squadList.contains(squad); //this returns false
        Log.d("Squad found .contains", squadFound.toString()); //it looks like the json gives a different object

        Log.d("Squad list", squadList.toString());

        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.removeLoss();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquad.this, "Loss removed for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void deleteSquadClicked(View view){
        //load the saved squads from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        squadList = gson.fromJson(squads, squadArrayList.getType());

        //remove a squad from the squadList
        int index = 0;

        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                break;
            }
            index++;
        }

        squadList.remove(index);

        //save the updated list to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquad.this, squad.getName() + " deleted!", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
