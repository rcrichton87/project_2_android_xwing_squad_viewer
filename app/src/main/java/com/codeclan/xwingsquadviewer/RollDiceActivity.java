package com.codeclan.xwingsquadviewer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import static android.support.constraint.ConstraintSet.WRAP_CONTENT;

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

    LinearLayout rollResultsImages;

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

        MediaPlayer diceSound = MediaPlayer.create(getApplicationContext(), R.raw.dice);
        diceSound.start();

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

        //empty the linearlayout of results before adding the new roll's results
        rollResultsImages = (LinearLayout)findViewById(R.id.roll_results_images);
        rollResultsImages.removeAllViews();

        while (numberOfDiceRolled < numberOfDiceToRoll){
            String diceRoll = rolledDice.roll();
            rollResultString += diceRoll;

            numberOfDiceRolled ++;
            if (numberOfDiceRolled < numberOfDiceToRoll){
                rollResultString += ", ";
            }

            //make a new imageview for each roll
            ImageView diceImage = new ImageView(this);
            diceImage.setLayoutParams(new android.view.ViewGroup.LayoutParams(WRAP_CONTENT,WRAP_CONTENT));

            //put an image into the imageview based on the dice type and result rolled
            if (rolledDice.getType() == DiceType.ATTACK){
                if (diceRoll.equals("Crit")){
                    diceImage.setImageResource(R.drawable.attack_crit);
                }
                if (diceRoll.equals("Hit")){
                    diceImage.setImageResource(R.drawable.attack_hit);
                }
                if (diceRoll.equals("Focus")){
                    diceImage.setImageResource(R.drawable.attack_focus);
                }
                if (diceRoll.equals("Blank")){
                    diceImage.setImageResource(R.drawable.attack_blank);
                }
            }

            if (rolledDice.getType() == DiceType.DEFENCE){
                if (diceRoll.equals("Evade")){
                    diceImage.setImageResource(R.drawable.defence_evade);
                }
                if (diceRoll.equals("Focus")){
                    diceImage.setImageResource(R.drawable.defence_focus);
                }
                if (diceRoll.equals("Blank")){
                    diceImage.setImageResource(R.drawable.defence_blank);
                }
            }

            //add the imageview to the linearlayout of dice results
            rollResultsImages.addView(diceImage);

        }

        rollResults.setText(rollResultString);

    }

}
