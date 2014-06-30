/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.metadata;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author kamilersz
 */
public class TableTest {
    
    public TableTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of toString method, of class Table.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Table instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Table.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Table instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumns method, of class Table.
     */
    @Test
    public void testGetColumns() {
        System.out.println("getColumns");
        Table instance = null;
        List expResult = null;
        List result = instance.getColumns();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
