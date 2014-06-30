/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.matcher;

import com.etlx.mapper.Ontology;
import java.util.Vector;
import java.util.Set;
import java.util.Properties;
import java.lang.Integer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.semanticweb.owl.align.Alignment;
import org.semanticweb.owl.align.AlignmentProcess;
import org.semanticweb.owl.align.Cell;
import org.semanticweb.owl.align.AlignmentException;

import fr.inrialpes.exmo.align.impl.DistanceAlignment;
import fr.inrialpes.exmo.ontowrap.HeavyLoadedOntology;
import fr.inrialpes.exmo.ontowrap.OntologyFactory;
import fr.inrialpes.exmo.ontowrap.OntowrapException;

import fr.inrialpes.exmo.ontosim.string.StringDistances;
import java.util.logging.Level;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;

/**
 * This class has been built for ISWC experiments with bibliography. It
 * implements a non iterative (one step) OLA algorithms based on the name of
 * classes and properties. It could be made iterative by just adding
 * range/domain on properties... The parameters are: - threshold: above what do
 * we select for the alignment; - epsillon [ignored]: for convergence - pic1:
 * weigth for class name - pic2: weight for class attributes - pia1 [ignored=1]:
 * weigth for property name - pia3 [ignored=0]: weigth for property domain -
 * pia4 [ignored=0]: weigth for property range
 *
 * @author Jérôme Euzenat
 * @version $Id: StrucSubsDistAlignment.java 1866 2014-01-14 14:34:59Z euzenat $
 */
public class OntoAlignment extends DistanceAlignment implements AlignmentProcess {

    final static Logger logger = LoggerFactory.getLogger(OntoAlignment.class);
    private HeavyLoadedOntology<Object> honto1 = null;
    private HeavyLoadedOntology<Object> honto2 = null;

    /**
     * Creation *
     */
    public OntoAlignment() {
        setType("**");
    }

    ;

    /**
     * Initialisation
     * The class requires HeavyLoadedOntologies
     */
    @Override
    public void init(Object o1, Object o2, Object ontologies) throws AlignmentException {
        super.init(o1, o2, ontologies);
        if (!(getOntologyObject1() instanceof HeavyLoadedOntology
                && getOntologyObject1() instanceof HeavyLoadedOntology)) {
            throw new AlignmentException("OntoAlignment requires HeavyLoadedOntology ontology loader");
        }
    }

