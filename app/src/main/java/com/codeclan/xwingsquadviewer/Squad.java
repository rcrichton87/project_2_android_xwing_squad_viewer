package com.codeclan.xwingsquadviewer;

import java.io.Serializable;

public class Squad implements Serializable{

    String name;
    String details;
    Integer wins;
    Integer losses;
    Faction faction;

    public Squad(String name, String details, Faction faction){
        this.name = name;
        this.details = details;
        this.wins = 0;
        this.losses = 0;
        this.faction = faction;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return losses;
    }

    public Faction getFaction() {
        return faction;
    }

    public void addWin(){
        wins++;
    }

    public void addLoss(){
        losses++;
    }

    public Integer getWinLossRatio(){
        return wins - losses;
    }

    public Integer getFactionSymbol(){
        if (faction == Faction.REBEL){
            return R.drawable.symbol_rebel;
        }
        else if (faction == Faction.IMPERIAL){
            return R.drawable.symbol_imperial;
        }
        else {
            return R.drawable.symbol_scum;
        }
    }



}
