package com.codeclan.xwingsquadviewer;

import org.junit.Before;
import org.junit.Test;

import static com.codeclan.xwingsquadviewer.Faction.*;
import static org.junit.Assert.*;

public class SquadTest {

    Squad squad1;

    @Before
    public void before(){
        squad1 = new Squad("Rogue Squadron", "Wedge Antilles, Luke Skywalker", REBEL, 0);
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
        Integer expected = 0;
        assertEquals(expected, squad1.getWins());
    }

    @Test
    public void testLossesStartAtZero(){
        Integer expected = 0;
        assertEquals(expected, squad1.getLosses());
    }

    @Test
    public void testCanGetFaction(){
        assertEquals(REBEL, squad1.getFaction());
    }

    @Test
    public void testAddWin(){
        squad1.addWin();
        Integer expected = 1;
        assertEquals(expected, squad1.getWins());
    }

    @Test
    public void testAddLoss(){
        squad1.addLoss();
        Integer expected = 1;
        assertEquals(expected, squad1.getLosses());
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


    @Test
    public void testSquadHasId(){
        assertNotNull(squad1.getId());
    }

    @Test
    public void testRemoveWin(){
        squad1.addWin();
        squad1.removeWin();
        Integer expected = 0;
        assertEquals(expected, squad1.getWins());
    }

    @Test
    public void testCantPutWinsBelowZero(){
        squad1.removeWin();
        Integer expected = 0;
        assertEquals(expected, squad1.getWins());
    }

    @Test
    public void testRemoveLoss(){
        squad1.addLoss();
        squad1.removeLoss();
        Integer expected = 0;
        assertEquals(expected, squad1.getLosses());
    }

    @Test
    public void testCantPutLossesBelowZero(){
        squad1.removeLoss();
        Integer expected = 0;
        assertEquals(expected, squad1.getWins());
    }

    @Test
    public void testCanChangeSquadName(){
        squad1.setName("Red Squadron");
        assertEquals("Red Squadron", squad1.getName());
    }

    @Test
    public void testCanChangeSquadDetails(){
        squad1.setDetails("Red Squadron Pilot");
        assertEquals("Red Squadron Pilot", squad1.getDetails());
    }

    @Test
    public  void testCanGetId(){
        Integer expected = 1;
        assertEquals(expected, squad1.getId());
    }

}