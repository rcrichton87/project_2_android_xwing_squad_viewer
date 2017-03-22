package com.codeclan.xwingsquadviewer;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class ListSquadsActivity extends AppCompatActivity {

    ArrayList<Squad> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squads_list);

        //loading squads from SharedPreferences
        list = SharedPrefsManager.loadSquadList(this);

        //sort the list based on the win/loss ratio
        Collections.sort(list);

        ListSquadsAdapter squadsAdapter = new ListSquadsAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(squadsAdapter);
    }

    public void squadClicked(View squad_item){
        Squad squad = (Squad) squad_item.getTag();

        // save the squad as json
        SharedPrefsManager.saveIndividualSquad(squad, this);

        //go to the showsquad activity
        Intent intent = new Intent(this, ShowSquadActivity.class);
        startActivity(intent);
    }

    //menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.squads_list, menu);
        return true;
    }


    //selecting menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //new squad menu item
        if(item.getItemId() == R.id.action_new_squad){
            Intent intent = new Intent(this, NewSquadActivity.class);
            startActivity(intent);
            return true;
        }

        //clear the list menu item
        if(item.getItemId() == R.id.clear_list){

            FragmentManager fm = getFragmentManager();
            DeleteWarning deleteWarning = new DeleteWarning();
            deleteWarning.show(fm, "confirm delete");
            return true;
        }

        //filter by rebel
        if(item.getItemId() == R.id.action_rebel_only){

            //make a list of filtered squads
            ArrayList<Squad> filteredSquads = new ArrayList<>();
            for (Squad squad : list ) {
                if (squad.getFaction() == Faction.REBEL){
                    filteredSquads.add(squad);
                }
            }

            //save the filtered list to the SharedPreferences
            SharedPrefsManager.saveFilteredList(filteredSquads, this);

            Intent intent = new Intent(this, FilteredListActivity.class);
            startActivity(intent);
            return true;
        }

        //filter by imperial
        if(item.getItemId() == R.id.action_imperial_only){

            //make a list of filtered squads
            ArrayList<Squad> filteredSquads = new ArrayList<>();
            for (Squad squad : list ) {
                if (squad.getFaction() == Faction.IMPERIAL){
                    filteredSquads.add(squad);
                }
            }

            //save the filtered list to the SharedPreferences
            SharedPrefsManager.saveFilteredList(filteredSquads, this);

            Intent intent = new Intent(this, FilteredListActivity.class);
            startActivity(intent);
            return true;
        }

        //filter by scum
        if(item.getItemId() == R.id.action_scum_only){

            //make a list of filtered squads
            ArrayList<Squad> filteredSquads = new ArrayList<>();
            for (Squad squad : list ) {
                if (squad.getFaction() == Faction.SCUM){
                    filteredSquads.add(squad);
                }
            }

            //save the filtered list to the SharedPreferences
            SharedPrefsManager.saveFilteredList(filteredSquads, this);

            Intent intent = new Intent(this, FilteredListActivity.class);
            startActivity(intent);
            return true;
        }

        //go to the dice roller
        if(item.getItemId() == R.id.action_roll_dice){
            Intent intent = new Intent(this, RollDiceActivity.class);
            intent.putExtra("rollResults", "No results");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void deleteConfirm(View view) {
        //clear the list
        list.clear();

        //save the updated list to the SharedPreferences
        SharedPrefsManager.saveSquadList(list, this);
        Toast.makeText(ListSquadsActivity.this, "Saved Squads Deleted", Toast.LENGTH_LONG).show();


        Intent intent = new Intent(this, ListSquadsActivity.class);
        startActivity(intent);
    }

}
