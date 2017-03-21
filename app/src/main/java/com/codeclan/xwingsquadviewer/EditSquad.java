package com.codeclan.xwingsquadviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EditSquad extends AppCompatActivity {

    public static final String SQUADS = "squads";

    EditText squadName;
    EditText squadDetails;
    Button saveChanges;

    String newName;
    String newDetails;

    Squad squad;
    ArrayList<Squad> squadList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_squad);

        //load the squad from json
        //SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        //Gson gson = new Gson();
        //String squadString = sharedPref.getString("individualSquad", "Nothing Found");
        //Log.d("Squad string", squadString);
        //TypeToken<Squad> squadTypeToken = new TypeToken<Squad>(){};
        squad = SharedPrefsManager.loadIndividualSquad(this);

        squadName = (EditText) findViewById(R.id.edit_squad_name);
        squadName.setText(squad.getName(), TextView.BufferType.EDITABLE);
        squadDetails = (EditText) findViewById(R.id.edit_squad_details);
        squadDetails.setText(squad.getDetails(), TextView.BufferType.EDITABLE);

        saveChanges = (Button) findViewById(R.id.save_changes);

    }

    public void saveChangesClicked(View view){
        newName = squadName.getText().toString();
        newDetails = squadDetails.getText().toString();



        //load the saved squads from sharedpreferences
        SharedPreferences sharedPref = getSharedPreferences(SQUADS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String squads = sharedPref.getString("squadList", "Nothing Found");
        TypeToken<ArrayList<Squad>> squadArrayList = new TypeToken<ArrayList<Squad>>(){};
        squadList = gson.fromJson(squads, squadArrayList.getType());

        //modify the squad in the squadList

        Log.d("Squad list", squadList.toString());

        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.setName(newName);
                listSquad.setDetails(newDetails);
            }
        }

        //save the updated list to the SharedPreferences
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("squadList", gson.toJson(squadList));
        editor.apply();

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(EditSquad.this, squad.getName() + " edited", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
