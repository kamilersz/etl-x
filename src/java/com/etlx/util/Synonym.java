/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author kamilersz
 */
public class Synonym {

    private static HashMap<String, String> synoList = null;
    static Synonym singleton = null;

    private Synonym() {
        synoList = new HashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("synonym.txt"));
            String line = br.readLine();
            while(line != null) {
            String[] tokens = line.split("\t");
            synoList.put(tokens[0], tokens[1]);
            line = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("File not found");
        } finally {
        }
    }

    public static String get(String word) {
        if (singleton == null) {
            singleton = new Synonym();
        }
        return synoList.get(word);
    }

    public static String synonymize(String word) {
        Vector<String> st = StringDistances.tokenize(word);
        for (String s : st) {
            String syno = get(s);
            if (syno != null) {
                word = word.replaceFirst(s, syno);
            }
        }
        return word;
    }
}
