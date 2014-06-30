/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author kamilersz
 */
public class MetaDataReaderTest {
    
    public MetaDataReaderTest() {
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
     * Test of toJSON method, of class MetaDataReader.
     */
    @Test
    public void testToJSON() throws Exception {
        System.out.println("toJSON");
        String filename = "";
        MetaDataReader instance = null;
        instance.toJSON(filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTables method, of class MetaDataReader.
     */
    @Test
    public void testGetTables() {
        System.out.println("getTables");
        MetaDataReader instance = null;
        List expResult = null;
        List result = instance.getTables();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getForeignKeys method, of class MetaDataReader.
     */
    @Test
    public void testGetForeignKeys() {
        System.out.println("getForeignKeys");
        MetaDataReader instance = null;
        List expResult = null;
        List result = instance.getForeignKeys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrimaryKeys method, of class MetaDataReader.
     */
    @Test
    public void testGetPrimaryKeys() {
        System.out.println("getPrimaryKeys");
        MetaDataReader instance = null;
        List expResult = null;
        List result = instance.getPrimaryKeys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
