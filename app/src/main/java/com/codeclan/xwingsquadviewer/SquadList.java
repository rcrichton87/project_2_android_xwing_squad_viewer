package com.codeclan.xwingsquadviewer;

import java.util.ArrayList;

import static com.codeclan.xwingsquadviewer.Faction.*;

public class SquadList {

    ArrayList<Squad> squadList;

    public SquadList() {
        squadList = new ArrayList<>();
        squadList.add( new Squad("Rogue Squadron", "Wedge Antilles\nLuke Skywalker\nWes Jansen", REBEL));
        squadList.add( new Squad("BBBBZ", "Dagger Squadron Pilot\nDagger Squadron Pilot\nDagger Squadron Pilot\nDagger Squadron Pilot\nBandit Squadron Pilot", REBEL));
        squadList.add( new Squad("The Force Awakens", "Rey\nPoe Dameron", REBEL));
        squadList.add( new Squad("Green Squadron", "Green Squadron Pilot\nGreen Squadron Pilot\nGreen Squadron Pilot\nGreen Squadron Pilot\nGreen Squadron Pilot", REBEL));
        squadList.add( new Squad("Brobots", "IG-88 B\nIG-88 C", SCUM));
        squadList.add( new Squad("Dengaroo", "Dengar\nManaroo", SCUM));
        squadList.add( new Squad("Mindlink", "Fenn Rau\nAsaaj Ventriss\nManaroo", SCUM));
        squadList.add( new Squad("Thug Life", "Syndicate Thug\nSyndicate Thug\nSyndicate Thug\nSyndicate Thug", SCUM));
        squadList.add( new Squad("Imperial Aces", "Soontir Fel\nCarnor Jax\nTetran Cowell", IMPERIAL));
        squadList.add( new Squad("Triple Defenders", "Maarek Steele\nColonel Vessery\nGalive Squadron Pilot", IMPERIAL));
        squadList.add( new Squad("Palpshuttle", "The Inquisitor\nOmega Leader\nWampa\nOmicron Group Pilot", IMPERIAL));
        squadList.add( new Squad("Alpha Squadron", "Alpha Squadron Pilot\nAlpha Squadron Pilot\nAlpha Squadron Pilot\nAlpha Squadron Pilot\nAlpha Squadron Pilot", IMPERIAL));
    }

    public ArrayList<Squad> getSquadList(){
        return squadList;
    }
}
