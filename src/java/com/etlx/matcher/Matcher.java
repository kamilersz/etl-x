/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etlx.matcher;

import com.etlx.metadata.Column;
import com.etlx.metadata.Table;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kamilersz
 */
public abstract class Matcher {

    protected ArrayList<Entry<String, Entry<String, Entry<String, Double>>>> tableDist;
    protected ArrayList<Entry<Entry<String, String>, Entry<Entry<String, String>, Entry<String, Double>>>> columnDist;

    protected HashMap<String,HashMap<String,Integer>> tableDpDist;
    protected HashMap<String,HashMap<String,Integer>> tableOpDist;
    
    public Matcher() {
        tableDist = new ArrayList();
        columnDist = new ArrayList();
        tableDpDist = new HashMap();
        tableOpDist = new HashMap();
    }
    
    protected String getTableMatch(String table) {
        for (Entry<String, Entry<String, Entry<String, Double>>> e : tableDist) {
            if (e.getKey().equals(table)) {
                return e.getValue().getKey();
            }
        }
        return null;
    }
    
    protected void addTableDist(String tableFrom, String tableTo, String relation, double score) {
        tableDist.add(new SimpleEntry(tableTo,new SimpleEntry<String, Entry<String, Double>>
                (tableFrom, new SimpleEntry<String,Double>(relation,score))));
    }
    
    protected void addColumnDist(String tableFrom, String columnFrom, String tableTo, String columnTo, String relation, double score) {
        columnDist.add(new SimpleEntry(new SimpleEntry<String,String>(tableTo,columnTo),
                new SimpleEntry<Entry<String, String>, Entry<String, Double>>
                (new SimpleEntry<String,String>(tableFrom,columnFrom), new SimpleEntry<String,Double>(relation,score))));
    }
    
    protected void addTableDpDist(String table1, String table2) {
        HashMap<String,Integer> count;
        if (tableDpDist.containsKey(table1)) {
            count = tableDpDist.get(table1);
            if (count.containsKey(table2)) {
                count.put(table2, count.get(table2) + 1);
            } else {
                count.put(table2, 1);
            }
        } else {
            count = new HashMap();
            count.put(table2,1);
            tableDpDist.put(table1,count);
        }
    }
    
    protected void addTableOpDist(String table1, String table2) {
        HashMap<String,Integer> count;
        if (tableOpDist.containsKey(table1)) {
            count = tableOpDist.get(table1);
            if (count.containsKey(table2)) {
                count.put(table2, count.get(table2) + 1);
            } else {
                count.put(table2, 1);
            }
        } else {
            count = new HashMap();
            count.put(table2,1);
            tableOpDist.put(table1,count);
        }
    }
    
    protected boolean isMapped(String tableTo, String colTo) {
        for(Entry<Entry<String, String>, Entry<Entry<String, String>, Entry<String, Double>>> e : columnDist) {
            if (e.getKey().getKey().equals(tableTo) && e.getKey().getValue().equals(colTo))
                return true;
        }
        return false;
    }

    abstract public void match(Properties params);

    public JSONObject toJson() throws JSONException {
        JSONObject result = new JSONObject();
        JSONArray result_table = new JSONArray();
        JSONArray result_column = new JSONArray();

        Iterator it = tableDist.iterator();
        while (it.hasNext()) {
            JSONArray map = new JSONArray();
            Entry<String, Entry<String, Entry<String, Double>>> e = (Entry) it.next();
            map.put(e.getValue().getKey());
            map.put(e.getKey());
            map.put(e.getValue().getValue().getValue());
            result_table.put(map);
        }
        it = columnDist.iterator();
        while (it.hasNext()) {
            JSONArray map = new JSONArray();
            Entry<Entry<String, String>, Entry<Entry<String, String>, Entry<String, Double>>> e = (Entry) it.next();
            map.put(e.getValue().getKey().getKey());
            map.put(e.getValue().getKey().getValue());
            map.put(e.getKey().getKey());
            map.put(e.getKey().getValue());
            map.put(e.getValue().getValue().getValue());
            result_column.put(map);
        }
        result.put("table_map", result_table);
        result.put("column_map", result_column);
        return result;
    }
}
