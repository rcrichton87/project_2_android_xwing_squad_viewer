package com.codeclan.xwingsquadviewer;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SquadListTest {

    SquadList squadList;

    @Before
    public void before(){
        squadList = new SquadList();
    }

    @Test
    public void newGetSquadList(){
        assertEquals(12, squadList.getSquadList().size());
    }

}
