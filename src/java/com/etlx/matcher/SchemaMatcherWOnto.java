/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.matcher;

import com.etlx.metadata.*;
import com.etlx.util.StringDistances;
import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author kamilersz
 */
public class SchemaMatcherWOnto extends Matcher {

    private DB db1 = null;
    private DB db2 = null;
    private double trshld;
    private long num = 0;

    public SchemaMatcherWOnto(DB db1, DB db2, Object ontology, double trshld) {
        this.db1 = db1;
        this.db2 = db2;
        this.trshld = trshld;

    }

    private void generateOpDist() {
        Iterator it = tableDist.iterator();
        while (it.hasNext()) {
            Entry<String, Entry<String, Entry<String, Double>>> e = (Entry) it.next();
            String table2 = e.getKey();
            String table1 = e.getValue().getKey();
            ArrayList<String> top1 = new ArrayList();
            ArrayList<String> top2 = new ArrayList();
            ArrayList<String> bot1 = new ArrayList();
            ArrayList<String> bot2 = new ArrayList();
            for (ForeignKey fk1 : db1.getForeignKeys()) {
                if (fk1.getTableFrom().equals(table1)) {
                    top1.add(fk1.getTableTo());
                }
                if (fk1.getTableTo().equals(table1)) {
                    bot1.add(fk1.getTableFrom());
                }
            }
            for (ForeignKey fk2 : db2.getForeignKeys()) {
                if (fk2.getTableFrom().equals(table2)) {
                    top2.add(fk2.getTableTo());
                }
                if (fk2.getTableTo().equals(table2)) {
                    bot2.add(fk2.getTableFrom());
                }
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
                    String domain1 = best_col_domain, domain2 = t2.getName();
                    addColumnDist(domain1, best_col.getName(), domain2, cl2.getName(), "=", 1. - max);
                    addTableDpDist(domain2, domain1);
                    System.out.println(++num + "\t" + domain1 + "\t" + best_col.getName() + "\t" + domain2 + "\t" + st2 + "\t" + (1 - max));
                }

            }
            if (found_table && (1 - max_table) > trshld) {
                addTableDist(best_tbl.getName(), t2.getName(), "=", 1. - max_table);
                System.out.println(++num + "\t" + best_tbl.getName() + "\t\t" + t2.getName() + "\t\t" + (1 - max_table));
            }
        }
        generateOpDist();
        matchDpDist();
        //matchOpDist();
        matchFkCol();
        matchPk();
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

        Iterator it = tableDist.iterator();
        while (it.hasNext()) {
            Entry<String, Entry<String, Entry<String, Double>>> e = (Entry) it.next();
            String table2 = e.getKey();
            String table1 = e.getValue().getKey();
            List<String> pk1c = null;
            List<String> pk2c = null;
            for (PrimaryKey pk : db1.getPrimaryKeys()) {
                if (pk.getTableName().equals(table1)) {
                    pk1c = pk.getPrimaryColumns();
                    break;
                }
            }
            for (PrimaryKey pk : db2.getPrimaryKeys()) {
                if (pk.getTableName().equals(table2)) {
                    pk2c = pk.getPrimaryColumns();
                    break;
                }
            }
            if (pk1c != null && pk2c != null) {
                for (String s2 : pk2c) {
                    if (!isMapped(table2, s2)) {
                        for (String s1 : pk1c) {
                            addColumnDist(table1, s1, table2, s2, "=", 1);
                            System.out.println(++num + "\t" + table1 + "\t" + s1 + "\t" + table2 + "\t" + s2 + "\t" + 1);
                        }
                    }
                }
            }
        }
    }

    private void matchFkCol() {

        Iterator it = tableDist.iterator();
        while (it.hasNext()) {
            Entry<String, Entry<String, Entry<String, Double>>> e = (Entry) it.next();
            String table2 = e.getKey();
            String table1 = e.getValue().getKey();
            HashSet<String> top1 = new HashSet();
            HashSet<String> top2 = new HashSet();
            for (ForeignKey fk1 : db1.getForeignKeys()) {
                if (fk1.getTableFrom().equals(table1)) {
                    top1.add(fk1.getTableTo());
                }
            }
            for (ForeignKey fk2 : db2.getForeignKeys()) {
                if (fk2.getTableFrom().equals(table2)) {
                    top2.add(fk2.getTableTo());
                }
            }
            for (String s1 : top1) {
                for (String s2 : top2) {
                    if (s1.equals(getTableMatch(s2))) {
                        for (ForeignKey fk1 : db1.getForeignKeys()) {
                            if (fk1.getTableFrom().equals(table1) && fk1.getTableTo().equals(s1)) {
                                for (ForeignKey fk2 : db2.getForeignKeys()) {
                                    if (fk2.getTableFrom().equals(table2) && fk2.getTableTo().equals(s2)) {
                                        for (String sc2 : fk2.getColFrom()) {
                                            for (String sc1 : fk1.getColFrom()) {
                                                addColumnDist(table1, sc1, table2, sc2, "=", 1);
                                                System.out.println(++num + "\t" + table1 + "\t" + sc1 + "\t" + table2 + "\t" + sc2 + "\t" + 1);
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
