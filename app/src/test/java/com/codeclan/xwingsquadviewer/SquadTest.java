package com.codeclan.xwingsquadviewer;

import org.junit.Before;
import org.junit.Test;

import static com.codeclan.xwingsquadviewer.Faction.*;
import static org.junit.Assert.*;

public class SquadTest {

    Squad squad1;

    @Before
    public void before(){
        squad1 = new Squad("Rogue Squadron", "Wedge Antilles, Luke Skywalker", REBEL);
    }

    @Test
    public void testCanGetName(){
        assertEquals("Rogue Squadron", squad1.getName());
    }

    @Test
    public void testCanGetDetails(){
        assertEquals("Wedge Antilles, Luke Skywalker", squad1.getDetails());
    }

    @Test
    public void testWinsStartAtZero(){
        assertEquals(0, squad1.getWins());
    }

    @Test
    public void testLossesStartAtZero(){
        assertEquals(0, squad1.getLosses());
    }

    @Test
    public void testCanGetFaction(){
        assertEquals(REBEL, squad1.getFaction());
    }

    @Test
    public void testAddWin(){
        squad1.addWin();
        assertEquals(1, squad1.getWins());
    }

    @Test
    public void testAddLoss(){
        squad1.addLoss();
        assertEquals(1, squad1.getLosses());
    }

    @Test
    public void testGetWinLossRatio(){
        squad1.addWin();
        squad1.addWin();
        squad1.addLoss();
        Integer expected = 1;
        assertEquals(expected, squad1.getWinLossRatio());
    }

    @Test
    public void testGetWinLossRatioNegative(){
        squad1.addWin();
        squad1.addLoss();
        squad1.addLoss();
        Integer expected = -1;
        assertEquals(expected, squad1.getWinLossRatio());
    }

    @Test
    public void testCanGetFactionSymbol(){
        Integer expected = R.drawable.symbol_rebel;
        assertEquals(expected, squad1.getFactionSymbol());
    }

}