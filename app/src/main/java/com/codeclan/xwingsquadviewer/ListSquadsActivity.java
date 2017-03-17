package com.codeclan.xwingsquadviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

    public void squadClicked(View squad_item){
        Squad squad = (Squad) squad_item.getTag();
        Intent intent = new Intent(this, ShowSquad.class);
        intent.putExtra("squad", squad);
        startActivity(intent);
    }
}
