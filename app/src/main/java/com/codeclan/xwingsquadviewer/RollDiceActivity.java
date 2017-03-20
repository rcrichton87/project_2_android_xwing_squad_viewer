package com.codeclan.xwingsquadviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class RollDiceActivity extends AppCompatActivity {

    Dice rolledDice;
    int numberOfDiceToRoll;

    String rollResultString;

    TextView selectDiceType;
    RadioGroup diceType;
    RadioButton attackDiceButton;
    RadioButton defenceDiceButton;

    TextView selectDiceNumber;
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

        selectDiceType = (TextView) findViewById(R.id.select_dice_type);
        selectDiceType.setText("Select the dice type to roll:");

        diceType = (RadioGroup) findViewById(R.id.dice_type);
        attackDiceButton = (RadioButton) findViewById(R.id.attack_dice);
        attackDiceButton.setChecked(true);
        defenceDiceButton = (RadioButton) findViewById(R.id.defence_dice);
        defenceDiceButton.setChecked(false);

        selectDiceNumber = (TextView) findViewById(R.id.select_dice_number);
        selectDiceNumber.setText("Select the number of dice to roll:");

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

        Bundle extras = this.getIntent().getExtras();
        String rollResultString = extras.getString("rollResults");
        rollResults = (TextView) findViewById(R.id.roll_results);
        rollResults.setText(rollResultString);

        rollButton = (Button) findViewById(R.id.roll_button);

    }

    public void rollButtonClicked(View button){

        int diceTypeButton = diceType.getCheckedRadioButtonId();
        if (diceTypeButton == attackDiceButton.getId() ){
            rolledDice = new Dice(DiceType.ATTACK);
        }
        if (diceTypeButton == defenceDiceButton.getId() ){
            rolledDice = new Dice(DiceType.DEFENCE);
        }

        int diceNumberButton = numberOfDice.getCheckedRadioButtonId();
        if (diceNumberButton == oneDice.getId() ){
            numberOfDiceToRoll = 1;
        }
        if (diceNumberButton == twoDice.getId() ){
            numberOfDiceToRoll = 2;
        }
        if (diceNumberButton == threeDice.getId() ){
            numberOfDiceToRoll = 3;
        }
        if (diceNumberButton == fourDice.getId() ){
            numberOfDiceToRoll = 4;
        }
        if (diceNumberButton == fiveDice.getId() ){
            numberOfDiceToRoll = 5;
        }
        if (diceNumberButton == sixDice.getId() ){
            numberOfDiceToRoll = 6;
        }

        int numberOfDiceRolled = 0;
        rollResultString = "";

        while (numberOfDiceRolled < numberOfDiceToRoll){
            rollResultString += rolledDice.roll();
            numberOfDiceRolled ++;
            if (numberOfDiceRolled < numberOfDiceToRoll){
                rollResultString += ", ";
            }
        }

        Intent intent = new Intent(this, RollDiceActivity.class);
        intent.putExtra("rollResults", rollResultString);
        startActivity(intent);


    }
}
