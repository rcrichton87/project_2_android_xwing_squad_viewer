package com.codeclan.xwingsquadviewer;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ShowSquadActivity extends AppCompatActivity {

    Squad squad;
    ArrayList<Squad> squadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_squad);

        //load the squad from json
        squad = SharedPrefsManager.loadIndividualSquad(this);

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

        Button confirmDelete = (Button) findViewById(R.id.delete_yes);
        Button cancelDelete = (Button) findViewById(R.id.delete_no);

    }

    public void addWinClicked(View view){
        //load the saved squads from sharedpreferences
        squadList = SharedPrefsManager.loadSquadList(this);

        //modify the squad in the squadList
        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.addWin();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPrefsManager.saveSquadList(squadList, this);

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquadActivity.this, "Win Added for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);

    }

    public void removeWinClicked(View view){
        //load the saved squads from sharedpreferences
        squadList = SharedPrefsManager.loadSquadList(this);

        //modify the squad in the squadList
        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.removeWin();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPrefsManager.saveSquadList(squadList, this);

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquadActivity.this, "Win Removed for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);

    }

    public void addLossClicked(View view){
        //load the saved squads from sharedpreferences
        squadList = SharedPrefsManager.loadSquadList(this);

        //modify a squad in the squadList
        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.addLoss();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPrefsManager.saveSquadList(squadList, this);

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquadActivity.this, "Loss Added for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void removeLossClicked(View view){
        //load the saved squads from sharedpreferences
        squadList = SharedPrefsManager.loadSquadList(this);

        //modify a squad in the squadList
        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.removeLoss();
            }
        }

        //save the updated list to the SharedPreferences
        SharedPrefsManager.saveSquadList(squadList, this);

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquadActivity.this, "Loss Removed for " + squad.getName(), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void editSquadClicked(View view){
        // save the squad as json
        SharedPrefsManager.saveIndividualSquad(squad, this);

        Intent intent = new Intent(this, EditSquad.class);
        startActivity(intent);
    }

    public void deleteSquadClicked(View view) {
        FragmentManager fm = getFragmentManager();
        DeleteWarning deleteWarning = new DeleteWarning();
        deleteWarning.show(fm, "Sample Fragment");

    }

    public void deleteConfirm(View view){
        //load the saved squads from sharedpreferences
        squadList = SharedPrefsManager.loadSquadList(this);

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
        SharedPrefsManager.saveSquadList(squadList, this);

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(ShowSquadActivity.this, squad.getName() + " Deleted!", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

}
