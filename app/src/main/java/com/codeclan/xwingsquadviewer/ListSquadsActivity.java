package com.codeclan.xwingsquadviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ListSquadsActivity extends AppCompatActivity {

    public static final String SQUADS = "Squads";
    ArrayList<Squad> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squads_list);

        //loading squads from SharedPreferences
        //SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        //String squads = sharedPref.getString("squads", "No squads found.");
        //Gson gson = new Gson();
        //TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        //ArrayList<Squad> list = gson.fromJson(squads, squadArrayList.getType());

        SquadList squadList = new SquadList();
        list = squadList.getSquadList();

        ListSquadsAdapter squadsAdapter = new ListSquadsAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(squadsAdapter);
    }

    public void squadClicked(View squad_item){
        Squad squad = (Squad) squad_item.getTag();
        Intent intent = new Intent(this, ShowSquad.class);
        intent.putExtra("squad", squad);
        startActivity(intent);
    }
}
