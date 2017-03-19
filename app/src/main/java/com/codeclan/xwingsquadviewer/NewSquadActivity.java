package com.codeclan.xwingsquadviewer;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class NewSquadActivity extends AppCompatActivity {

    public static final String SQUADS = "squads";

    EditText squadName;
    EditText squadDetails;
    RadioGroup squadFaction;
    Button saveSquad;

    String name;
    String details;
    Faction faction;

    Squad squad;
    ArrayList<Squad> squadList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_squad);

        squadName = (EditText) findViewById(R.id.new_squad_name);
        squadDetails = (EditText) findViewById(R.id.new_squad_details);
        squadFaction = (RadioGroup) findViewById(R.id.new_squad_faction);
        saveSquad = (Button) findViewById(R.id.save_button);


    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.faction_rebel:
                if (checked) {
                    faction = Faction.REBEL;
                    break;
                }
            case R.id.faction_imperial:
                if (checked) {
                    faction = Faction.IMPERIAL;
                    break;
                }
            case R.id.faction_scum:
                if (checked){
                    faction = Faction.SCUM;
                    break;
                }
        }
    }

    public void onSaveButtonClicked(View button){


        //create a new squad
        name = squadName.getText().toString();
        details = squadDetails.getText().toString();

        if (faction != null){
            squad = new Squad(name, details, faction);
        } else {
            Toast.makeText(NewSquadActivity.this, "Please select a faction", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("Squad", squad.toString());
        Log.d("Squad name", squad.getName());

        //load the saved squads from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        ArrayList<Squad> squadList = gson.fromJson(squads, squadArrayList.getType());

        //add the newly created squad to the saved squads
        squadList.add(squad);
        Log.d("Squad List", squadList.toString());


        //save the new squad to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        Log.d("squadlist json", gson.toJson(squadList));
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();
        Toast.makeText(NewSquadActivity.this, "Squad Saved", Toast.LENGTH_LONG).show();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        startActivity(intent);

    }



}
