/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.metadata;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author kamilersz
 */
public class ColumnTest {
    
    public ColumnTest() {
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
     * Test of toString method, of class Column.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Column instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Column.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Column instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataType method, of class Column.
     */
    @Test
    public void testGetDataType() {
        System.out.println("getDataType");
        Column instance = null;
        String expResult = "";
        String result = instance.getDataType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConstraint method, of class Column.
     */
    @Test
    public void testGetConstraint() {
        System.out.println("getConstraint");
        Column instance = null;
        String expResult = "";
        String result = instance.getConstraint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLength method, of class Column.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        Column instance = null;
        Integer expResult = null;
        Integer result = instance.getLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecision method, of class Column.
     */
    @Test
    public void testGetPrecision() {
        System.out.println("getPrecision");
        Column instance = null;
        Integer expResult = null;
        Integer result = instance.getPrecision();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
