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
public class ForeignKeyTest {
    
    public ForeignKeyTest() {
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
     * Test of setTableFrom method, of class ForeignKey.
     */
    @Test
    public void testSetTableFrom() {
        System.out.println("setTableFrom");
        String tableFrom = "";
        ForeignKey instance = null;
        instance.setTableFrom(tableFrom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTableTo method, of class ForeignKey.
     */
    @Test
    public void testSetTableTo() {
        System.out.println("setTableTo");
        String tableTo = "";
        ForeignKey instance = null;
        instance.setTableTo(tableTo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColFrom method, of class ForeignKey.
     */
    @Test
    public void testSetColFrom() {
        System.out.println("setColFrom");
        List<String> colFrom = null;
        ForeignKey instance = null;
        instance.setColFrom(colFrom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColTo method, of class ForeignKey.
     */
    @Test
    public void testSetColTo() {
        System.out.println("setColTo");
        List<String> colTo = null;
        ForeignKey instance = null;
        instance.setColTo(colTo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class ForeignKey.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        ForeignKey instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableFrom method, of class ForeignKey.
     */
    @Test
    public void testGetTableFrom() {
        System.out.println("getTableFrom");
        ForeignKey instance = null;
        String expResult = "";
        String result = instance.getTableFrom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableTo method, of class ForeignKey.
     */
    @Test
    public void testGetTableTo() {
        System.out.println("getTableTo");
        ForeignKey instance = null;
        String expResult = "";
        String result = instance.getTableTo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColFrom method, of class ForeignKey.
     */
    @Test
    public void testGetColFrom() {
        System.out.println("getColFrom");
        ForeignKey instance = null;
        List expResult = null;
        List result = instance.getColFrom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColTo method, of class ForeignKey.
     */
    @Test
    public void testGetColTo() {
        System.out.println("getColTo");
        ForeignKey instance = null;
        List expResult = null;
        List result = instance.getColTo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ForeignKey.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ForeignKey instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
