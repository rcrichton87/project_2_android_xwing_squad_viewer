package com.codeclan.xwingsquadviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class NewSquadActivity extends AppCompatActivity {

    EditText squadName;
    EditText squadDetails;
    RadioGroup squadFaction;
    RadioButton factionRebel;
    RadioButton factionImperial;
    RadioButton factionScum;
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
        factionRebel = (RadioButton) findViewById(R.id.faction_rebel);
        factionImperial = (RadioButton) findViewById(R.id.faction_imperial);
        factionScum = (RadioButton) findViewById(R.id.faction_scum);
        factionRebel.setChecked(true);
        factionImperial.setChecked(false);
        factionScum.setChecked(false);
        squadFaction.check(R.id.faction_rebel);

        saveSquad = (Button) findViewById(R.id.save_button);


    }

    public void onSaveButtonClicked(View button){


        //create a new squad
        name = squadName.getText().toString();
        details = squadDetails.getText().toString();

        //get the faction from the radiobutttons
        int factionButton = squadFaction.getCheckedRadioButtonId();
        if (factionButton == factionRebel.getId() ) {
            faction = Faction.REBEL;
        }
        if (factionButton == factionImperial.getId() ) {
            faction = Faction.IMPERIAL;
        }
        if (factionButton == factionScum.getId()){
            faction = Faction.SCUM;
        }

        squad = new Squad(name, details, faction);

        //load the saved squads from sharedpreferences
        squadList = SharedPrefsManager.loadSquadList(this);

        //add the newly created squad to the saved squads
        squadList.add(squad);

        //save the updated list to the SharedPreferences
        SharedPrefsManager.saveSquadList(squadList, this);
        Toast.makeText(NewSquadActivity.this, "Squad Saved", Toast.LENGTH_LONG).show();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        startActivity(intent);

    }

}
