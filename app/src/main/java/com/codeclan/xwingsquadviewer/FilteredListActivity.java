package com.codeclan.xwingsquadviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;

public class FilteredListActivity extends AppCompatActivity {

    ArrayList<Squad> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squads_list);

        //loading squads from SharedPreferences
        list = SharedPrefsManager.loadFilteredList(this);

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
}
