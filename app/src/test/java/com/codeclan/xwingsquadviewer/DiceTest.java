package com.codeclan.xwingsquadviewer;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiceTest {

    Dice attackDice;
    Dice defenceDice;

    @Before
    public void before(){
        attackDice = new Dice(DiceType.ATTACK);
        defenceDice = new Dice(DiceType.DEFENCE);
    }

    @Test
    public void testAttackDiceHasEightResults(){
        assertEquals(8, attackDice.getLength());
    }

    @Test
    public void testDefenceDiceHasEightResults(){
        assertEquals(8, defenceDice.getLength());
    }

    @Test
    public void testAttackDiceGetAtIndex(){
        assertEquals("Crit", attackDice.getResultAtIndex(0));
    }

}
