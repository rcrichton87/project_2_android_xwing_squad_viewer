package com.codeclan.xwingsquadviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;

public class FilteredListActivity extends AppCompatActivity {

    public static final String SQUADS = "squads";
    ArrayList<Squad> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squads_list);

        //loading squads from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("filteredList", "Nothing found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        list = gson.fromJson(squads, squadArrayList.getType());

        //sort the list based on the win/loss ratio
        Collections.sort(list);

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
}
