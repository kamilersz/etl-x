/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.matcher;

import com.etlx.mapper.Ontology;
import com.etlx.metadata.*;
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

import com.etlx.util.StringDistances;
import com.etlx.util.Synonym;
import java.util.*;
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
public class SchemaAlignment extends DistanceAlignment implements AlignmentProcess {

    final static Logger logger = LoggerFactory.getLogger(SchemaAlignment.class);
    private DB db1 = null;
    private DB db2 = null;
    private double trshld;

    /**
     * Creation *
     */
    public SchemaAlignment(DB db1, DB db2, double trshld) {
        setType("**");
        this.db1 = db1;
        this.db2 = db2;
        this.trshld = trshld;
    }

    ;

    /**
     * Initialisation
     * The class requires HeavyLoadedOntologies
     */
    @Override
    public void init(Object o1, Object o2, Object ontologies) throws AlignmentException {
        super.init(null, null, ontologies);
        db1 = (DB) o1;
        db2 = (DB) o2;
    }

    /**
     * Processing *
     */
    // Could better use similarity
    @Override
    public void align(Alignment alignment, Properties params) throws AlignmentException {
        loadInit(alignment);
        double threshold = 1.; // threshold above which distances are too high
        int i, j = 0;     // index for onto1 and onto2 classes
        double pic1 = (params.getProperty("classNameWeight") == null) ? 0.5 : Integer.parseInt(params.getProperty("classNameWeight")); // class weigth for name
        double pic2 = (params.getProperty("classPropertyWeight") == null) ? 0.5 : Integer.parseInt(params.getProperty("classPropertyWeight")); // class weight for properties
        double pia1 = (params.getProperty("attNameWeight") == null) ? 0.75 : Integer.parseInt(params.getProperty("attNameWeight")); // relation weight for name
        double pia2 = (params.getProperty("attTypeWeight") == null) ? 0.25 : Integer.parseInt(params.getProperty("attTypeWeight")); // relation weight for range
        double epsillon = 0.05; // stoping condition

        long num = 0;
        for (Table t2 : db2.getTables()) {
            boolean found_table = false;
            Table best_tbl = null;
            boolean found = false;
            Column best_col = null;
            String best_col_domain = null;
            double max_table = threshold;
            double distance, distance_table;
            for (Column cl2 : t2.getColumns()) {
                double max = threshold;
                String st2 = cl2.getName();
                if (st2 != null) {
                    st2 = st2.toUpperCase();
                }
                for (Table t1 : db1.getTables()) {
                    for (Column cl1 : t1.getColumns()) {
                        String st1 = cl1.getName();
                        if (st1 != null) {
                            st1 = st1.toUpperCase();
                        }
                        if (st1 != null || st2 != null) {
                            distance = pia1 * (StringDistances.compareName(st1, st2) * 0.7 + StringDistances.compareTableName(t1.getName().toUpperCase(), t2.getName().toUpperCase()) * 0.3);
                        } else {
                            distance = pia1;
                        }
                        if ((cl1.getDataType() != null && cl2.getDataType() != null) ? cl1.getDataType().equals(cl2.getDataType()) : false) {
//                    double error = 0;
//                    if (cl1.getLength() != 0 && cl2.getLength() != 0) {
//                        int r1 = 1 + cl1.getLength();
//                        int r2 = 1 + cl2.getLength();
//                        double dist = (r1 > r2) ? (r2 / r1) : (r1 / r2);
//                        error = dist;
//                    }
//                    if (cl1.getPrecision() != 0 && cl2.getPrecision() != 0) {
//                        int r1 = 1 + cl1.getPrecision();
//                        int r2 = 1 + cl2.getPrecision();
//                        double dist = (r1 > r2) ? (r2 / r1) : (r1 / r2);
//                        error = (error + dist) / 2;
//                    }
//                    propmatrix[i][j] += pia2 * error;
                        } else {
                            distance += pia2;
                        }
                        if (distance < max) {
                            found = true;
                            best_col = cl1;
                            best_col_domain = t1.getName();
                            max = distance;
                        }

                    }
                    distance_table = pic1 * StringDistances.compareTableName(t1.getName().toUpperCase(), t2.getName().toUpperCase());

                    if (distance_table < max_table) {
                        found_table = true;
                        best_tbl = t1;
                        max_table = distance_table;
                    }
                }

                if (found && (1 - max) > trshld) {
                    try {
                        Cell x = addAlignCell(best_col, cl2, "=", 1. - max);
                        String domain1 = best_col_domain, domain2 = t2.getName();
                        x.setExtension(null, "\"align:domain1\"", domain1);
                        x.setExtension(null, "\"align:domain2\"", domain2);
                        x.setExtension(null, "\"align:type\"", "column");
                        System.out.println(++num + "\t" + domain1 + "\t" + best_col.getName() + "\t" + domain2 + "\t" + st2 + "\t" + (1 - max));
                    } catch (Exception owex) {
                        throw new AlignmentException("Error accessing ontology", owex);
                    }
                }

            }
            if (found_table && (1 - max_table) > trshld) {
                Cell c = addAlignCell(best_tbl, t2, "=", 1. - max_table);
                c.setExtension(null, "\"align:type\"", "table");
                System.out.println(++num + "\t" + best_tbl.getName() + "\t\t" + t2.getName() + "\t\t" + (1 - max_table));
            }
        }


        // Iterate until completion
//        double factor = 1.0;
//        while (factor > epsillon) {
//            for (i = 0; i < nbclass1; i++) {
//                List<ForeignKey> properties1 = db1.getForeignKeys();
//                int nba1 = properties1.size();
//                if (nba1 > 0) { // if not, keep old values...
//                    //Set correspondences = new HashSet();
//                    for (j = 0; j < nbclass2; j++) {
//                        List<ForeignKey> properties2 = db2.getForeignKeys();
//                        int nba2 = properties2.size();
//                        double attsum = 0.;
//                        // check that there is a correspondance
//                        // in list of class2 atts and add their weights
//                        for (Object prp : properties1) {
//                            Set<Cell> s2 = getAlignCells1(prp);
//                            // Find the property with the higest similarity
//                            // that is matched here
//                            double currentValue = 0.;
//                            for (Cell c2 : s2) {
//                                if (properties2.contains(c2.getObject2())) {
//                                    double val = c2.getStrength();
//                                    if (val > currentValue) {
//                                        currentValue = val;
//                                    }
//                                }
//                            }
//                            attsum = attsum + 1 - currentValue;
//                        }
//                        //classmatrix[i][j] = classmatrix[i][j] + pic2 * (2 * attsum / (nba1 + nba2));
//                    }
//                }
//            }
//
//            // Assess factor
//            // -- FirstExp: nothing to be done: one pass
//            factor = 0.;
//        }
    }
}
