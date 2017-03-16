package com.codeclan.xwingsquadviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListSquadsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.squads_list);

        SquadList squadList = new SquadList();
        ArrayList<Squad> list = squadList.getSquadList();

        ListSquadsAdapter squadsAdapter = new ListSquadsAdapter(this, list);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(squadsAdapter);
    }
}
