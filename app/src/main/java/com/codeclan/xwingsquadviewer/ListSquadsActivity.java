package com.codeclan.xwingsquadviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class ListSquadsActivity extends AppCompatActivity {

    public static final String SQUADS = "squads";
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
        Intent intent = new Intent(this, ShowSquad.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.squads_list, menu);
        return true;
    }


    //the dropdown menu
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

            //clear the list
            list.clear();

            //save the updated list to the SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPref.edit();
            Log.d("squadlist json", gson.toJson(list));
            editor.putString("squadList", gson.toJson(list));
            editor.apply();
            Toast.makeText(ListSquadsActivity.this, "Saved Squads Deleted", Toast.LENGTH_LONG).show();

            Intent intent = getIntent();
            startActivity(intent);
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
            SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("filteredList", gson.toJson(filteredSquads));
            Log.d("FilteredList", gson.toJson(list));
            editor.apply();

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
            SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("filteredList", gson.toJson(filteredSquads));
            Log.d("FilteredList", gson.toJson(list));
            editor.apply();

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
            SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("filteredList", gson.toJson(filteredSquads));
            Log.d("FilteredList", gson.toJson(list));
            editor.apply();

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

}
