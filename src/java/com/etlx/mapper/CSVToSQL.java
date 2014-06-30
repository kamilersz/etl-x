/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kamilersz
 */
public class CSVToSQL {

    public static String nameAbbrev(String text) {
        String result = "";
        boolean lastIsLetter = false;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            boolean currIsLetter = Character.isLetter(ch);
            if (!lastIsLetter && currIsLetter) {
                result += ch;
            }
            lastIsLetter = currIsLetter;
        }
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        if (args.length < 2) {
            System.err.println("Insufficent parameter");
        } else {
            String type = args[0];
            if ("table".equalsIgnoreCase(type)) {
        parseTable(args[1]);
            }else if ("pk".equalsIgnoreCase(type)) {
        parsePk(args[1]);
            }else if ("fk".equalsIgnoreCase(type)) {
        parseFk(args[1]);
            }
        }
    }
    
    public static void parsePk(String args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(args));
        try {
            String line = br.readLine();
            String table, col;
            String[] parse;

            while (line != null) {
                parse = line.split("\t", -1);
                table = parse[0];
                col = parse[1];
                System.out.println("ALTER TABLE "+table+" ADD (CONSTRAINT "+((table.length() >10)?nameAbbrev(table):table) +"_PK PRIMARY KEY (");
                System.out.println(col+"));");

                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
    
    public static void parseFk(String args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(args));
        try {
            String line = br.readLine();
            String tableFrom, colFrom, tableTo, colTo;
            String[] parse;

            while (line != null) {
                parse = line.split("\t", -1);
                tableFrom = parse[0];
                colFrom = parse[1];
                tableTo = parse[2];
                colTo = parse[3];
                System.out.println("ALTER TABLE " + tableFrom);
                System.out.println("ADD (CONSTRAINT " + nameAbbrev(tableFrom) + "_" + nameAbbrev(tableTo)+ "_FK");
                System.out.println("FOREIGN KEY(");
                System.out.println(colFrom);
                System.out.println(")REFERENCES "+tableTo+" (");
                System.out.println(colTo);
                System.out.println("));");

                line = br.readLine();
            }
        } finally {
            br.close();
        }

    }

    public static void parseTable(String args) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(args));
        try {
            String line = br.readLine();
            String prevTable = null, table;
            List attribute = null, type = null;
            String[] parse;
            attribute = new ArrayList();
            type = new ArrayList();

            while (line != null) {
                parse = line.split("\t", -1);
                table = parse[0];
                if (prevTable != null) {
                    if (table.compareTo(prevTable) != 0) {
                        System.out.println("CREATE table " + prevTable + " (");
                        for (int i = 0; i < attribute.size() - 1; i++) {
                            System.out.println(attribute.get(i) + " " + type.get(i) + ",");
                        }
                        System.out.println(attribute.get(attribute.size() - 1) + " " + type.get(attribute.size() - 1));
                        System.out.println(");");
                        attribute = new ArrayList();
                        type = new ArrayList();
                    }
                }
                prevTable = table;
                attribute.add(parse[1]);
                if (parse[2].length() > 5) {
                    if (false && parse[2].substring(0, 6).compareTo("NUMBER") == 0 && parse[2].indexOf('(') > 1) {
//                        System.out.println(parse[2].substring(parse[2].indexOf('(')+1,parse[2].indexOf(')')));
                        if (parse[2].indexOf('*') > 0) {
                            Integer ix = Integer.parseInt(parse[2].substring(parse[2].indexOf('*') + 1, parse[2].indexOf(')')));
                            type.add("NUMBER(*," + (ix % 10) + parse[2].substring(parse[2].indexOf(')')));
                        } else {
                            Integer ix = Integer.parseInt(parse[2].substring(parse[2].indexOf('(') + 1, parse[2].indexOf(')')));
                            if (ix > 36) {
                                type.add("NUMBER(" + (ix / 10) + "," + (ix % 10) + parse[2].substring(parse[2].indexOf(')')));
                            } else {
                                type.add(parse[2]);
                            }
                        }
                    } else {
                        type.add(parse[2]);
                    }
                } else {
                    type.add(parse[2]);
                }
                line = br.readLine();
            }
            System.out.println("CREATE table " + prevTable + " (");
            for (int i = 0; i < attribute.size() - 1; i++) {
                System.out.println(attribute.get(i) + " " + type.get(i) + ",");
            }
            System.out.println(attribute.get(attribute.size() - 1) + " " + type.get(attribute.size() - 1));
            System.out.println(");");
        } finally {
            br.close();
        }

    }
}
