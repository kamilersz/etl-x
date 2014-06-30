/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.matcher;

import com.etlx.mapper.Ontology;
import com.etlx.metadata.*;
import com.etlx.util.StringDistances;
import fr.inrialpes.exmo.ontowrap.HeavyLoadedOntology;
import fr.inrialpes.exmo.ontowrap.LoadedOntology;
import fr.inrialpes.exmo.ontowrap.OntologyFactory;
import fr.inrialpes.exmo.ontowrap.OntowrapException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.owl.align.AlignmentException;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;

/**
 *
 * @author kamilersz
 */
public class OntoMatcher extends Matcher {

    private HeavyLoadedOntology<Object> onto1 = null;
    private HeavyLoadedOntology<Object> onto2 = null;
    private double trshld;
    private long num = 0;

    public OntoMatcher(Object onto1, Object onto2, double trshld) throws AlignmentException {
        if (onto1 instanceof URI && onto2 instanceof URI) {
            this.onto1 = (HeavyLoadedOntology<Object>) loadOntology((URI) onto1);
            this.onto2 = (HeavyLoadedOntology<Object>) loadOntology((URI) onto2);
            System.out.println("Ontology loaded");
        }
        this.trshld = trshld;

    }

    static LoadedOntology loadOntology(URI ref) throws AlignmentException {
        OntologyFactory factory = OntologyFactory.getFactory();
        try {
            return factory.loadOntology(ref);
        } catch (OntowrapException owex) {
            throw new AlignmentException("Cannot load ontology " + ref, owex);
        }
    }

    private void generateOpDist() throws OntowrapException, URISyntaxException {
        Iterator it = tableDist.iterator();
        while (it.hasNext()) {
            Entry<String, Entry<String, Entry<String, Double>>> e = (Entry) it.next();
            String table2 = e.getKey();
            String table1 = e.getValue().getKey();
            ArrayList<String> top1 = new ArrayList();
            ArrayList<String> top2 = new ArrayList();
            ArrayList<String> bot1 = new ArrayList();
            ArrayList<String> bot2 = new ArrayList();
            for (Object op1 : Ontology.getObjectPropertiesDomain(onto1.getOntology(), onto1.getEntity(new URI(onto1.getURI().toString() + "#" + table1)))) {
                top1.add(onto1.getEntityName(Ontology.getPropertyRange(onto1.getOntology(), op1)));
            }
            for (Object op1 : Ontology.getObjectPropertiesRange(onto1.getOntology(), onto1.getEntity(new URI(onto1.getURI().toString() + "#" + table1)))) {
                bot1.add(onto1.getEntityName(Ontology.getPropertyDomain(onto1.getOntology(), op1)));
            }
            for (Object op2 : Ontology.getObjectPropertiesDomain(onto2.getOntology(), onto2.getEntity(new URI(onto2.getURI().toString() + "#" + table2)))) {
                top2.add(onto2.getEntityName(Ontology.getPropertyRange(onto2.getOntology(), op2)));
            }
            for (Object op2 : Ontology.getObjectPropertiesRange(onto2.getOntology(), onto2.getEntity(new URI(onto2.getURI().toString() + "#" + table2)))) {
                bot2.add(onto2.getEntityName(Ontology.getPropertyDomain(onto2.getOntology(), op2)));
            }
            for (String s1 : top1) {
                for (String s2 : top2) {
                    addTableOpDist(s2, s1);
                }
            }
            for (String s1 : bot1) {
                for (String s2 : bot2) {
                    addTableOpDist(s2, s1);
                }
            }

        }
    }

