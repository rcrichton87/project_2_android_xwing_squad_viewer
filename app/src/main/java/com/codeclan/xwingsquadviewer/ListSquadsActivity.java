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

public class ListSquadsActivity extends AppCompatActivity {

    public static final String SQUADS = "squads";
    ArrayList<Squad> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squads_list);

        //creating squad list from presets
//        SquadList squadList = new SquadList();
//        list = squadList.getSquadList();

        //setting up a default string if sharedPref is empty
        SquadList defaultSquadList = new SquadList();
        ArrayList<Squad> defaultSquadArrayList = defaultSquadList.getSquadList();

        //loading squads from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String defaultSquads = gson.toJson(defaultSquadArrayList);
        String squads = sharedPref.getString("squadList", defaultSquads);
        Log.d("squads json", squads);
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        list = gson.fromJson(squads, squadArrayList.getType());


        ListSquadsAdapter squadsAdapter = new ListSquadsAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(squadsAdapter);
    }

    public void squadClicked(View squad_item){
        Squad squad = (Squad) squad_item.getTag();

        // save the squad as json
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("individualSquad", gson.toJson(squad));
        editor.apply();

        Log.d("squad in json", squad.toString());
        Log.d("squad as json", gson.toJson(squad));

        Intent intent = new Intent(this, ShowSquad.class);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.squads_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.action_new_squad){
            Intent intent = new Intent(this, NewSquadActivity.class);
            startActivity(intent);
            return true;
        }
        if(item.getItemId() == R.id.clear_list){
            //loading squads from SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String squads = sharedPref.getString("squadList", "None found.");
            Log.d("squads json", squads);
            TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
            list = gson.fromJson(squads, squadArrayList.getType());

            //clear the list
            list.clear();

            //save the updated list to the SharedPreferences
            SharedPreferences.Editor editor = sharedPref.edit();
            Log.d("squadlist json", gson.toJson(list));
            editor.putString("squadList", gson.toJson(list));
            editor.apply();
            Toast.makeText(ListSquadsActivity.this, "Saved Squads Deleted", Toast.LENGTH_LONG).show();

            Intent intent = getIntent();
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
