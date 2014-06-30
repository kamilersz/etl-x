/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.matcher;

import com.etlx.metadata.*;
import com.etlx.util.StringDistances;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

/**
 *
 * @author kamilersz
 */
public class SchemaMatcher extends Matcher {

    private DB db1 = null;
    private DB db2 = null;
    private double trshld;
    private long num = 0;

    public SchemaMatcher(DB db1, DB db2, double trshld) {
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
        Iterator it;
        Comparator colComp = new Comparator<Entry<Entry<Column, String>, Double>>() {

            @Override
            public int compare(Entry<Entry<Column, String>, Double> lhs, Entry<Entry<Column, String>, Double> rhs) {
                if (lhs.getValue() > rhs.getValue()) {
                    return -1;
                }
                if (lhs.getValue() == rhs.getValue()) {
                    return 0;
                }
                return +1;
            }
        };
        Comparator tabComp = new Comparator<Entry<Table, Double>>() {

            @Override
            public int compare(Entry<Table, Double> lhs, Entry<Table, Double> rhs) {
                if (lhs.getValue() > rhs.getValue()) {
                    return -1;
                }
                if (lhs.getValue() == rhs.getValue()) {
                    return 0;
                }
                return +1;
            }
        };

        for (Table t2 : db2.getTables()) {
            PriorityQueue<Entry<Table, Double>> max_table = new PriorityQueue<Entry<Table, Double>>(5, tabComp);
            double distance, distance_table;
            for (Table t1 : db1.getTables()) {
                for (Column cl2 : t2.getColumns()) {
                    PriorityQueue<Entry<Entry<Column, String>, Double>> max = new PriorityQueue<Entry<Entry<Column, String>, Double>>(5, colComp);
                    String st2 = cl2.getName();
                    if (st2 != null) {
                        st2 = st2.toUpperCase();
                    }
                    for (Column cl1 : t1.getColumns()) {
                        String st1 = cl1.getName();
                        if (st1 != null) {
                            st1 = st1.toUpperCase();
                        }
                        if (st1 != null || st2 != null) {
                            distance = pia1 * (StringDistances.compareName(st1, st2) * 0.7 + StringDistances.compareTableName(t1.getName().toUpperCase(), t2.getName().toUpperCase()) * 0.3);
                            //distance = pia1 * StringDistances.compareName(t1.getName().toUpperCase()+"_"+st1, t2.getName().toUpperCase()+" " +st2);
                        } else {
                            distance = pia1;
                        }
                        if ((cl1.getDataType() != null && cl2.getDataType() != null) ? cl1.getDataType().equals(cl2.getDataType()) : false) {
                        } else {
                            distance += pia2;
                        }
                        if ((1 - distance) > trshld) {
                            max.add(new SimpleEntry(new SimpleEntry(cl1, t1.getName()), distance));
                            if (max.size() > 5) {
                                max.poll();
                            }
                        }

                    }
                    it = max.iterator();
                    while (it.hasNext()) {
                        Entry<Entry<Column, String>, Double> e = (Entry) it.next();
                        if (1 - e.getValue() > trshld) {
                            String domain1 = e.getKey().getValue(), domain2 = t2.getName();
                            addColumnDist(domain1, e.getKey().getKey().getName(), domain2, cl2.getName(), "=", 1. - e.getValue());
                            addTableDpDist(domain2, domain1);
                            System.out.println(++num + "\t" + domain1 + "\t" + e.getKey().getKey().getName() + "\t" + domain2 + "\t" + st2 + "\t" + (1 - e.getValue()));
                        }
                    }
                }

                distance_table = pic1 * StringDistances.compareTableName(t1.getName().toUpperCase(), t2.getName().toUpperCase());
                if ((1 - distance_table) > trshld) {
                    System.out.println("add " + t1.getName());
                    max_table.add(new SimpleEntry(t1, distance_table));
                    if (max_table.size() > 5) {
                        max_table.poll();
                    }
                }

            }
            it = max_table.iterator();
            while (it.hasNext()) {
                Entry<Table, Double> e = (Entry) it.next();
                if (1 - e.getValue() > trshld) {
                    addTableDist(e.getKey().getName(), t2.getName(), "=", 1. - e.getValue());
                    System.out.println(++num + "\t" + e.getKey().getName() + "\t\t" + t2.getName() + "\t\t" + (1 - e.getValue()));
                }
            }
        }
        generateOpDist();
        matchDpDist();
        matchOpDist();
        //matchFkCol();
        //matchPk();
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
