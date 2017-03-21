package com.codeclan.xwingsquadviewer;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Squad implements Comparable<Squad>{

    String name;
    String details;
    Integer wins;
    Integer losses;
    Faction faction;
    static AtomicInteger nextId = new AtomicInteger();
    Integer id;

    public Squad(String name, String details, Faction faction){
        this.name = name;
        this.details = details;
        this.wins = 0;
        this.losses = 0;
        this.faction = faction;
        this.id = nextId.incrementAndGet();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName){
        this.name = newName;
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

    public void removeWin(){
        if (wins == 0){
            return;
        }
        wins -= 1;
    }

    public void addLoss(){
        losses++;
    }

    public void removeLoss(){
        if (losses == 0){
            return;
        }
        losses -= 1;
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

    public Integer getId(){
        return id;
    }


    //allows squads to be compared by their win/loss ratios using collections.sort(ArrayList)
    @Override
    public int compareTo(Squad compareSquad){
        int compareWinLoss=((Squad)compareSquad).getWinLossRatio();
        if (compareWinLoss > this.getWinLossRatio()){
            return 1;
        } else if (compareWinLoss < this.getWinLossRatio()){
            return -1;
        } else {
            return 0;
        }
    }

}
