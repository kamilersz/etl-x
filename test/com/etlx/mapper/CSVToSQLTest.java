/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author kamilersz
 */
public class CSVToSQLTest {
    
    public CSVToSQLTest() {
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
     * Test of nameAbbrev method, of class CSVToSQL.
     */
    @Test
    public void testNameAbbrev() {
        System.out.println("nameAbbrev");
        String text = "";
        String expResult = "";
        String result = CSVToSQL.nameAbbrev(text);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CSVToSQL.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        CSVToSQL.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parsePk method, of class CSVToSQL.
     */
    @Test
    public void testParsePk() throws Exception {
        System.out.println("parsePk");
        String[] args = null;
        CSVToSQL.parsePk(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseFk method, of class CSVToSQL.
     */
    @Test
    public void testParseFk() throws Exception {
        System.out.println("parseFk");
        String[] args = null;
        CSVToSQL.parseFk(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseTable method, of class CSVToSQL.
     */
    @Test
    public void testParseTable() throws Exception {
        System.out.println("parseTable");
        String[] args = null;
        CSVToSQL.parseTable(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
