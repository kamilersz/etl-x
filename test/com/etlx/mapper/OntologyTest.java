/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import com.etlx.metadata.ForeignKey;
import com.etlx.metadata.PrimaryKey;
import com.etlx.metadata.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;

/**
 *
 * @author kamilersz
 */
public class OntologyTest {
    
    public OntologyTest() {
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
     * Test of loadOntology method, of class Ontology.
     */
    @Test
    public void testLoadOntology() throws Exception {
        System.out.println("loadOntology");
        String filename = "";
        Ontology instance = new Ontology();
        instance.loadOntology(filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOntology method, of class Ontology.
     */
    @Test
    public void testSaveOntology() {
        System.out.println("saveOntology");
        OWLOntology onto = null;
        String filename = "";
        Ontology instance = new Ontology();
        instance.saveOntology(onto, filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dbSchemaToOnto method, of class Ontology.
     */
    @Test
    public void testDbSchemaToOnto() throws Exception {
        System.out.println("dbSchemaToOnto");
        List<Table> tables = null;
        List<PrimaryKey> pks = null;
        List<ForeignKey> fks = null;
        Ontology instance = new Ontology();
        OWLOntology expResult = null;
        OWLOntology result = instance.dbSchemaToOnto(tables, pks, fks);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Ontology.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Ontology.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRange method, of class Ontology.
     */
    @Test
    public void testGetRange() {
        System.out.println("getRange");
        Object o = null;
        Object p = null;
        int asserted = 0;
        Set expResult = null;
        Set result = Ontology.getRange(o, p, asserted);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataProperties method, of class Ontology.
     */
    @Test
    public void testGetDataProperties() {
        System.out.println("getDataProperties");
        Object o = null;
        Object c = null;
        int local = 0;
        int asserted = 0;
        int named = 0;
        Set expResult = null;
        Set result = Ontology.getDataProperties(o, c, local, asserted, named);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectPropertiesDomain method, of class Ontology.
     */
    @Test
    public void testGetObjectPropertiesDomain() {
        System.out.println("getObjectPropertiesDomain");
        Object o = null;
        Object c = null;
        Set expResult = null;
        Set result = Ontology.getObjectPropertiesDomain(o, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjectPropertiesRange method, of class Ontology.
     */
    @Test
    public void testGetObjectPropertiesRange() {
        System.out.println("getObjectPropertiesRange");
        Object o = null;
        Object c = null;
        Set expResult = null;
        Set result = Ontology.getObjectPropertiesRange(o, c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyDomain method, of class Ontology.
     */
    @Test
    public void testGetPropertyDomain() {
        System.out.println("getPropertyDomain");
        Object o = null;
        Object p = null;
        OWLClass expResult = null;
        OWLClass result = Ontology.getPropertyDomain(o, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyRange method, of class Ontology.
     */
    @Test
    public void testGetPropertyRange() {
        System.out.println("getPropertyRange");
        Object o = null;
        Object p = null;
        OWLClass expResult = null;
        OWLClass result = Ontology.getPropertyRange(o, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClasses method, of class Ontology.
     */
    @Test
    public void testGetClasses() {
        System.out.println("getClasses");
        Ontology instance = new Ontology();
        Set expResult = null;
        Set result = instance.getClasses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOps method, of class Ontology.
     */
    @Test
    public void testGetOps() {
        System.out.println("getOps");
        Ontology instance = new Ontology();
        Set expResult = null;
        Set result = instance.getOps();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubClassOf method, of class Ontology.
     */
    @Test
    public void testGetSubClassOf() {
        System.out.println("getSubClassOf");
        Ontology instance = new Ontology();
        HashMap expResult = null;
        HashMap result = instance.getSubClassOf();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
