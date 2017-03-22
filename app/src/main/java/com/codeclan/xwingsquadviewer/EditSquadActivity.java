package com.codeclan.xwingsquadviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class EditSquadActivity extends AppCompatActivity {

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

        //load the squadlist
        squadList = SharedPrefsManager.loadSquadList(this);

        //modify the squad in the squadList
        for (Squad listSquad : squadList){
            if (listSquad.getId().equals(squad.getId()) ){
                listSquad.setName(newName);
                listSquad.setDetails(newDetails);
            }
        }

        //save the updated squadlist
        SharedPrefsManager.saveSquadList(squadList, this);

        //go back to the list
        Intent intent = new Intent(this, ListSquadsActivity.class);
        Toast.makeText(EditSquadActivity.this, squad.getName() + " Edited", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }
}
