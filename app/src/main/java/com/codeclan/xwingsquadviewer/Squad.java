package com.codeclan.xwingsquadviewer;

public class Squad {

    String name;
    String details;
    int wins;
    int losses;
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

    public int getWins() {
        return wins;
    }

    public int getLosses() {
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

    public int getWinLossRatio(){
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
