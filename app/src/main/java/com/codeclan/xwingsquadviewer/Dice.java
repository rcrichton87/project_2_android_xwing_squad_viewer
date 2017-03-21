package com.codeclan.xwingsquadviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.codeclan.xwingsquadviewer.DiceType.*;

public class Dice {

    ArrayList<String> results;
    DiceType diceType;

    public Dice(DiceType type){
        if (type == ATTACK){
            this.results = new ArrayList<String>(Arrays.asList("Crit", "Hit", "Hit", "Hit", "Focus", "Focus", "Blank", "Blank"));
        }
        if (type == DEFENCE){
            this.results = new ArrayList<String>(Arrays.asList("Evade", "Evade", "Evade", "Focus", "Focus", "Blank", "Blank", "Blank"));
        }
        this.diceType = type;
    }

    public DiceType getType(){
        return diceType;
    }

    public int getLength(){
        return results.size();
    }

    public String getResultAtIndex(int index){
        return results.get(index);
    }


    public String roll(){
        Random rand = new Random();
        int listSize = getLength();
        int index = rand.nextInt(listSize);
        String rolledResult = getResultAtIndex(index);
        return rolledResult;
    }

}