    /**
     * Processing *
     */
    // Could better use similarity
    @Override
    public void align(Alignment alignment, Properties params) throws AlignmentException {
        loadInit(alignment);
        honto1 = (HeavyLoadedOntology<Object>) getOntologyObject1();
        honto2 = (HeavyLoadedOntology<Object>) getOntologyObject2();
        double threshold = 1.; // threshold above which distances are too high
        int i, j = 0;     // index for onto1 and onto2 classes
        int nbclass1 = 0; // number of classes in onto1
        int nbclass2 = 0; // number of classes in onto2
        Vector<Object> classlist2 = new Vector<Object>(10); // onto2 classes
        Vector<Object> classlist1 = new Vector<Object>(10); // onto1 classes
        double classmatrix[][];   // class distance matrix
        int nbprop1 = 0; // number of properties in onto1
        int nbprop2 = 0; // number of properties in onto2
        Vector<Object> proplist2 = new Vector<Object>(10); // onto2 properties
        Vector<Object> proplist1 = new Vector<Object>(10); // onto1 properties
        double propmatrix[][];   // properties distance matrix
        double pic1 = (params.getProperty("classNameWeight")==null)? 0.5:Integer.parseInt(params.getProperty("classNameWeight")); // class weigth for name
        double pic2 = (params.getProperty("classPropertyWeight")==null)? 0.5:Integer.parseInt(params.getProperty("classPropertyWeight")); // class weight for properties
        double pia1 = (params.getProperty("attNameWeight")==null)? 0.75:Integer.parseInt(params.getProperty("attNameWeight")); // relation weight for name
        double pia2 = (params.getProperty("attTypeWeight")==null)? 0.25:Integer.parseInt(params.getProperty("attTypeWeight")); // relation weight for range
        double epsillon = 0.05; // stoping condition

        try {
            // Create property lists and matrix
//            for (Object prop : honto1.getObjectProperties()) {
//                nbprop1++;
//                proplist1.add(prop);
//            }
            for (Object prop : honto1.getDataProperties()) {
                nbprop1++;
                proplist1.add(prop);
            }
//            for (Object prop : honto2.getObjectProperties()) {
//                nbprop2++;
//                proplist2.add(prop);
//            }
            for (Object prop : honto2.getDataProperties()) {
                nbprop2++;
                proplist2.add(prop);
            }
            propmatrix = new double[nbprop1 + 1][nbprop2 + 1];

            // Create class lists
            for (Object cl : honto1.getClasses()) {
                nbclass1++;
                classlist1.add(cl);
            }
            for (Object cl : honto2.getClasses()) {
                nbclass2++;
                classlist2.add(cl);
            }
            classmatrix = new double[nbclass1 + 1][nbclass2 + 1];

            logger.debug("Initializing property distances");

            for (i = 0; i < nbprop1; i++) {
                Object cl1 = proplist1.get(i);
                String st1 = honto1.getEntityName(cl1);
                if (st1 != null) {
                    st1 = st1.toLowerCase();
                }
                Set drSet1 = Ontology.getRange(honto1.getOntology(), cl1, OntologyFactory.ANY);
                for (j = 0; j < nbprop2; j++) {
                    Object cl2 = proplist2.get(j);
                    String st2 = honto2.getEntityName(cl2);
                    if (st2 != null) {
                        st2 = st2.toLowerCase();
                    }
                    Set drSet2 = Ontology.getRange(honto1.getOntology(), cl1, OntologyFactory.ANY);
                    if (st1 != null || st2 != null) {
                        propmatrix[i][j] = pia1 * StringDistances.ngramDistance(st1, st2);
                    } else {
                        propmatrix[i][j] = pia1;
                    }
                    if (drSet1.isEmpty() || drSet2.isEmpty()) {
                        propmatrix[i][j] += pia2;
                    } else {
                        OWLDatatypeRestriction odr1 = (OWLDatatypeRestriction) drSet1.iterator().next();
                        OWLDatatypeRestriction odr2 = (OWLDatatypeRestriction) drSet2.iterator().next();
                        if (odr1.getDatatype() == odr2.getDatatype()) {
                            double error = 0;
                            for (OWLFacetRestriction ofr1 : odr1.getFacetRestrictions()) {
                                for (OWLFacetRestriction ofr2 : odr2.getFacetRestrictions()) {
                                    if (ofr1.getFacet() == ofr2.getFacet()) {
                                        int r1 = 1 + Integer.parseInt(ofr1.getFacetValue().getLiteral());
                                        int r2 = 1 + Integer.parseInt(ofr2.getFacetValue().getLiteral());
                                        double dist = (r1 > r2)?(r2/r1):(r1/r2);
                                        error = (error==0)?(dist):((error+dist)/2);
                                    }
                                }
                            }
                            propmatrix[i][j] += pia2 * error;
                        } else {
                            propmatrix[i][j] += pia2;
                        }
                    }
                }
            }

            // Initialize class distances
            logger.debug("Initializing class distances");
            for (i = 0; i < nbclass1; i++) {
                Object cl1 = classlist1.get(i);
                for (j = 0; j < nbclass2; j++) {
                    classmatrix[i][j] = pic1 * StringDistances.ngramDistance(honto1.getEntityName(cl1).toLowerCase(), honto2.getEntityName(classlist2.get(j)).toLowerCase());
                }
            }
        } catch (OntowrapException owex) {
            throw new AlignmentException("Error accessing ontology", owex);
        }

        // Iterate until completion
        double factor = 1.0;
        while (factor > epsillon) {
            // Compute property distances
            // -- FirstExp: nothing to be done: one pass
            // Here create the best matches for property distance already
            // -- FirstExp: goes directly in the alignment structure
            //    since it will never be refined anymore...
            logger.debug("Storing property alignment");
            for (i = 0; i < nbprop1; i++) {
                boolean found = false;
                int best = 0;
                double max = threshold;
                for (j = 0; j < nbprop2; j++) {
                    try {
                        Cell x = addAlignCell(proplist1.get(i), proplist2.get(j), "=", 1. - propmatrix[i][j]);
                        String domain1 = null, domain2 = null;
                        for (Object domain : honto1.getDomain(proplist1.get(i), OntologyFactory.ANY)) {
                            domain1 = (domain1 == null) ? honto1.getEntityName(domain) : domain1 + "," + honto1.getEntityName(domain);
                        }
                        x.setExtension(null, "\"align:domain1\"", domain1);
                        for (Object domain : honto2.getDomain(proplist2.get(j), OntologyFactory.ANY)) {
                            domain2 = (domain2 == null) ? honto2.getEntityName(domain) : domain2 + "," + honto2.getEntityName(domain);
                        }
                        x.setExtension(null, "\"align:domain2\"", domain2);
                        x.setExtension(null, "\"align:type\"", (honto1.isDataProperty(proplist1.get(i))) ? "column" : "fk");
                    } catch (OntowrapException owex) {
                        throw new AlignmentException("Error accessing ontology", owex);
                    }
                }
            }

            logger.debug("Computing class distances");
            // Compute classes distances
            // -- for all of its attribute, find the best match if possible... easy
            // -- simply replace in the matrix the value by the value plus the 
            // classmatrix[i][j] =
            // pic1 * classmatrix[i][j]
            // + pic2 * 2 *
            //  (sigma (att in c[i]) getAllignCell... )
            //  / nbatts of c[i] + nbatts of c[j]
            try {
                for (i = 0; i < nbclass1; i++) {
                    Set<? extends Object> properties1 = honto1.getProperties(classlist1.get(i), OntologyFactory.ANY, OntologyFactory.ANY, OntologyFactory.ANY);
                    int nba1 = properties1.size();
                    if (nba1 > 0) { // if not, keep old values...
                        //Set correspondences = new HashSet();
                        for (j = 0; j < nbclass2; j++) {
                            Set<? extends Object> properties2 = honto2.getProperties(classlist2.get(j), OntologyFactory.ANY, OntologyFactory.ANY, OntologyFactory.ANY);
                            int nba2 = properties2.size();
                            double attsum = 0.;
                            // check that there is a correspondance
                            // in list of class2 atts and add their weights
                            for (Object prp : properties1) {
                                Set<Cell> s2 = getAlignCells1(prp);
                                // Find the property with the higest similarity
                                // that is matched here
                                double currentValue = 0.;
                                for (Cell c2 : s2) {
                                    if (properties2.contains(c2.getObject2())) {
                                        double val = c2.getStrength();
                                        if (val > currentValue) {
                                            currentValue = val;
                                        }
                                    }
                                }
                                attsum = attsum + 1 - currentValue;
                            }
                            classmatrix[i][j] = classmatrix[i][j]
                                    + pic2 * (2 * attsum / (nba1 + nba2));
                        }
                    }
                }
            } catch (OntowrapException owex) {
                throw new AlignmentException("Error accessing ontology", owex);
            }

            // Assess factor
            // -- FirstExp: nothing to be done: one pass
            factor = 0.;
        }

        // This mechanism should be parametric!
        // Select the best match
        // There can be many algorithm for these:
        // n:m: get all of those above a threshold
        // 1:1: get the best discard lines and columns and iterate
        // Here we basically implement ?:* because the algorithm
        // picks up the best matching object above threshold for i.
        logger.debug("Storing class alignment");

        for (i = 0; i < nbclass1; i++) {
            boolean found = false;
            int best = 0;
            double max = threshold;
            for (j = 0; j < nbclass2; j++) {
                if (classmatrix[i][j] < max) {
                    found = true;
                    best = j;
                    max = classmatrix[i][j];
                }
            }
            if (found) {
                Cell c = addAlignCell(classlist1.get(i), classlist2.get(best), "=", 1. - max);
                c.setExtension(null, "\"align:type\"", "table");
            }
        }
    }
}
