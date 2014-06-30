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
public class DBTest {
    
    public DBTest() {
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
     * Test of getAllColumns method, of class DB.
     */
    @Test
    public void testGetAllColumns() {
        System.out.println("getAllColumns");
        DB instance = null;
        List expResult = null;
        List result = instance.getAllColumns();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTables method, of class DB.
     */
    @Test
    public void testGetTables() {
        System.out.println("getTables");
        DB instance = null;
        List expResult = null;
        List result = instance.getTables();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTables method, of class DB.
     */
    @Test
    public void testSetTables() {
        System.out.println("setTables");
        List<Table> tables = null;
        DB instance = null;
        instance.setTables(tables);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getForeignKeys method, of class DB.
     */
    @Test
    public void testGetForeignKeys() {
        System.out.println("getForeignKeys");
        DB instance = null;
        List expResult = null;
        List result = instance.getForeignKeys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setForeignKeys method, of class DB.
     */
    @Test
    public void testSetForeignKeys() {
        System.out.println("setForeignKeys");
        List<ForeignKey> foreignKeys = null;
        DB instance = null;
        instance.setForeignKeys(foreignKeys);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrimaryKeys method, of class DB.
     */
    @Test
    public void testGetPrimaryKeys() {
        System.out.println("getPrimaryKeys");
        DB instance = null;
        List expResult = null;
        List result = instance.getPrimaryKeys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrimaryKeys method, of class DB.
     */
    @Test
    public void testSetPrimaryKeys() {
        System.out.println("setPrimaryKeys");
        List<PrimaryKey> primaryKeys = null;
        DB instance = null;
        instance.setPrimaryKeys(primaryKeys);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
