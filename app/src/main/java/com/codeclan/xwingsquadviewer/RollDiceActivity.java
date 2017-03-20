package com.codeclan.xwingsquadviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RollDiceActivity extends AppCompatActivity {

    String rollResultString = "No results";

    RadioGroup diceType;
    RadioButton attackDiceButton;
    RadioButton defenceDiceButton;

    RadioGroup numberOfDice;
    RadioButton oneDice;
    RadioButton twoDice;
    RadioButton threeDice;
    RadioButton fourDice;
    RadioButton fiveDice;
    RadioButton sixDice;

    TextView rollResults;

    Button rollButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_dice);

        diceType = (RadioGroup) findViewById(R.id.dice_type);
        attackDiceButton = (RadioButton) findViewById(R.id.attack_dice);
        attackDiceButton.setChecked(true);
        defenceDiceButton = (RadioButton) findViewById(R.id.defence_dice);
        defenceDiceButton.setChecked(false);

        numberOfDice = (RadioGroup) findViewById(R.id.number_of_dice);
        oneDice = (RadioButton) findViewById(R.id.one_dice);
        oneDice.setChecked(true);
        twoDice = (RadioButton) findViewById(R.id.two_dice);
        twoDice.setChecked(false);
        threeDice = (RadioButton) findViewById(R.id.three_dice);
        threeDice.setChecked(false);
        fourDice = (RadioButton) findViewById(R.id.four_dice);
        fourDice.setChecked(false);
        fiveDice = (RadioButton) findViewById(R.id.five_dice);
        fiveDice.setChecked(false);
        sixDice = (RadioButton) findViewById(R.id.six_dice);
        sixDice.setChecked(false);

        rollResults = (TextView) findViewById(R.id.roll_results);
        rollResults.setText(rollResultString);

        rollButton = (Button) findViewById(R.id.roll_button);

    }
}