    @Override
    public void match(Properties params) {
        double threshold = 1.; // threshold above which distances are too high
        int i, j = 0;     // index for onto1 and onto2 classes
        double pic1 = (params.getProperty("classNameWeight") == null) ? 0.5 : Double.parseDouble(params.getProperty("classNameWeight")); // class weigth for name
        double pic2 = (params.getProperty("classPropertyWeight") == null) ? 0.5 : Double.parseDouble(params.getProperty("classPropertyWeight")); // class weight for properties
        double pia1 = (params.getProperty("attNameWeight") == null) ? 0.75 : Double.parseDouble(params.getProperty("attNameWeight")); // relation weight for name
        double pia2 = (params.getProperty("attTypeWeight") == null) ? 0.25 : Double.parseDouble(params.getProperty("attTypeWeight")); // relation weight for range
        double epsillon = 0.05; // stoping condition
        try {
            System.out.println("Now matching");
            for (Object t2 : onto2.getClasses()) {
                Set<? extends Object> dp2 = Ontology.getDataProperties(onto2.getOntology(), t2, OntologyFactory.ANY, OntologyFactory.ANY, OntologyFactory.ANY);
                int nba2 = dp2.size();
                if (nba2 > 0) {
                    boolean found_table = false;
                    Object best_tbl = null;
                    boolean found = false;
                    Object best_col = null;
                    String best_col_domain = null;
                    double max_table = threshold;
                    double distance, distance_table;
                    for (Object cl2 : dp2) {
                        double max = threshold;
                        String st2 = onto2.getEntityName(cl2);
                        if (st2 != null) {
                            st2 = st2.toUpperCase();
                        }
                        for (Object t1 : onto1.getClasses()) {
                            Set<? extends Object> dp1 = Ontology.getDataProperties(onto1.getOntology(), t1, OntologyFactory.ANY, OntologyFactory.ANY, OntologyFactory.ANY);
                            int nba1 = dp1.size();
                            if (nba1 > 0) {
                                for (Object cl1 : dp1) {
                                    String st1 = onto1.getEntityName(cl1);
                                    if (st1 != null) {
                                        st1 = st1.toUpperCase();
                                    }
                                    if (st1 != null || st2 != null) {
                                        distance = pia1 * (StringDistances.compareName(st1, st2) * 0.7 + StringDistances.compareTableName(onto1.getEntityName(t1).toUpperCase(), onto2.getEntityName(t2).toUpperCase()) * 0.3);
                                    } else {
                                        distance = pia1;
                                    }
                                    Set drSet1 = Ontology.getRange(onto1.getOntology(), cl1, OntologyFactory.ANY);
                                    Set drSet2 = Ontology.getRange(onto2.getOntology(), cl2, OntologyFactory.ANY);
                                    if (drSet1.isEmpty() || drSet2.isEmpty()) {
                                        distance += pia2;
                                    } else {
                                        OWLDatatypeRestriction odr1 = (OWLDatatypeRestriction) drSet1.iterator().next();
                                        OWLDatatypeRestriction odr2 = (OWLDatatypeRestriction) drSet2.iterator().next();
                                        if (odr1.getDatatype() == odr2.getDatatype()) {
                                        } else {
                                            distance += pia2;
                                        }
                                    }
                                    if (distance < max) {
                                        found = true;
                                        best_col = cl1;
                                        best_col_domain = onto1.getEntityName(t1);
                                        max = distance;
                                    }

                                }
                            }
                            distance_table = pic1 * StringDistances.compareTableName(onto1.getEntityName(t1).toUpperCase(), onto2.getEntityName(t2).toUpperCase());

                            if (distance_table < max_table) {
                                found_table = true;
                                best_tbl = t1;
                                max_table = distance_table;
                            }
                        }

                        if (found && (1 - max) > trshld) {
                            String domain1 = best_col_domain, domain2 = onto2.getEntityName(t2);
                            addColumnDist(domain1, onto1.getEntityName(best_col), domain2, onto2.getEntityName(cl2), "=", 1. - max);
                            addTableDpDist(domain2, domain1);
                            System.out.println(++num + "\t" + domain1 + "\t" + onto1.getEntityName(best_col) + "\t" + domain2 + "\t" + st2 + "\t" + (1 - max));
                        }

                    }
                    if (found_table && (1 - max_table) > trshld) {
                        addTableDist(onto1.getEntityName(best_tbl), onto2.getEntityName(t2), "=", 1. - max_table);
                        System.out.println(++num + "\t" + onto1.getEntityName(best_tbl) + "\t\t" + onto2.getEntityName(t2) + "\t\t" + (1 - max_table));
                    }
                }
            }
        } catch (OntowrapException ex) {
            System.out.println(ex);
        }
        try {
            generateOpDist();
            System.out.println(tableOpDist);
            //        matchDpDist();
            //        //matchOpDist();
            //        matchPk();
            //        matchPk();
        } catch (OntowrapException ex) {
            Logger.getLogger(OntoMatcher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(OntoMatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void matchDpDist() {
        Iterator it = tableDpDist.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, HashMap<String, Integer>> e = (Entry) it.next();
            String table2 = e.getKey();
            if (getTableMatch(table2) == null) {
                HashMap<String, Integer> x = e.getValue();
                Iterator jt = x.entrySet().iterator();
                Integer max = 0;
                String table1 = "";
                while (jt.hasNext()) {
                    Entry<String, Integer> e2 = (Entry) jt.next();
                    if (e2.getValue() > max) {
                        max = e2.getValue();
                        table1 = e2.getKey();
                    }
                }
                addTableDist(table1, table2, "=", 1);
                System.out.println(++num + "\t" + table1 + "\t\t" + table2 + "\t\t" + 1);
            }
        }

    }

    private void matchOpDist() {
        Iterator it = tableOpDist.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, HashMap<String, Integer>> e = (Entry) it.next();
            String table2 = e.getKey();
            if (getTableMatch(table2) == null) {
                HashMap<String, Integer> x = e.getValue();
                Iterator jt = x.entrySet().iterator();
                Integer max = 0;
                String table1 = "";
                while (jt.hasNext()) {
                    Entry<String, Integer> e2 = (Entry) jt.next();
                    if (e2.getValue() > max) {
                        max = e2.getValue();
                        table1 = e2.getKey();
                    }
                }
                addTableDist(table1, table2, "=", 1);
                System.out.println(++num + "\t" + table1 + "\t\t" + table2 + "\t\t" + 1);
            }
        }
    }

    private void matchPk() {

    }

    private void matchFkCol() {

    }
}
