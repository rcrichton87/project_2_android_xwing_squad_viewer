package com.codeclan.xwingsquadviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.codeclan.xwingsquadviewer.DiceType.*;

/**
 * Created by user on 20/03/2017.
 */

public class Dice {

    ArrayList<String> results;

    public Dice(DiceType type){
        if (type == ATTACK){
            this.results = new ArrayList<String>(Arrays.asList("Crit", "Hit", "Hit", "Hit", "Focus", "Focus", "Blank", "Blank"));
        }
        if (type == DEFENCE){
            this.results = new ArrayList<String>(Arrays.asList("Evade", "Evade", "Evade", "Focus", "Focus", "Blank", "Blank", "Blank"));
        }
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
