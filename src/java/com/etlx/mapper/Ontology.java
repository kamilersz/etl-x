/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import com.etlx.metadata.Column;
import com.etlx.metadata.ForeignKey;
import com.etlx.metadata.PrimaryKey;
import com.etlx.metadata.Table;
import java.io.*;
import org.semanticweb.owlapi.vocab.OWLFacet.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.junit.Ignore;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.BufferingMode;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasoner;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import org.semanticweb.owlapi.util.OWLClassExpressionVisitorAdapter;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitor;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import uk.ac.manchester.cs.owlapi.modularity.ModuleType;
import uk.ac.manchester.cs.owlapi.modularity.SyntacticLocalityModuleExtractor;

/**
 *
 * @author kamilersz
 */
public class Ontology {

    private Set<OWLClass> classes;
    private Set<OWLObjectProperty> ops;
    private HashMap<OWLClass, List<OWLClass>> subClassOf;

    public Ontology() {
    }

    public void loadOntology(String filename) throws IOException, OWLOntologyCreationException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File(filename);
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Loaded ontology: " + ontology);
        IRI documentIRI = manager.getOntologyDocumentIRI(ontology);
        classes = (Set<OWLClass>) ontology.getClassesInSignature();
        for (OWLClass cls : getClasses()) {
            ontology.getSubClassAxiomsForSuperClass(cls);
            System.out.println(cls);
        }
        ops = (Set<OWLObjectProperty>) ontology.getObjectPropertiesInSignature();
        System.out.println("    from: " + documentIRI);
        manager.removeOntology(ontology);

    }

    public void saveOntology(OWLOntology onto, String filename) {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        try {
            File file = new File(filename);
            manager.saveOntology(onto, new OWLXMLOntologyFormat(),
                    IRI.create(file.toURI()));
        } catch (Exception ex) {
            Logger.getLogger(Ontology.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveOntology(OWLOntology onto, OutputStream o) {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        try {
            manager.saveOntology(onto, new OWLXMLOntologyFormat(),o);
        } catch (Exception ex) {
            Logger.getLogger(Ontology.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public OWLOntology dbSchemaToOnto(List<Table> tables, List<PrimaryKey> pks, List<ForeignKey> fks) throws OWLOntologyCreationException, OWLOntologyStorageException {
        String base = "http://www.semanticweb.org/kamilersz/ontologies/" + System.currentTimeMillis() + "/myOntology";
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLDataFactory factory = manager.getOWLDataFactory();
        OWLOntology dbOnto = manager.createOntology(IRI.create(base));
        for (Table t : tables) {
            OWLClass oc = factory.getOWLClass(IRI.create(base + "#" + t.getName()));
            OWLAxiom declareC = factory.getOWLDeclarationAxiom(oc);
            manager.addAxiom(dbOnto, declareC);
            for (Column c : t.getColumns()) {
                OWLDatatype colDT = null;
                OWLDataRange colDR = null;
                if (c.getDataType().equals("varchar") || c.getDataType().equals("char")) {
                    colDT = factory.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
                    OWLFacetRestriction stringLength = factory.getOWLFacetRestriction(OWLFacet.LENGTH, c.getLength());
                    colDR = factory.getOWLDatatypeRestriction(colDT, stringLength);
                } else if (c.getDataType().equals("date") || c.getDataType().equals("datetime")) {
                    colDT = factory.getOWLDatatype(OWL2Datatype.XSD_DATE_TIME.getIRI());
                    colDR = factory.getOWLDatatypeRestriction(colDT);
                } else if (c.getDataType().equals("number")) {
                    colDT = factory.getOWLDatatype(OWL2Datatype.XSD_DECIMAL.getIRI());
                    if (c.getLength() != 0) {
                        Set<OWLFacetRestriction> s = new HashSet<OWLFacetRestriction>();
                        s.add(factory.getOWLFacetRestriction(OWLFacet.LENGTH, c.getLength()));
                        s.add(factory.getOWLFacetRestriction(OWLFacet.TOTAL_DIGITS, c.getPrecision()));
                        colDR = factory.getOWLDatatypeRestriction(colDT, s);
                    } else {
                        colDR = factory.getOWLDatatypeRestriction(colDT);
                    }
                } else if (c.getDataType().equals("integer")) {
                    colDT = factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
                    colDR = factory.getOWLDatatypeRestriction(colDT);
                } else {
                    colDT = factory.getOWLDatatype(OWL2Datatype.XSD_DATE_TIME.getIRI());
                    colDR = factory.getOWLDatatypeRestriction(colDT);
                }
                OWLDataProperty colOnto = factory.getOWLDataProperty(IRI.create(base + "#" + c.getName()));
                OWLDataPropertyDomainAxiom colDom = factory.getOWLDataPropertyDomainAxiom(colOnto, oc);
                OWLDataPropertyRangeAxiom colRng = factory.getOWLDataPropertyRangeAxiom(colOnto, colDR);
                //OWLAxiom colRng = factory.getOWLData(colOnto, colDT);
                OWLAxiom declareDP = factory.getOWLDeclarationAxiom(colOnto);
                manager.addAxiom(dbOnto, declareDP);
                manager.addAxiom(dbOnto, colDom);
                manager.addAxiom(dbOnto, colRng);
            }
        }
        for (ForeignKey fk : fks) {
            OWLObjectProperty op = factory.getOWLObjectProperty(IRI.create(base + "#" + fk.getName()));
            OWLClass opfrom = factory.getOWLClass(IRI.create(base + "#" + fk.getTableFrom()));
            OWLClass opto = factory.getOWLClass(IRI.create(base + "#" + fk.getTableTo()));
            OWLAxiom declareOp = factory.getOWLDeclarationAxiom(op);
            OWLObjectPropertyDomainAxiom opd = factory.getOWLObjectPropertyDomainAxiom(op, opto);
            OWLObjectPropertyRangeAxiom opr = factory.getOWLObjectPropertyRangeAxiom(op, opfrom);
            manager.addAxiom(dbOnto, declareOp);
            manager.addAxiom(dbOnto, opd);
            manager.addAxiom(dbOnto, opr);
        }
        for (PrimaryKey pk : pks) {
            for (String col : pk.getPrimaryColumns()) {
                OWLDataProperty colOnto = factory.getOWLDataProperty(IRI.create(base + "#" + col));
                OWLFunctionalDataPropertyAxiom ofa = factory.getOWLFunctionalDataPropertyAxiom(colOnto);
                manager.addAxiom(dbOnto, ofa);
            }
        }
        return dbOnto;
    }

    public static void main(String[] args) throws IOException, OWLOntologyCreationException {
//        Ontology o = new Ontology();
//        o.loadOntology("ppdm.owl");
    }

    public static Set<Object> getRange(Object o, Object p, int asserted) {
        Set<Object> resultSet = new HashSet<Object>();
        for (Object ent : ((OWLProperty) p).getRanges((OWLOntology) o)) {
            if (ent instanceof OWLClass || ent instanceof OWLDataRange) {
                resultSet.add(ent);
            }
        }
        return resultSet;
    }

    public static Set<Object> getDataProperties(Object o, Object c, int local, int asserted, int named) {
        Set<Object> resultSet = new HashSet<Object>();
        for (Object ent : ((OWLClass) c).getReferencingAxioms((OWLOntology) o)) {
            if (ent instanceof OWLDataPropertyDomainAxiom) {
                for (Object drent : ((OWLDataPropertyDomainAxiom) ent).getDataPropertiesInSignature()) {
                    resultSet.add(drent);
                }
            }
        }
        return resultSet;
    }

    public static Set<Object> getObjectPropertiesDomain(Object o, Object c) {
        Set<Object> resultSet = new HashSet<Object>();
        for (Object ent : ((OWLClass) c).getReferencingAxioms((OWLOntology) o)) {
            if (ent instanceof OWLObjectPropertyDomainAxiom) {
                for (Object drent : ((OWLObjectPropertyDomainAxiom) ent).getObjectPropertiesInSignature()) {
                    resultSet.add(drent);
                }
            }
        }
        return resultSet;
    }

    public static Set<Object> getObjectPropertiesRange(Object o, Object c) {
        Set<Object> resultSet = new HashSet<Object>();
        for (Object ent : ((OWLClass) c).getReferencingAxioms((OWLOntology) o)) {
            if (ent instanceof OWLObjectPropertyRangeAxiom) {
                for (Object drent : ((OWLObjectPropertyRangeAxiom) ent).getObjectPropertiesInSignature()) {
                    resultSet.add(drent);
                }
            }
        }
        return resultSet;
    }
    
    public static OWLClass getPropertyDomain(Object o, Object p) {
        if (p instanceof OWLObjectProperty) {
            for (OWLObjectPropertyDomainAxiom ora : ((OWLOntology) o).getObjectPropertyDomainAxioms((OWLObjectProperty) p)) {
                return ora.getClassesInSignature().iterator().next();
            }
            return null;
        } else {
            return null;
        }
    }
    
    public static OWLClass getPropertyRange(Object o, Object p) {
        if (p instanceof OWLObjectProperty) {
            for (OWLObjectPropertyRangeAxiom ora : ((OWLOntology) o).getObjectPropertyRangeAxioms((OWLObjectProperty) p)) {
                return ora.getClassesInSignature().iterator().next();
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * @return the classes
     */
    public Set<OWLClass> getClasses() {
        return classes;
    }

    /**
     * @return the ops
     */
    public Set<OWLObjectProperty> getOps() {
        return ops;
    }

    /**
     * @return the subClassOf
     */
    public HashMap<OWLClass, List<OWLClass>> getSubClassOf() {
        return subClassOf;
    }
}
