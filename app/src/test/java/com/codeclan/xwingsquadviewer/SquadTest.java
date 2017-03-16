package com.codeclan.xwingsquadviewer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {

    Squad squad1;

    @Before
    public void before(){
        squad1 = new Squad("Rogue Squadron", "Wedge Antilles, Luke Skywalker", Faction.REBEL);
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

}