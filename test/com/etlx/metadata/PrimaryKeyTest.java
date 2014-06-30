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
public class PrimaryKeyTest {
    
    public PrimaryKeyTest() {
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
     * Test of setTable method, of class PrimaryKey.
     */
    @Test
    public void testSetTable() {
        System.out.println("setTable");
        String table = "";
        PrimaryKey instance = null;
        instance.setTable(table);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCol method, of class PrimaryKey.
     */
    @Test
    public void testSetCol() {
        System.out.println("setCol");
        List<String> primaryColumns = null;
        PrimaryKey instance = null;
        instance.setCol(primaryColumns);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class PrimaryKey.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        PrimaryKey instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class PrimaryKey.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        PrimaryKey instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableName method, of class PrimaryKey.
     */
    @Test
    public void testGetTableName() {
        System.out.println("getTableName");
        PrimaryKey instance = null;
        String expResult = "";
        String result = instance.getTableName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrimaryColumns method, of class PrimaryKey.
     */
    @Test
    public void testGetPrimaryColumns() {
        System.out.println("getPrimaryColumns");
        PrimaryKey instance = null;
        List expResult = null;
        List result = instance.getPrimaryColumns();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
