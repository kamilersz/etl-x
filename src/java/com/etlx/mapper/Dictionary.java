/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kamilersz
 */
public class Dictionary {

    private int id;
    private String term;
    private int no;
    private String type;
    private String termClassification;
    private String meaning;
    private String synonym[];

    public Dictionary(int id, String term, int no, String type, String termClassification, String meaning, String synonyms) {
        this.id = id;
        this.term = term;
        this.no = no;
        this.type = type;
        this.termClassification = termClassification;
        this.meaning = meaning;
        this.synonym = synonyms.split(",");
    }

    @Override
    public String toString() {
        return term + " " + type + " " + termClassification;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the no
     */
    public int getNo() {
        return no;
    }

    /**
     * @param no the no to set
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the termClassification
     */
    public String getTermClassification() {
        return termClassification;
    }

    /**
     * @param termClassification the termClassification to set
     */
    public void setTermClassification(String termClassification) {
        this.termClassification = termClassification;
    }

    /**
     * @return the meaning
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * @param meaning the meaning to set
     */
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    /**
     * @return the synonyms
     */
    public String[] getSynonym() {
        return synonym;
    }

    private static Dictionary hashMapToDictionary(HashMap r) {
        Integer id = (Integer) r.get("id");
        String term = (String) r.get("term");
        Integer no = (Integer) r.get("no");
        String type = (String) r.get("type");
        String termClassification = (String) r.get("termclassification");
        String meaning = (String) r.get("meaning");
        String synonyms = (String) r.get("synonyms");
        return new Dictionary(id, term, no, type, termClassification, meaning, synonyms);
    }

    public static List<Dictionary> findByTerm(String findTerm) {
        Query q = new Query("mysql", "jdbc:mysql://localhost:3306/ta", "root", "");
        String[] param = {findTerm};
        List<HashMap<String, Object>> res = q.all("select * from dict where term = ?", param);
        List<Dictionary> dictList = new ArrayList();
        for (HashMap<String, Object> r : res) {
            dictList.add(hashMapToDictionary(r));
        }
        return dictList;
    }

    public static List<Dictionary> findByTerm(String findTerm, boolean exact) {
        if (exact) {
            return findByTerm(findTerm);
        } else {
            Query q = new Query("mysql", "jdbc:mysql://localhost:3306/ta", "root", "");
            String[] param = {"%" + findTerm + "%"};
            List<HashMap<String, Object>> res = q.all("select * from dict where term like ?", param);
            List<Dictionary> dictList = new ArrayList();
            for (HashMap<String, Object> r : res) {
                dictList.add(hashMapToDictionary(r));
            }
            return dictList;
        }
    }

    public static List<Dictionary> findByMeaning(String findTerm, boolean exact) {
        if (exact) {
            Query q = new Query("mysql", "jdbc:mysql://localhost:3306/ta", "root", "");
            String[] param = {"%" + findTerm + "%"};
            List<HashMap<String, Object>> res = q.all("select * from dict where meaning like ?", param);
            List<Dictionary> dictList = new ArrayList();
            for (HashMap<String, Object> r : res) {
                dictList.add(hashMapToDictionary(r));
            }
            return dictList;
        } else {
            Query q = new Query("mysql", "jdbc:mysql://localhost:3306/ta", "root", "");
            String[] param = findTerm.split(" ");
            String where = "meaning like ? ";
            for (int i = 0; i < param.length; i++) {
                param[i] = "%" + param[i] + "%";
            }
            for (int i = 1; i < param.length; i++) {
                where += "and meaning like ? ";
            }
            List<HashMap<String, Object>> res = q.all("select * from dict where " + where, param);
            List<Dictionary> dictList = new ArrayList();
            for (HashMap<String, Object> r : res) {
                dictList.add(hashMapToDictionary(r));
            }
            return dictList;
        }
    }

    private static void makeSynonym() {
        Query q = new Query("mysql", "jdbc:mysql://localhost:3306/ta", "root", "");
        List<HashMap<String, Object>> res = q.all("select term,meaning from dict", null);
        for (HashMap<String, Object> r : res) {
            String[] param = {(String) r.get("meaning")};
            List<HashMap<String, Object>> res2 = q.all("select term,meaning from dict where meaning = ?", param);
            String term1 = (String) r.get("term");
            String synonym = null;
            if (res2.size() < 2) {
                continue;
            }
            for (HashMap<String, Object> r2 : res2) {
                String term2 = (String) r2.get("term");
                if (term1 != null && term2 != null && !(term1.equals(term2))) {
                    synonym = (synonym == null) ? term2 : (synonym + "," + term2);
                }
            }
            String[] param2 = {synonym, term1};
            q.n("update dict set synonyms = ? where term = ?", param2);
        }
    }

    private static void comparePPDM() {
        Query q = new Query("mysql", "jdbc:mysql://localhost:3306/ta", "root", "");
        List<HashMap<String, Object>> res = q.all("select distinct `column`, term from dictionary", null);
        for (HashMap<String, Object> r : res) {
            String[] param = {"%" + (String) r.get("term") + "%"};
            List<HashMap<String, Object>> res2 = q.all("select * from dict where meaning like ?", param);
            if (res2.size() > 0) {
                System.out.println(r.get("term"));
            }

        }
    }

    public static void main(String[] args) {
        makeSynonym();
    }
}
